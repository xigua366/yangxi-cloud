package com.yangxi.cloud.framework.web.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import com.yangxi.cloud.framework.constants.CoreConstant;
import com.yangxi.cloud.framework.web.filter.GlobalHttpRequestFilter;
import com.yangxi.cloud.framework.web.properties.WebProperties;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * <p>
 *
 * </p>
 *
 * @author yangxi
 * @version 1.0
 */
@Configuration
@Import(value = {GlobalExceptionHandler.class, GlobalRequestBodyAdvice.class, GlobalResponseBodyAdvice.class})
@EnableConfigurationProperties(WebProperties.class)
public class WebConfiguration {

    @Bean
    public FilterRegistrationBean<GlobalHttpRequestFilter> globalHttpRequestFilter() {
        GlobalHttpRequestFilter globalHttpRequestFilter = new GlobalHttpRequestFilter();
        FilterRegistrationBean<GlobalHttpRequestFilter> filterFilterRegistrationBean = new FilterRegistrationBean<>();
        filterFilterRegistrationBean.setFilter(globalHttpRequestFilter);
        filterFilterRegistrationBean.setName("globalHttpRequestFilter");
        filterFilterRegistrationBean.addUrlPatterns("/*");
        filterFilterRegistrationBean.setOrder(100);
        return filterFilterRegistrationBean;
    }

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
        return jacksonObjectMapperBuilder -> {
            // 设置默认时区
            // jacksonObjectMapperBuilder.timeZone(ZoneId.systemDefault().getId());
            jacksonObjectMapperBuilder.timeZone(CoreConstant.DEFAULT_TIME_ZONE);

            // 序列化的时候属性值为NULL，也进行序列化
            jacksonObjectMapperBuilder.serializationInclusion(JsonInclude.Include.ALWAYS);
            // 反序列化的时候如果多了其他属性，也不抛出异常
            jacksonObjectMapperBuilder.featuresToDisable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

            // java.util.Date默认格式配置
            jacksonObjectMapperBuilder.simpleDateFormat(CoreConstant.DATE_TIME_FORMAT_PATTERN);


            // Java8相关日期类默认格式配置
            JavaTimeModule javaTimeModule = new JavaTimeModule();
            javaTimeModule.addSerializer(LocalDateTime.class,new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(CoreConstant.DATE_TIME_FORMAT_PATTERN)));
            javaTimeModule.addSerializer(LocalDate.class,new LocalDateSerializer(DateTimeFormatter.ofPattern(CoreConstant.DATE_FORMAT_PATTERN)));
            javaTimeModule.addSerializer(LocalTime.class,new LocalTimeSerializer(DateTimeFormatter.ofPattern(CoreConstant.TIME_FORMAT_PATTERN)));
            javaTimeModule.addDeserializer(LocalDateTime.class,new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(CoreConstant.DATE_TIME_FORMAT_PATTERN)));
            javaTimeModule.addDeserializer(LocalDate.class,new LocalDateDeserializer(DateTimeFormatter.ofPattern(CoreConstant.DATE_FORMAT_PATTERN)));
            javaTimeModule.addDeserializer(LocalTime.class,new LocalTimeDeserializer(DateTimeFormatter.ofPattern(CoreConstant.TIME_FORMAT_PATTERN)));
            jacksonObjectMapperBuilder.modules(javaTimeModule);
        };
    }

}