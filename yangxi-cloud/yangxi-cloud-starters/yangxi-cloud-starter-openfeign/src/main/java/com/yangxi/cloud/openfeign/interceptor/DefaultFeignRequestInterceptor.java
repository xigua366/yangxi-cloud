package com.yangxi.cloud.openfeign.interceptor;

import com.yangxi.cloud.framework.web.constants.WebConstant;
import com.yangxi.cloud.framework.web.context.TenantContext;
import feign.RequestInterceptor;
import feign.RequestTemplate;

/**
 * <p>
 * 默认的openfeign拦截器组件
 * </p>
 *
 * @author yangxi
 * @version 1.0
 */
public class DefaultFeignRequestInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate template) {

        // feign调用下游服务默认增加的header信息
        String tenantId = TenantContext.getTenantId();

        if(tenantId != null && !"".equals(tenantId)) {
            template.header(WebConstant.TENANT_ID, tenantId);
        }
    }

}