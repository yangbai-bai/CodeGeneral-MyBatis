package ${package.Entity};

import com.baomidou.mybatisplus.annotation.*;
import java.io.Serial;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
<#list table.importPackages as pkg>
import ${pkg};
</#list>

/**
* ${table.comment!}
*
* @author ${author}
* @since ${date}
*/
@Getter
@Setter
@TableName("${table.name}")
public class ${entity} implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

<#-- 遍历字段 -->
<#list table.fields as field>
    /**
    * ${field.comment!}
    */
    <#if field.keyFlag>
    @TableId(value = "${field.name}", type = IdType.AUTO)
    <#elseif field.fill??>
    @TableField(value = "${field.name}", fill = FieldFill.${field.fill})
    <#else>
    @TableField("${field.name}")
    </#if>
    private ${field.propertyType} ${field.propertyName};

</#list>
}
