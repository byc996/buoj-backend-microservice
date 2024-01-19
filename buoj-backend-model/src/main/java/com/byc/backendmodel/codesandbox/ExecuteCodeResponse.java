package com.byc.backendmodel.codesandbox;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExecuteCodeResponse {

    private List<String> outputList;

    /**
     * 执行状态
     */
    private Integer status;

    /**
     * 执行信息
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
     * 执行时间
     */
    private Long time;

    /**
     * 执行内存
     */
    private Long memory;

//    /**
//     * 判题信息
//     */
//    private JudgeInfo judgeInfo;
}
