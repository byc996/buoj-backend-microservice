package com.byc.judgeservice.judge.codesandbox.impl;

import com.byc.judgeservice.judge.codesandbox.CodeSandbox;
import com.byc.backendmodel.codesandbox.ExecuteCodeRequest;
import com.byc.backendmodel.codesandbox.ExecuteCodeResponse;

/**
 * 第三方代码沙箱（调用网上现成的代码沙箱）
 */
public class ThirdPartyCodeSandbox implements CodeSandbox {
    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        System.out.println("第三方代码沙箱");
        return null;
    }
}
