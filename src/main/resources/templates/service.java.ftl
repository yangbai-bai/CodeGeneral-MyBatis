package ${package.Service};

import ${package.Entity}.${entity};
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


/**
* ${table.comment!} Service 接口
* @author ${author}
* @since ${date}
*/
public interface ${table.serviceName} {

    /** 查询全部 */
    List<${entity}> listAll();

    /** 根据 ID 查询 */
    ${entity} getById(${pkType} ${pkName});

    /** 新增 */
    int save(${entity} bean);

    /** 根据 ID 更新 */
    int updateById(${entity} bean);

    /** 根据 ID 删除 */
    int removeById(${pkType} ${pkName});
}