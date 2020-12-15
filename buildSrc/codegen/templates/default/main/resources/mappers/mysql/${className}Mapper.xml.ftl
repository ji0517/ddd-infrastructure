<#include "/macro.include.ftl" />
<#assign className = table.className>
<#assign classNameFirstLower = table.classNameFirstLower>   
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
<#macro mapperEl value>${r"#{"}<@uncapColumnName value.sqlName/>,jdbcType=${value.jdbcSqlTypeName}}</#macro>
<!--
<#include "/java_copyright.include.ftl">
<#include "/java_description.include.ftl">
-->
<mapper namespace="${basepackage}.dao.${className}Mapper">

	<resultMap id="RM${className}" type="${basepackage}.model.${className}">
        <#list table.columns as column>
        <result property="<@uncapColumnName column.sqlName/>" column="${column.sqlName}"/>
		</#list>
	</resultMap>
	
	<!-- 用于select查询公用抽取的列 -->
	<sql id="${className}Columns">
	    <![CDATA[
		<#list table.columns as column>T.${column.sqlName}<#if column_has_next>,</#if></#list>
	    ]]>
	</sql>

	<!-- useGeneratedKeys="true" keyProperty="xxx" for sqlserver and mysql  useGeneratedKeys="true" keyProperty="${table.idColumn.columnNameFirstLower}"-->
	<insert id="insert${className}" parameterType="${basepackage}.model.${className}">
    <![CDATA[
        INSERT INTO ${table.sqlName} (
        <#list table.columns as column> ${column.sqlName}<#if column_has_next>,</#if></#list>
        ) VALUES (
        <#list table.columns as column> <@mapperEl column/><#if column_has_next>,</#if></#list>
        )
    ]]>
		<!--	
			oracle: order="BEFORE" SELECT sequenceName.nextval AS ID FROM DUAL 
			DB2: order="BEFORE"" values nextval for sequenceName
		<selectKey resultType="java.lang.Long" order="BEFORE" keyProperty="userId">
			SELECT sequenceName.nextval AS ID FROM DUAL 
        </selectKey>
		-->
	</insert>
    
	<update id="update${className}" parameterType="${basepackage}.model.${className}">
    <![CDATA[
        UPDATE ${table.sqlName} SET
	        <#list table.notPkColumns as column>${column.sqlName} = <@mapperEl column/> <#if column_has_next>,</#if> </#list>
        WHERE 
        	<#list table.compositeIdColumns as column>${column.sqlName} = <@mapperEl column/> <#if column_has_next> AND </#if> </#list>
    ]]>
	</update>

    <delete id="delete${className}" parameterType="${basepackage}.model.${className}">
    <![CDATA[
        DELETE FROM ${table.sqlName} WHERE
        <#list table.compositeIdColumns as column>
        ${column.sqlName} = <@mapperEl column/> <#if column_has_next> AND </#if>
		</#list>
    ]]>
    </delete>

    
    <select id="query${className}" resultMap="RM${className}" parameterType="${basepackage}.model.${className}">
		SELECT <include refid="${className}Columns" />
	    <![CDATA[
		    FROM ${table.sqlName} T
	        WHERE 
				<#list table.compositeIdColumns as column>
		        T.${column.sqlName} = <@mapperEl column/> <#if column_has_next> AND </#if>
		        </#list>    
	    ]]>
	</select>


    <!--

    <select id="query${className}Count" resultType="long">
        SELECT count(*) FROM ${table.sqlName} T
		<where>
            1=1
	       <#list table.columns as column>
	       <if test="<@uncapColumnName column.sqlName/> != null and <@uncapColumnName column.sqlName/> != ''">
               AND T.${column.sqlName} = <@mapperEl column/>
           </if>
           </#list>
        </where>
    </select>

      -->

    <select id="query${className}s" resultMap="RM${className}" parameterType="${basepackage}.model.${className}">
        SELECT <include refid="${className}Columns" />
        FROM ${table.sqlName} T
        <where>
            1=1
	       <#list table.columns as column>
	       <if test="<@uncapColumnName column.sqlName/> != null and <@uncapColumnName column.sqlName/> != ''">
               AND T.${column.sqlName} = <@mapperEl column/>
           </if>
           </#list>
        </where>
    </select>

    
    <!--
    	分页查询已经使用Dialect进行分页,也可以不使用Dialect直接编写分页
    	因为分页查询将传 offset,pageSize,lastRows 三个参数,不同的数据库可以根于此三个参数属性应用不同的分页实现

    <select id="findPage${className}s" resultMap="RM${className}">
    	SELECT <include refid="${className}Columns" />
	    FROM ${table.sqlName} T
		<include refid="query${className}Where"/>
		
		<if test="@Ognl@isNotEmpty(sortColumns)">
			ORDER BY <@jspEl 'sortColumns'/>
		</if>
    </select>

    -->
	
</mapper>

