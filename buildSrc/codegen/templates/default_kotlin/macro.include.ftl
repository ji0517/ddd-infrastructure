<#-- 本文件包含一些公共的函数,本文件会被其它模板自动include -->

<#assign dollar = '$'> 

<#-- 将value变成jsp el表达式,主要由于FreeMarker生成表达式不方便 -->
<#macro jspEl value>${r"${"}${value}}</#macro>

<#-- 生成java构造函数 -->
<#macro generateConstructor constructor>
	public ${constructor}(){
	}

	public ${constructor}(
	<#list table.compositeIdColumns as column>
		${column.javaType} <@uncapColumnName column.sqlName/> <#if column_has_next>,</#if>
	</#list>		
	){
	<#list table.compositeIdColumns as column>
		<#if column.pk>
		this.<@uncapColumnName column.sqlName/>  = <@uncapColumnName column.sqlName/> ;
		</#if>
	</#list>	
	}

</#macro>


<#-- F_USER_STATE  ==>  UserState -->
<#macro capColumnName value>${value?replace("_"," ")?capitalize?replace("F ","")?replace(" ","")}</#macro>


<#-- F_USER_STATE  ==>  UserState -->
<#macro uncapColumnName value>${value?replace("_"," ")?capitalize?replace("F ","")?replace(" ","")?uncap_first}</#macro>

<#-- F_USER_STATE  ==>  USER_STATE -->
<#macro rmF_ value>${value?replace("F_","")}</#macro>




