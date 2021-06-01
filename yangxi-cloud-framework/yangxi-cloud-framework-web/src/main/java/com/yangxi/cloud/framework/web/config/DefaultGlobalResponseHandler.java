package com.yangxi.cloud.framework.web.config;

import com.yangxi.cloud.framework.core.JsonData;
import com.yangxi.cloud.framework.core.JsonMap;
import com.yangxi.cloud.framework.utils.JsonUtil;
import com.yangxi.cloud.framework.web.utils.CommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Objects;

/**
 * @author yangxi
 * @version 1.0
 * @date 2020-10-10 17:26
 */
@Slf4j
@Configuration
@RestControllerAdvice
public class DefaultGlobalResponseHandler implements ResponseBodyAdvice<Object> {

    /**
     * 是否支持advice功能
     *
     * @param returnType
     * @param converterType
     * @return true:支持  false:不支持
     */
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    /**
     * 处理response的具体业务方法
     * @param body
     * @param returnType
     * @param selectedContentType
     * @param selectedConverterType
     * @param request
     * @param response
     * @return
     */
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {


        if(body instanceof JsonData) {
            return body;
        }

        if(body instanceof String) {
            try {
                HttpServletResponse httpServletResponse = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getResponse();
                if(httpServletResponse != null) {
                    CommonUtil.sendJsonMessage(httpServletResponse, JsonData.buildSuccess(body));
                    return null;
                }
            } catch(Exception e) {
                log.error("get httpServletResponse error", e);
            }

            return JsonUtil.object2Json(JsonData.buildSuccess(body));
        }

        // 如果是返回JsonMap对象，则不进行JsonData封装
        if(body instanceof JsonMap) {
            return body;
        }

        return JsonData.buildSuccess(body);
    }
}