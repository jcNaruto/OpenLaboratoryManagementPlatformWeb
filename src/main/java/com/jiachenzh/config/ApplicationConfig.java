package com.jiachenzh.config;

import com.jiachenzh.interceptor.JwtInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

/**
 * @ClassName ApplicationConfig
 * @Description jwtInterceptor配置类，注意防止拦截登陆
 * @Author
 * @Date 2019/3/6 13:35
 * @Version 1.0
 */

@Configuration
public class ApplicationConfig implements WebMvcConfigurer {
    @Autowired
    private JwtInterceptor jwtInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration addInterceptor = registry.addInterceptor(jwtInterceptor);
        addInterceptor.addPathPatterns("/**");
        addInterceptor.excludePathPatterns("/**/login");
        addInterceptor.excludePathPatterns("/res/**");
        addInterceptor.excludePathPatterns("/html/**");

    }




}
