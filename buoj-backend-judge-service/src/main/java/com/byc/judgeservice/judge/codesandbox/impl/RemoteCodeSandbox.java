package com.byc.judgeservice.judge.codesandbox.impl;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.byc.backendcommon.common.ErrorCode;
import com.byc.backendcommon.exception.BusinessException;
import com.byc.judgeservice.judge.codesandbox.CodeSandbox;
import com.byc.backendmodel.codesandbox.ExecuteCodeRequest;
import com.byc.backendmodel.codesandbox.ExecuteCodeResponse;
import org.apache.commons.lang3.StringUtils;

/**
 * 远程代码沙箱（实际调用接口的沙箱）
 */
public class RemoteCodeSandbox implements CodeSandbox {

    // 定义鉴权请求头和密钥
    private static final String AUTH_REQUEST_HEADER = "auth";

    private static final String AUTH_REQUEST_SECRET = "secretKey";

    private static final String REMOTE_HOST = "34.16.153.108";
    private static final String REMOTE_PORT = "8090";

    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        System.out.println("远程代码沙箱");
        String url = String.format("http://%s:%s/executeCode", REMOTE_HOST, REMOTE_PORT);
        String json = JSONUtil.toJsonStr(executeCodeRequest);
        String responseStr = HttpUtil.createPost(url)
                .header(AUTH_REQUEST_HEADER, AUTH_REQUEST_SECRET)
                .timeout(60000)
                .body(json)
                .execute()
                .body();
        if (StringUtils.isBlank(responseStr)) {
            throw new BusinessException(ErrorCode.API_REQUEST_ERROR, "远程调用代码沙箱错误, 错误信息 = " + responseStr);
        }
        return JSONUtil.toBean(responseStr, ExecuteCodeResponse.class);
    }
}
