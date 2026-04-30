package ${package.ServiceImpl};

import ${package.Entity}.${entity};
import ${package.Mapper}.${table.mapperName};
import ${package.Service}.${table.serviceName};
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import java.util.List;

<#-- 工具函数：主键类型 -->
<#function getPrimaryKeyType fields>
    <#list fields as field>
        <#if field.keyFlag><#return field.propertyType></#if>
    </#list>
    <#return "">
</#function>
<#assign pkType = getPrimaryKeyType(table.fields)>

<#-- 工具函数：主键 java 名 -->
<#function getPrimaryKeyName fields>
    <#list fields as field>
        <#if field.keyFlag><#return field.propertyName></#if>
    </#list>
    <#return "">
</#function>
<#assign pkName = getPrimaryKeyName(table.fields)>

<#assign mapperBeanName = "${table.entityName?uncap_first}Mapper">

/**
* ${table.comment!} Service 实现
* @author ${author}
* @since ${date}
*/
@Service
public class ${table.serviceImplName} implements ${table.serviceName} {

    @Resource
    private ${table.mapperName} ${mapperBeanName};

    @Override
    public List<${entity}> listAll() {
        return ${table.entityName?uncap_first}Mapper.selectAll();
    }

    @Override
    public ${entity} getById(${pkType} ${pkName}) {
        return ${table.entityName?uncap_first}Mapper.selectById(${pkName});
    }

    @Override
    public int save(${entity} bean) {
        return ${table.entityName?uncap_first}Mapper.insertOne(bean);
    }

    @Override
    public int updateById(${entity} bean) {
        return ${table.entityName?uncap_first}Mapper.updateById(bean);
    }

    @Override
    public int removeById(${pkType} ${pkName}) {
        return ${table.entityName?uncap_first}Mapper.deleteById(${pkName});
    }
}