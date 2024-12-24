package hh.plus.server.common.config;

import hh.plus.server.balance.presentation.interceptor.BalanceInterceptor;
import hh.plus.server.cart.presentation.interceptor.CartInterceptor;
import hh.plus.server.order.presentation.interceptor.OrderInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new BalanceInterceptor()).addPathPatterns("/api/balance/*");
        registry.addInterceptor(new CartInterceptor()).addPathPatterns("/api/cart/*");
        registry.addInterceptor(new OrderInterceptor()).addPathPatterns("/api/order/*");
    }
}
