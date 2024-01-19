package com.byc.judgeservice.judge.codesandbox;

import com.byc.backendmodel.codesandbox.ExecuteCodeRequest;
import com.byc.backendmodel.codesandbox.ExecuteCodeResponse;

/**
 * 代码沙箱接口定义
 */
public interface CodeSandbox {

    /**
     * 执行代码
     *
     * @param executeCodeRequest
     * @return
     */
    ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest);
}
