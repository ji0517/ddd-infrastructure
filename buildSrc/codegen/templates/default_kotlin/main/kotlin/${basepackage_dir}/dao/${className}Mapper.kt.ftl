/*
<#include "/java_copyright.include.ftl">
 */
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>   
package ${basepackage}.dao

<#include "/java_imports.include.ftl">
import ${basepackage}.model.${className}
<#--import ${basepackage}.dao.*-->

import org.apache.ibatis.annotations.Mapper

/**
<#include "/java_description.include.ftl">
 */
@Mapper
interface ${className}Mapper {


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
     * @param ${classNameLower} 传主键
     * @return Int
     */
    fun delete${className}(${classNameLower}:${className}): Int


    /**
     * 主键逻辑删除 ${className}
     * @param ${classNameLower} 传主键
     * @return Int
     */
    fun delete${className}Logically(${classNameLower}:${className}): Int


    /**
     * 主键查询 ${className}
     * @param ${classNameLower}  传主键
     * @return ${className}
     */
    fun query${className}(${classNameLower}:${className}): ${className}


    //fun query${className}Count(${className} ${classNameLower}): Int

    /**
     * 查询 ${className}  通过分页组件 拦截sql分页
     * @param ${classNameLower}  传查询条件
     * @return ${className} 列表
     */
    fun query${className}s(${classNameLower}:${className}): List<${className}>


}
