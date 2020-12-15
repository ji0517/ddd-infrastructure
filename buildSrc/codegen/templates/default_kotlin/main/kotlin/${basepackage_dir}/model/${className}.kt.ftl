/*
<#include "/macro.include.ftl"/>
<#include "/java_copyright.include.ftl">
 */
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
package ${basepackage}.model

<#include "/java_imports.include.ftl">

/**
<#include "/java_description.include.ftl">
 */
class ${className} : java.io.Serializable{

    companion object {
        private const val serialVersionUID = 5990939387657237756L
    }
	
	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
	<#list table.columns as column>
    /**
     * ${column.columnAlias!}       db_column: ${column.sqlName} <@uncapColumnName column.sqlName/>
     * ${column.hibernateValidatorExprssion}
     */
	 var <@uncapColumnName column.sqlName/> : ${column.javaType}? = null
	</#list>
	//columns END


}
