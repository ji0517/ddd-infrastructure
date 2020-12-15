<#include "/macro.include.ftl" />
/*
<#include "/java_copyright.include.ftl">
 */
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>   
package ${basepackage}.service.impl;

<#include "/java_imports.include.ftl">
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import ${basepackage}.model.${className};
import ${basepackage}.dao.${className}Mapper;
import ${basepackage}.service.I${className}Service;

/**
<#include "/java_description.include.ftl">
 */
@Service
public class ${className}ServiceImpl implements I${className}Service {

    private final static org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(${className}ServiceImpl.class);

    @Resource
    ${className}Mapper ${classNameLower}Mapper;

    /**
     * 新增 ${className}
     * @param ${classNameLower}
     * @return int
     */
    @Override
    public int insert${className}(${className} ${classNameLower}) {
        return ${classNameLower}Mapper.insert${className}(${classNameLower});
    }

    /**
     * 更新 ${className}
     * @param ${classNameLower}
     * @return int
     */
    @Override
    public int update${className}(${className} ${classNameLower}){
        return ${classNameLower}Mapper.update${className}(${classNameLower});
    }

    /**
     * 主键删除 ${className}
<#list table.compositeIdColumns as column>
     * @param <@uncapColumnName column.sqlName/> ${column.columnAlias}
 </#list>
     * @return int
     */
    @Override
    public int delete${className}ByKeys(<#list table.compositeIdColumns as column>${column.javaType} <@uncapColumnName column.sqlName/><#if column_has_next>,</#if></#list>) {
        ${className} ${classNameLower} = new ${className}();
<#list table.compositeIdColumns as column>
        ${classNameLower}.set<@capColumnName column.sqlName/>(<@uncapColumnName column.sqlName/>);
</#list>
        return ${classNameLower}Mapper.delete${className}(${classNameLower});
    }

    /**
     * 主键查询 ${className}
<#list table.compositeIdColumns as column>
     * @param <@uncapColumnName column.sqlName/> ${column.columnAlias}
</#list>
     * @return ${className}
     */
    @Override
    public ${className} query${className}ByKeys(<#list table.compositeIdColumns as column>${column.javaType} <@uncapColumnName column.sqlName/><#if column_has_next>,</#if></#list>) {
        ${className} ${classNameLower} = new ${className}();
        <#list table.compositeIdColumns as column>
        ${classNameLower}.set<@capColumnName column.sqlName/>(<@uncapColumnName column.sqlName/>);
        </#list>
        return ${classNameLower}Mapper.query${className}(${classNameLower});
    }


    //int query${className}Count(${className} ${classNameLower});

    /**
     * 查询 ${className}  通过分页组件 拦截sql分页
     * @param ${classNameLower}  传查询条件
     * @param index 当前页索引
     * @param size  当前页记录数
     * @return ${className} 列表
     */
    @Override
    public List<${className}> query${className}s(${className} ${classNameLower},Integer index,Integer size) {
        PageHelper.startPage(index, size);
        return ${classNameLower}Mapper.query${className}s(${classNameLower});
    }


}
