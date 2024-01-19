package com.byc.judgeservice.judge.strategy;

import com.byc.backendmodel.codesandbox.JudgeInfo;
import com.byc.backendmodel.dto.question.JudgeCase;
import com.byc.backendmodel.entity.Question;
import com.byc.backendmodel.entity.QuestionSubmit;
import lombok.Data;

import java.util.List;

/**
 * 上下文（用于定义在策略中传递的参数）
 */
@Data
public class JudgeContext {

    private JudgeInfo judgeInfo;

    private List<String> inputList;

    private List<String> outputList;

    private List<JudgeCase> judgeCaseList;

    private Question question;

    private QuestionSubmit questionSubmit;

}
