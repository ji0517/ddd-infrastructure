<#include "/macro.include.ftl" />
/*
<#include "/java_copyright.include.ftl">
 */
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>   
package ${basepackage}.service.impl

<#include "/java_imports.include.ftl">
import org.apache.logging.log4j.LogManager
import com.github.pagehelper.Page
import com.github.pagehelper.PageHelper
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import javax.annotation.Resource

import ${basepackage}.model.${className}
import ${basepackage}.dao.${className}Mapper
import ${basepackage}.service.I${className}Service

/**
<#include "/java_description.include.ftl">
 */
@Service
class ${className}ServiceImpl : I${className}Service {

    private val logger = LogManager.getLogger(${className}ServiceImpl::class.java)

    @Resource
    lateinit var ${classNameLower}Mapper: ${className}Mapper

    /**
     * 新增 ${className}
     * @param ${classNameLower}
     * @return Int
     */
    override fun insert${className}(${classNameLower} :${className}): Int {
        return ${classNameLower}Mapper.insert${className}(${classNameLower})
    }

    /**
     * 更新 ${className}
     * @param ${classNameLower}
     * @return Int
     */
    override fun update${className}(${classNameLower} :${className}) : Int{
        return ${classNameLower}Mapper.update${className}(${classNameLower})
    }

    /**
     * 主键删除 ${className}
<#list table.compositeIdColumns as column>
     * @param <@uncapColumnName column.sqlName/> ${column.columnAlias}
 </#list>
     * @return Int
     */
    override fun delete${className}ByKeys(<#list table.compositeIdColumns as column><@uncapColumnName column.sqlName/> : ${column.javaType} <#if column_has_next>,</#if></#list>) : Int{
        var ${classNameLower} = ${className}()
<#list table.compositeIdColumns as column>
        ${classNameLower}.<@uncapColumnName column.sqlName/> = <@uncapColumnName column.sqlName/>
</#list>
        return ${classNameLower}Mapper.delete${className}(${classNameLower})
    }

    /**
     * 主键逻辑删除 ${className}
<#list table.compositeIdColumns as column>
     * @param <@uncapColumnName column.sqlName/> ${column.columnAlias}
</#list>
     * @return Int
     */
    override fun delete${className}Logically(<#list table.compositeIdColumns as column><@uncapColumnName column.sqlName/> : ${column.javaType} <#if column_has_next>,</#if></#list>) : Int{
        var ${classNameLower} = ${className}()
<#list table.compositeIdColumns as column>
        ${classNameLower}.<@uncapColumnName column.sqlName/> = <@uncapColumnName column.sqlName/>
</#list>
        return ${classNameLower}Mapper.delete${className}Logically(${classNameLower})
    }

    /**
     * 主键查询 ${className}
<#list table.compositeIdColumns as column>
     * @param <@uncapColumnName column.sqlName/> ${column.columnAlias}
</#list>
     * @return ${className}
     */
    override fun query${className}ByKeys(<#list table.compositeIdColumns as column><@uncapColumnName column.sqlName/> : ${column.javaType} <#if column_has_next>,</#if></#list>) : ${className}  {
        var ${classNameLower} = ${className}()
<#list table.compositeIdColumns as column>
        ${classNameLower}.<@uncapColumnName column.sqlName/> = <@uncapColumnName column.sqlName/>
</#list>
        return ${classNameLower}Mapper.query${className}(${classNameLower})
    }


    //int query${className}Count(${className} ${classNameLower})

    /**
     * 查询 ${className}  通过分页组件 拦截sql分页
     * @param ${classNameLower}  传查询条件
     * @param index 当前页索引
     * @param size  当前页记录数
     * @return ${className} 列表
     */
    override fun query${className}s(${classNameLower} : ${className} , index :Int, size :Int) : Page<${className}>{
        var page = PageHelper.startPage<${className}>(index, size)
        ${classNameLower}Mapper.query${className}s(${classNameLower})
        return page
    }


}
