package com.magic.core.config;

import com.magic.core.springframework.CustomerHandlerInterceptor;
import com.magic.core.springframework.JFengJsonConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.http.MediaType;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Yin Jian Feng
 * Date: 14-3-4
 * Time: 上午11:52
 * To change this template use File | Settings | File Templates.
 */
@Configuration
@ComponentScan(basePackages = "com.magic")
@ImportResource("classpath:spring-security.xml")
public class WebConfig extends WebMvcConfigurationSupport {
    @Bean
    ViewResolver viewResolver(){
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setViewClass(JstlView.class);
        resolver.setContentType("text/html;charset=UTF-8");
//        resolver.setPrefix("/jsp/");
        resolver.setSuffix(".jsp");
        return resolver;
    }
    @Bean
    TilesConfigurer tilesConfigurer(){
        TilesConfigurer configurer = new TilesConfigurer();
        configurer.setDefinitions(new String[]{"/tiles/tiles.xml"});
        return configurer;
    }

/*    @Bean
    public PropertyPlaceholderConfigurer propertyPlaceholderConfigurer(){
        PropertyPlaceholderConfigurer propertyPlaceholderConfigurer = new PropertyPlaceholderConfigurer();
        propertyPlaceholderConfigurer.setLocation(new ClassPathResource("db.properties"));
        return propertyPlaceholderConfigurer;
    }*/

    @Override
    protected void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        StringHttpMessageConverter stringHttpMessageConverter = new StringHttpMessageConverter();
        stringHttpMessageConverter.setSupportedMediaTypes(MediaType.parseMediaTypes("text/html;charset=UTF-8,text/plain;charset=UTF-8"));

        // convert json to object
        MappingJackson2HttpMessageConverter mappingJacksonHttpMessageConverter = new JFengJsonConverter();
        mappingJacksonHttpMessageConverter.setSupportedMediaTypes(MediaType.parseMediaTypes("application/json;charset=UTF-8"));

//        MarshallingHttpMessageConverter marshallingHttpMessageConverter = new MarshallingHttpMessageConverter();
//        marshallingHttpMessageConverter.setSupportedMediaTypes(MediaType.parseMediaTypes("application/xml"));

//        FormHttpMessageConverter formHttpMessageConverter = new FormHttpMessageConverter();

        ByteArrayHttpMessageConverter byteArrayHttpMessageConverter = new ByteArrayHttpMessageConverter();
        List<MediaType> supportedMediaTypes = new ArrayList<MediaType>();
        supportedMediaTypes.add(MediaType.IMAGE_JPEG);
        supportedMediaTypes.add(MediaType.IMAGE_PNG);
        supportedMediaTypes.add(MediaType.IMAGE_GIF);
        byteArrayHttpMessageConverter.setSupportedMediaTypes(supportedMediaTypes);

        converters.add(byteArrayHttpMessageConverter);
        converters.add(stringHttpMessageConverter);
        converters.add(mappingJacksonHttpMessageConverter);
//        converters.add(formHttpMessageConverter);
//        converters.add(marshallingHttpMessageConverter);
    }

/*
    @Bean
    public RequestMappingHandlerAdapter requestMappingHandlerAdapter(){
        RequestMappingHandlerAdapter requestMappingHandlerAdapter = new RequestMappingHandlerAdapter();
        StringHttpMessageConverter stringHttpMessageConverter = new StringHttpMessageConverter();
        stringHttpMessageConverter.setSupportedMediaTypes(MediaType.parseMediaTypes("text/html;charset=UTF-8,text/plain;charset=UTF-8"));
        List<HttpMessageConverter<?>> converters = new ArrayList<HttpMessageConverter<?>>();
        converters.add(stringHttpMessageConverter);
        requestMappingHandlerAdapter.setMessageConverters(converters);
        return requestMappingHandlerAdapter;
    }
*/

    /**
     */
   /* @Override
    protected void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {
        CustomerExceptionResolver handlerExceptionResolver = new CustomerExceptionResolver();
        exceptionResolvers.add(handlerExceptionResolver);
    }*/

    /**
     * Supports FileUploads.
     */
    @Bean
    public MultipartResolver multipartResolver(){
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setMaxUploadSize(10000000);
        return multipartResolver;
    }

    /**
     * @param registry
     * Handle status resources.
     */
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/res/**").addResourceLocations("/res/");
    }

    @Bean
    public CustomerHandlerInterceptor customerHandlerInterceptor(){
        return new CustomerHandlerInterceptor();
    }

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(customerHandlerInterceptor());
        super.addInterceptors(registry);
    }
}
