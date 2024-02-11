package com.samax.simpleCommerce.common.config;

import com.samax.simpleCommerce.common.excption.ScApiExceptionHandlerFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ScApiExceptionConfig {

    @Bean
    public FilterRegistrationBean<ScApiExceptionHandlerFilter> loggingFilter(ScApiExceptionHandlerFilter exceptionHandlerFilter){
        FilterRegistrationBean<ScApiExceptionHandlerFilter> registrationBean = new FilterRegistrationBean<>();

        registrationBean.setFilter(exceptionHandlerFilter);
        registrationBean.addUrlPatterns("/*");
        registrationBean.setOrder(Integer.MAX_VALUE);

        return registrationBean;
    }

}
