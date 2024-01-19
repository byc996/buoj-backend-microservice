package com.byc.judgeservice.judge;

import com.byc.judgeservice.judge.strategy.DefaultJudgeStrategy;
import com.byc.judgeservice.judge.strategy.JavaLanguageJudgeStrategy;
import com.byc.judgeservice.judge.strategy.JudgeContext;
import com.byc.judgeservice.judge.strategy.JudgeStrategy;
import com.byc.backendmodel.codesandbox.JudgeInfo;
import com.byc.backendmodel.entity.QuestionSubmit;
import org.springframework.stereotype.Service;

/**
 * 判题管理（简化调用）
 */
@Service
public class JudgeManager {

    /**
     * 执行判题
     *
     * @param judgeContext
     * @return
     */
    JudgeInfo doJudge(JudgeContext judgeContext) {
        QuestionSubmit questionSubmit = judgeContext.getQuestionSubmit();
        String language = questionSubmit.getLanguage();
        JudgeStrategy judgeStrategy = new DefaultJudgeStrategy();
        if ("java".equals(language)) {
            judgeStrategy = new JavaLanguageJudgeStrategy();
        } else {
            judgeStrategy = new JavaLanguageJudgeStrategy();
        }
        return judgeStrategy.doJudge(judgeContext);
    }

}
