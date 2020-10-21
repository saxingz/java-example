package org.saxing.a0041_wemedia.config;

import com.google.api.services.youtube.YouTube;
import org.saxing.a0041_wemedia.platform.youtube.YouTubeApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.IOException;
import java.security.GeneralSecurityException;

/**
 * web mvc config
 *
 * @author saxing 2020/10/18 21:49
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    /**
     *  如果配置跨域，就增加这个配置
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("/static/");
        registry.addResourceHandler("/js/**").addResourceLocations("/js/");
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars");
    }


    /**
     * 跨域支持配置
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowCredentials(true).allowedOrigins("*")
                .allowedMethods("GET", "PUT", "DELETE", "POST", "OPTIONS").maxAge(3600);
    }


    @Bean
    public YouTube getService() throws GeneralSecurityException, IOException {
        return new YouTubeApi().getService();
    }

}
