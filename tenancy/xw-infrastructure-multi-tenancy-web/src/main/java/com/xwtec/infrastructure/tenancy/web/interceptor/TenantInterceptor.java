
package com.xwtec.infrastructure.tenancy.web.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xwtec.infrastructure.tenancy.web.config.TenantProperties;
import com.xwtec.infrastructure.tenancy.web.context.TenantContextHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class TenantInterceptor implements HandlerInterceptor{
    private final static Logger logger = LoggerFactory.getLogger(TenantInterceptor.class);

    public TenantProperties properties;


    public TenantInterceptor(TenantProperties properties){
        this.properties = properties;
    }
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String tenant = request.getHeader(properties.getTenantFlag());
        if(tenant!=null&&tenant.trim().length()>0){
            TenantContextHolder.setTenant(tenant);
            return true;
        }else{
            logger.info("没有租户信息");
            ObjectMapper mapper = new ObjectMapper();
            Map<String,String> map = new HashMap<>();
            map.put("code","400");
            map.put("message","没有租户信息");
            response.setContentType("application/json;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write(mapper.writeValueAsString(map));
            return false;
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
                                 @Nullable Exception ex) throws Exception {
        //清空租户信息
        TenantContextHolder.clear();
    }
}
