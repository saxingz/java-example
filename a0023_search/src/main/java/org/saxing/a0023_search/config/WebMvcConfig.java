package org.saxing.a0023_search.config;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import javax.servlet.MultipartConfigElement;
import java.nio.charset.Charset;

/**
 * swagger-ui
 *
 * Created by saxing on 2018/5/6.
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {


    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowCredentials(true)
                .allowedHeaders("*")
                .allowedOrigins("*")
                .allowedMethods("*");
    }

    @Bean
    public MultipartConfigElement multipartConfigElement(){
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //文件最大kb mb
        factory.setMaxFileSize("10MB");
        factory.setMaxRequestSize("100MB");
        return factory.createMultipartConfig();
    }

    /**
     * 乱码
     *
     * @return
     */
    @Bean
    public HttpMessageConverter<String> responseBodyConverter(){
        return new StringHttpMessageConverter(Charset.forName("UTF-8"));
    }

}

//    @Bean
//    public MappingJackson2HttpMessageConverter customJackson2HttpMessageConverter() {
//        MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.configure(
//                DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//        objectMapper.getSerializerProvider().setNullValueSerializer(
//                new JsonSerializer<Object>() {
//                    @Override
//                    public void serialize(Object value, JsonGenerator jgen,
//                                          SerializerProvider provider) throws IOException,
//                            JsonProcessingException {
//                        jgen.writeString("");
//                    }
//                });
//        // 进行HTML解码
//        objectMapper.registerModule(new SimpleModule().addSerializer(
//                String.class, new JsonSerializer<String>() {
//                    @Override
//                    public void serialize(String value, JsonGenerator jgen,
//                                          SerializerProvider provider) throws IOException,
//                            JsonProcessingException {
//                        jgen.writeString(StringEscapeUtils.unescapeHtml4(value));
//                    }
//                }));
//        jsonConverter.setObjectMapper(objectMapper);
//        return jsonConverter;
//    }
//
//    @Override
//    public void configureMessageConverters(
//            List<HttpMessageConverter<?>> converters) {
//        converters.add(customJackson2HttpMessageConverter());
//        super.addDefaultHttpMessageConverters(converters);
//    }
//
//    @Override
//    protected void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
//        configurer.enable();
//    }


