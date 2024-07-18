package hh.plus.server.balance.config;

import hh.plus.server.balance.presentation.interceptor.BalanceInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new BalanceInterceptor()).addPathPatterns("/api/balance/*");
    }
}
