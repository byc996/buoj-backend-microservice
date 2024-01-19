package com.byc.gateway.config;

import com.byc.gateway.exception.SentinelBlockExceptionHandler;
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
