/*
<#include "/java_copyright.include.ftl">
 */
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>   
package ${basepackage}.dao;

<#include "/java_imports.include.ftl">
import ${basepackage}.model.${className};
<#--import ${basepackage}.dao.*;-->

import org.apache.ibatis.annotations.Mapper;

/**
<#include "/java_description.include.ftl">
 */
@Mapper
public interface ${className}Mapper {


    /**
     * 新增 ${className}
     * @param ${classNameLower}
     * @return int
     */
    int insert${className}(${className} ${classNameLower});

    /**
     * 更新 ${className}
     * @param ${classNameLower}
     * @return int
     */
    int update${className}(${className} ${classNameLower});

    /**
     * 主键删除 ${className}
     * @param ${classNameLower} 传主键
     * @return int
     */
    int delete${className}(${className} ${classNameLower});

    /**
     * 主键查询 ${className}
     * @param ${classNameLower}  传主键
     * @return ${className}
     */
    ${className} query${className}(${className} ${classNameLower});


    //int query${className}Count(${className} ${classNameLower});

    /**
     * 查询 ${className}  通过分页组件 拦截sql分页
     * @param ${classNameLower}  传查询条件
     * @return ${className} 列表
     */
    List<${className}> query${className}s(${className} ${classNameLower});


}
