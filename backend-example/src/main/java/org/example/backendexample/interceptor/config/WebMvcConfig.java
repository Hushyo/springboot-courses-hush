package org.example.backendexample.interceptor.config;

import lombok.RequiredArgsConstructor;
import org.example.backendexample.interceptor.AdminInterceptor;
import org.example.backendexample.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebMvcConfig implements WebMvcConfigurer {

    private final LoginInterceptor loginInterceptor;
    private final AdminInterceptor adminInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/api/**") //一个*代表api下单层的请求，两个**代表api下所有的请求
                //对于所有的请求都拦截，但是得允许用户登录
                .excludePathPatterns("/api/login");
        registry.addInterceptor(adminInterceptor)
                .addPathPatterns(("/api/admin/**"));
    }
}
