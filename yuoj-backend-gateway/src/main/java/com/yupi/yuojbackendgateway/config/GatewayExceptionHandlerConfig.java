package com.yupi.yuojbackendgateway.config;

import com.alibaba.csp.sentinel.adapter.gateway.sc.SentinelGatewayFilter;
import com.yupi.yuojbackendgateway.exception.SentinelBlockExceptionHandler;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

@Configuration
public class GatewayExceptionHandlerConfig {

    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)  //优先级必须最高
    public SentinelBlockExceptionHandler sentinelBlockExceptionHandler() {
        return new SentinelBlockExceptionHandler();
    }

}
