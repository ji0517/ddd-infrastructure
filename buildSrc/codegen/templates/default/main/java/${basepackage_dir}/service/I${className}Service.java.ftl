<#include "/macro.include.ftl" />
/*
<#include "/java_copyright.include.ftl">
 */
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>   
package ${basepackage}.service;

<#include "/java_imports.include.ftl">
import ${basepackage}.model.${className};

/**
<#include "/java_description.include.ftl">
 */
public interface I${className}Service {


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
<#list table.compositeIdColumns as column>
     * @param <@uncapColumnName column.sqlName/> ${column.columnAlias}
 </#list>
     * @return int
     */
    int delete${className}ByKeys(<#list table.compositeIdColumns as column>${column.javaType} <@uncapColumnName column.sqlName/><#if column_has_next>,</#if></#list>);

    /**
     * 主键查询 ${className}
<#list table.compositeIdColumns as column>
     * @param <@uncapColumnName column.sqlName/> ${column.columnAlias}
</#list>
     * @return ${className}
     */
    ${className} query${className}ByKeys(<#list table.compositeIdColumns as column>${column.javaType} <@uncapColumnName column.sqlName/><#if column_has_next>,</#if></#list>);


    //int query${className}Count(${className} ${classNameLower});

    /**
     * 查询 ${className}  通过分页组件 拦截sql分页
     * @param ${classNameLower}  传查询条件
     * @param index 当前页索引
     * @param size  当前页记录数
     * @return ${className} 列表
     */
    List<${className}> query${className}s(${className} ${classNameLower},Integer index,Integer size);


}
