package com.zbcn.feignserver.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/**
 *  通用配置
 *  <br/>
 *  @author zbcn8
 *  @since  2020/12/8 15:37
 */
@Configuration
public class FeignConfig {
    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
}
