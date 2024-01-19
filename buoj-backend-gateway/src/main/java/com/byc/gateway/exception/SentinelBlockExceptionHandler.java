package com.byc.gateway.exception;

import com.alibaba.csp.sentinel.slots.block.authority.AuthorityException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowException;
import com.alibaba.csp.sentinel.slots.system.SystemBlockException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.byc.backendcommon.common.BaseResponse;
import com.byc.backendcommon.common.ResultUtils;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebExceptionHandler;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class SentinelBlockExceptionHandler implements WebExceptionHandler {
    private static final int SENTINEL_EXCEPTION_STATUS = 429;
    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
        BaseResponse baseResponse = ResultUtils.error(SENTINEL_EXCEPTION_STATUS, "sentinel异常");
        if (ex instanceof FlowException) {
            baseResponse.setMessage("流控异常");
        } else if (ex instanceof DegradeException) {
            baseResponse.setMessage("熔断降级异常");
        } else if (ex instanceof ParamFlowException) {
            baseResponse.setMessage("热点参数限流异常");
        } else if (ex instanceof AuthorityException) {
            baseResponse.setMessage("权限异常");
        } else if (ex instanceof SystemBlockException){
            baseResponse.setMessage("系统保护异常");
        }


        return response(exchange.getResponse(), baseResponse);
    }

    private Mono<Void> response(ServerHttpResponse response, BaseResponse baseResponse) {
        response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
        String resJson = null;
        try {
            resJson = new ObjectMapper().writeValueAsString(baseResponse);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        DataBuffer dataBuffer = response.bufferFactory().wrap(resJson.getBytes());
        return response.writeWith(Flux.just(dataBuffer));
    }
}
