package com.byc.backendmodel.dto.question;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 更新请求
 *
 */
@Data
public class QuestionUpdateRequest implements Serializable {


    /**
     * id
     */
    private Long id;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 题目难度
     */
    private Integer difficulty;

    /**
     * 标签列表
     */
    private List<String> tags;

    /**
     * 题目答案
     */
    private LanguageConfig answer;


//    /**
//     * 方法名称
//     */
//    private String methodName;
//
//    /**
//     * 方法参数类型
//     */
//    private List<String> types;
//
//    /**
//     * 返回值类型
//     */
//    private String returnType;

    /**
     * 默认代码
     */
    private LanguageConfig defaultCode;

    /**
     * 主类
     */
    private LanguageConfig mainClass;

    /**
     * 判题用例
     */
    private List<JudgeCase> judgeCase;

    /**
     * 判题配置
     */
    private JudgeConfig judgeConfig;

    private static final long serialVersionUID = 1L;
}