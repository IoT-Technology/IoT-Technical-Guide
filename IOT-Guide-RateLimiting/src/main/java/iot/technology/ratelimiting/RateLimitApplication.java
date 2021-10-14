package iot.technology.ratelimiting;

import iot.technology.ratelimiting.bucket4j.interceptor.RateLimitInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author mushuwei
 */
@SpringBootApplication(scanBasePackages = {"iot.technology.ratelimiting"})
public class RateLimitApplication implements WebMvcConfigurer {

    @Autowired
    @Lazy
    private RateLimitInterceptor interceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(interceptor)
                .addPathPatterns("/api/v1/area/**");
    }

    public static void main(String[] args) {
        SpringApplication.run(RateLimitApplication.class);
    }
}
