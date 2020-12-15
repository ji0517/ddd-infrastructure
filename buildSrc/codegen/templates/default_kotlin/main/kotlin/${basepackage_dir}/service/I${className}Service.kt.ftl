<#include "/macro.include.ftl" />
/*
<#include "/java_copyright.include.ftl">
 */
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>   
package ${basepackage}.service

<#include "/java_imports.include.ftl">
import ${basepackage}.model.${className}
import com.github.pagehelper.Page
/**
<#include "/java_description.include.ftl">
 */
interface I${className}Service {


    /**
     * 新增 ${className}
     * @param ${classNameLower}
     * @return Int
     */
    fun insert${className}(${classNameLower}:${className}): Int

    /**
     * 更新 ${className}
     * @param ${classNameLower}
     * @return Int
     */
    fun update${className}(${classNameLower}:${className}): Int

    /**
     * 主键删除 ${className}
<#list table.compositeIdColumns as column>
     * @param <@uncapColumnName column.sqlName/> ${column.columnAlias}
 </#list>
     * @return Int
     */
    fun delete${className}ByKeys(<#list table.compositeIdColumns as column> <@uncapColumnName column.sqlName/> : ${column.javaType}<#if column_has_next>,</#if></#list>): Int


    /**
     * 主键逻辑删除 ${className}
<#list table.compositeIdColumns as column>
     * @param <@uncapColumnName column.sqlName/> ${column.columnAlias}
</#list>
     * @return Int
     */
    fun delete${className}Logically(<#list table.compositeIdColumns as column> <@uncapColumnName column.sqlName/> : ${column.javaType}<#if column_has_next>,</#if></#list>): Int

    /**
     * 主键查询 ${className}
<#list table.compositeIdColumns as column>
     * @param <@uncapColumnName column.sqlName/> ${column.columnAlias}
</#list>
     * @return ${className}
     */
    fun query${className}ByKeys(<#list table.compositeIdColumns as column><@uncapColumnName column.sqlName/> : ${column.javaType}<#if column_has_next>,</#if></#list>): ${className}


    //fun query${className}Count(${classNameLower}:${className}) : Int

    /**
     * 查询 ${className}  通过分页组件 拦截sql分页
     * @param ${classNameLower}  传查询条件
     * @param index 当前页索引
     * @param size  当前页记录数
     * @return ${className} 列表
     */
    fun query${className}s( ${classNameLower} :${className}, index :Int, size :Int) : Page<${className}>


}
