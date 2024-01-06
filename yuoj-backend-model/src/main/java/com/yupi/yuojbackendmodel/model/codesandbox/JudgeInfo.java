package com.yupi.yuojbackendmodel.model.codesandbox;

import lombok.Data;

/**
 * 判题信息
 */
@Data
public class JudgeInfo {

    /**
     * 程序执行信息  比如：通过
     */
    private String message;

    /**
     * 详细错误信息
     */
    private String detailMessage;

    /**
     * 标准输出信息
     */
    private String stdOut;

    /**
     * 程序执行状态  比如：0
     */
    private Integer status;

    /**
     * 消耗内存
     */
    private Double memory;

    /**
     * 消耗时间（KB）
     */
    private Long time;
}
