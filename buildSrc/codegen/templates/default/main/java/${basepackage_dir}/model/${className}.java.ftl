/*
<#include "/macro.include.ftl"/>
<#include "/java_copyright.include.ftl">
 */
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
package ${basepackage}.model;

<#include "/java_imports.include.ftl">

/**
<#include "/java_description.include.ftl">
 */
public class ${className}  implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	//alias
	public static final String TABLE_ALIAS = "${table.tableAlias}";
	<#list table.columns as column>
	public static final String ALIAS_<@rmF_ column.sqlName/> = "${column.columnAlias}";
	</#list>
	
	//date formats
	<#list table.columns as column>
	<#if column.isDateTimeColumn>
	public static final String FORMAT_<@rmF_ column.sqlName/> = DATE_FORMAT;
	</#if>
	</#list>
	
	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
	<#list table.columns as column>
    /**
     * ${column.columnAlias!}       db_column: ${column.sqlName} <@uncapColumnName column.sqlName/>
     * ${column.hibernateValidatorExprssion}
     */
	private ${column.javaType} <@uncapColumnName column.sqlName/>;
	</#list>
	//columns END

<@generateConstructor className/>
<@generateJavaColumns/>
<@generateJavaOneToMany/>
<@generateJavaManyToOne/>

}

<#macro generateJavaColumns>
	<#list table.columns as column>
		<#if column.isDateTimeColumn>
	public String get<@capColumnName column.sqlName/>String() {
		return DateConvertUtils.format(get<@capColumnName column.sqlName/>(), FORMAT_${column.constantName});
	}
	public void set<@capColumnName column.sqlName/>String(String value) {
		set<@capColumnName column.sqlName/>(DateConvertUtils.parse(value, FORMAT_${column.constantName},${column.javaType}.class));
	}
	
		</#if>	
	public void set<@capColumnName column.sqlName/>(${column.javaType} value) {
		this.<@uncapColumnName column.sqlName/> = value;
	}
	
	public ${column.javaType} get<@capColumnName column.sqlName/>() {
		return this.<@uncapColumnName column.sqlName/>;
	}
	</#list>
</#macro>

<#macro generateJavaOneToMany>
	<#list table.exportedKeys.associatedTables?values as foreignKey>
	<#assign fkSqlTable = foreignKey.sqlTable>
	<#assign fkTable    = fkSqlTable.className>
	<#assign fkPojoClass = fkSqlTable.className>
	<#assign fkPojoClassVar = fkPojoClass?uncap_first>
	
	private Set ${fkPojoClassVar}s = new HashSet(0);
	public void set${fkPojoClass}s(Set<${fkPojoClass}> ${fkPojoClassVar}){
		this.${fkPojoClassVar}s = ${fkPojoClassVar};
	}
	
	public Set<${fkPojoClass}> get${fkPojoClass}s() {
		return ${fkPojoClassVar}s;
	}
	</#list>
</#macro>

<#macro generateJavaManyToOne>
	<#list table.importedKeys.associatedTables?values as foreignKey>
	<#assign fkSqlTable = foreignKey.sqlTable>
	<#assign fkTable    = fkSqlTable.className>
	<#assign fkPojoClass = fkSqlTable.className>
	<#assign fkPojoClassVar = fkPojoClass?uncap_first>
	
	private ${fkPojoClass} ${fkPojoClassVar};
	
	public void set${fkPojoClass}(${fkPojoClass} ${fkPojoClassVar}){
		this.${fkPojoClassVar} = ${fkPojoClassVar};
	}
	
	public ${fkPojoClass} get${fkPojoClass}() {
		return ${fkPojoClassVar};
	}
	</#list>
</#macro>
