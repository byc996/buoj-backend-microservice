package com.byc.judgeservice.judge.codesandbox.impl;

import com.byc.judgeservice.judge.codesandbox.CodeSandbox;
import com.byc.backendmodel.codesandbox.ExecuteCodeRequest;
import com.byc.backendmodel.codesandbox.ExecuteCodeResponse;
import com.byc.backendmodel.codesandbox.JudgeInfo;
import com.byc.backendmodel.enums.JudgeInfoMessageEnum;
import com.byc.backendmodel.enums.QuestionSubmitStatusEnum;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * 示例代码沙箱（仅为了跑通业务流程）
 */
@Slf4j
public class ExampleCodeSandbox implements CodeSandbox {
    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        List<String> inputList = executeCodeRequest.getInputList();
        ExecuteCodeResponse executeCodeResponse = new ExecuteCodeResponse();
        executeCodeResponse.setOutputList(inputList);
        executeCodeResponse.setMessage("测试执行成功");
        executeCodeResponse.setStatus(QuestionSubmitStatusEnum.SUCCEED.getValue());
        JudgeInfo judgeInfo = new JudgeInfo();
        judgeInfo.setMessage(JudgeInfoMessageEnum.ACCEPTED.getText());
        judgeInfo.setMemory(100.0);
        judgeInfo.setTime(100L);
//        executeCodeResponse.setJudgeInfo(judgeInfo);
        return executeCodeResponse;
    }
}
