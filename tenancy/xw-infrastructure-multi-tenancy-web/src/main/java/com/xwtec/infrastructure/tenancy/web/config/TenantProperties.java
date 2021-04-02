package com.xwtec.infrastructure.tenancy.web.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import static com.xwtec.infrastructure.tenancy.web.config.TenantProperties.DEFAULT_PREFIX;

@ConfigurationProperties(value = DEFAULT_PREFIX)
public class TenantProperties {

    /**
     * PREFIX
     */
    public static final String DEFAULT_PREFIX = "xw.tenant";

    /**
     * 是否启用多租户
     */
    private boolean enable = false;

    private String tenantFlag = "tenant";

    /**
     * 租户不拦截的路径
     */
    private String[] excludePathPatterns ;

    /**
     * 租户拦截的路径
     */
    private String[] addPathPatterns;

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public String getTenantFlag() {
        return tenantFlag;
    }

    public void setTenantFlag(String tenantFlag) {
        this.tenantFlag = tenantFlag;
    }

    public String[] getExcludePathPatterns() {
        return excludePathPatterns;
    }

    public void setExcludePathPatterns(String[] excludePathPatterns) {
        this.excludePathPatterns = excludePathPatterns;
    }

    public String[] getAddPathPatterns() {
        return addPathPatterns;
    }

    public void setAddPathPatterns(String[] addPathPatterns) {
        this.addPathPatterns = addPathPatterns;
    }
}

