package ${package.Mapper};

import ${package.Entity}.${entity};
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;
<#-- 获取主键类型 -->
<#function getPrimaryKeyType fields>
    <#list fields as field>
        <#if field.keyFlag>
            <#return field.propertyType>
        </#if>
    </#list>
    <#return ""> <#-- 如果没有主键，返回空字符串 -->
</#function>

<#-- 主键类型 -->
<#assign pkType = getPrimaryKeyType(table.fields)>

<#-- 获取主键JAVA名称 -->
<#function getPrimaryKeyName fields>
    <#list fields as field>
        <#if field.keyFlag>
            <#return field.propertyName>
        </#if>
    </#list>
    <#return ""> <#-- 如果没有主键，返回空字符串 -->
</#function>

<#-- 主键JAVA名称 -->
<#assign pkName = getPrimaryKeyName(table.fields)>

/**
* @author ${author}
* @since ${date}
* ${table.comment!} Mapper 接口
*/
@Mapper
public interface ${table.mapperName} extends BaseMapper<${entity}> {

    /** 查询全部 */
    List<${entity}> selectAll();

    /** 根据 ID 查询 */
    ${entity} selectById(@Param("${pkName}") ${pkType} ${pkName});

    /** 插入一条 */
    int insertOne(${entity} bean);

    /** 根据 ID 更新 */
    int updateById(${entity} bean);

    /** 根据 ID 删除 */
    int deleteById(@Param("${pkName}") ${pkType} ${pkName});
}
