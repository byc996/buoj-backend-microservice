package com.byc.judgeservice.judge;

import cn.hutool.json.JSONUtil;
import com.byc.backendcommon.common.ErrorCode;
import com.byc.backendcommon.exception.BusinessException;
import com.byc.judgeservice.judge.codesandbox.CodeSandbox;
import com.byc.judgeservice.judge.codesandbox.CodeSandboxFactory;
import com.byc.judgeservice.judge.codesandbox.CodeSandboxProxy;
import com.byc.judgeservice.judge.strategy.JudgeContext;
import com.byc.backendmodel.codesandbox.ExecuteCodeRequest;
import com.byc.backendmodel.codesandbox.ExecuteCodeResponse;
import com.byc.backendmodel.codesandbox.JudgeInfo;
import com.byc.backendmodel.dto.question.JudgeCase;
import com.byc.backendmodel.entity.Question;
import com.byc.backendmodel.entity.QuestionSubmit;
import com.byc.backendmodel.enums.QuestionSubmitStatusEnum;
import com.byc.serviceclient.service.QuestionFeignClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JudgeServiceImpl implements JudgeService {

    @Resource
    private QuestionFeignClient questionFeignClient;

    @Resource
    private JudgeManager judgeManager;

    @Value("${codesandbox.type:example}")
    private String type;


    @Override
    public QuestionSubmit doJudge(long questionSubmitId) {
        // 1）传入题目的提交 id，获取到对应的题目、提交信息（包含代码、编程语言等）
        QuestionSubmit questionSubmit = questionFeignClient.getQuestionSubmitById(questionSubmitId);
        if (questionSubmit == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR, "提交信息不存在");
        }
        Long questionId = questionSubmit.getQuestionId();
        Question question = questionFeignClient.getQuestionById(questionId);
        if (question == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR, "题目不存在");
        }
        // 2）如果题目提交状态不为等待中，就不用重复执行了
        if (!questionSubmit.getStatus().equals(QuestionSubmitStatusEnum.WAITING.getValue())) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR, "题目正在判题中");
        }
        // 3）更改判题（题目提交）的状态为 “判题中”，防止重复执行
        QuestionSubmit questionSubmitUpdate = new QuestionSubmit();
        questionSubmitUpdate.setId(questionSubmitId);
        questionSubmitUpdate.setStatus(QuestionSubmitStatusEnum.RUNNING.getValue());
        boolean update = questionFeignClient.updateQuestionSubmitById(questionSubmitUpdate);
        if (!update) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "题目状态更新错误");
        }
        // 4）调用沙箱，获取到执行结果
        CodeSandbox codeSandbox = CodeSandboxFactory.newInstance(type);
        codeSandbox = new CodeSandboxProxy(codeSandbox);
        String language = questionSubmit.getLanguage();
        String code = questionSubmit.getCode();
        // 获取输入用例
        String judgeCaseStr = question.getJudgeCase();
        List<JudgeCase> judgeCaseList = JSONUtil.toList(judgeCaseStr, JudgeCase.class);
        List<String> inputList = judgeCaseList.stream().map(JudgeCase::getInput).collect(Collectors.toList());
//        List<String> types = JSONUtil.toList(question.getTypes(), String.class);
//        String paramTypes = String.join(" ", types);
        String mainClass = question.getMainClass();
        ExecuteCodeRequest executeCodeRequest = ExecuteCodeRequest.builder()
                .code(code)
                .language(language)
                .inputList(inputList)
                .mainClass(mainClass)
//                .methodName(question.getMethodName())
//                .paramTypes(paramTypes)
                .build();
        ExecuteCodeResponse executeCodeResponse = codeSandbox.executeCode(executeCodeRequest);
        List<String> outputList = executeCodeResponse.getOutputList();
        // 5）根据沙箱的执行结果，设置题目的判题状态和信息
        JudgeContext judgeContext = new JudgeContext();
        JudgeInfo judgeInfo = new JudgeInfo();
        judgeInfo.setStatus(executeCodeResponse.getStatus());
        judgeInfo.setMessage(executeCodeResponse.getMessage());
        judgeInfo.setStdOut(executeCodeResponse.getStdOut());
        judgeInfo.setTime(executeCodeResponse.getTime());
        judgeInfo.setMemory(executeCodeResponse.getMemory() * 1.0);
        judgeInfo.setDetailMessage(executeCodeResponse.getDetailMessage());
        judgeContext.setJudgeInfo(judgeInfo);
        judgeContext.setInputList(inputList);
        judgeContext.setOutputList(outputList);
        judgeContext.setJudgeCaseList(judgeCaseList);
        judgeContext.setQuestion(question);
        judgeContext.setQuestionSubmit(questionSubmit);
        judgeInfo = judgeManager.doJudge(judgeContext);
        // 6）修改数据库中的判题结果
        questionSubmitUpdate = new QuestionSubmit();
        questionSubmitUpdate.setId(questionSubmitId);
        questionSubmitUpdate.setStatus(QuestionSubmitStatusEnum.SUCCEED.getValue());
        questionSubmitUpdate.setJudgeInfo(JSONUtil.toJsonStr(judgeInfo));
        update = questionFeignClient.updateQuestionSubmitById(questionSubmitUpdate);
        if (!update) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "题目状态更新错误");
        }
        if (judgeInfo.getStatus() == 0) {
            boolean updateAcceptedNum = questionFeignClient.updateQuestionAcceptedNum(questionId);
            if (!updateAcceptedNum) throw new BusinessException(ErrorCode.SYSTEM_ERROR, "题目接受数更新错误");
        }
        QuestionSubmit questionSubmitResult = questionFeignClient.getQuestionSubmitById(questionId);
        return questionSubmitResult;
    }
}
