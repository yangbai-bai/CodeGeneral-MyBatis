<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="${package.Mapper}.${table.mapperName}">

    <!-- 基础字段映射 -->
    <resultMap id="BaseResultMap" type="${package.Entity}.${entity}">
<#list table.fields as field>
        <result column="${field.name}" property="${field.propertyName}"/>
</#list>
    </resultMap>

    <!-- 字段列表 -->
    <sql id="Base_Column_List">
<#list table.fields as field>
        ${field.name}<#if field_has_next>,</#if>
</#list>
    </sql>

    <!-- 查询全部 -->
    <select id="selectAll" resultMap="BaseResultMap">
        SELECT <include refid="Base_Column_List"/>
        FROM ${table.name}
        ORDER BY id DESC
    </select>

    <!-- 根据 ID 查询 -->
    <select id="selectById" parameterType="java.lang.Long" resultMap="BaseResultMap">
        SELECT <include refid="Base_Column_List"/>
        FROM ${table.name}
        WHERE
        <#list table.fields as field><#if field.keyFlag>${field.name} = #${r'{'}${field.propertyName}${r'}'}  <#break></#if></#list>
    </select>

    <!-- 插入一条 -->
    <insert id="insertOne" parameterType="${package.Entity}.${entity}" useGeneratedKeys="true" keyProperty="id">
        INSERT INTO ${table.name}
        <trim prefix="(" suffix=")" suffixOverrides=",">
<#list table.fields as field>
<#if field.propertyName != "id">
            ${field.name},
</#if>
</#list>
        </trim>
        VALUES
        <trim prefix="(" suffix=")" suffixOverrides=",">
<#list table.fields as field>
<#if field.propertyName != "id">
            #${r'{'}${field.propertyName}${r'}'},
</#if>
</#list>
        </trim>
    </insert>

    <!-- 根据 ID 更新 -->
    <update id="updateById" parameterType="${package.Entity}.${entity}">
        UPDATE ${table.name}
        <set>
<#list table.fields as field>
<#if field.propertyName != "id">
            <if test="${field.propertyName} != null">
                ${field.name} = #${r'{'}${field.propertyName}${r'}'},
            </if>
</#if>
</#list>
        </set>
        WHERE
        <#list table.fields as field><#if field.keyFlag>${field.name} = #${r'{'}${field.propertyName}${r'}'}  <#break></#if></#list>
    </update>
    <#assign hasPk = false>
    <#list table.fields as field>
        <#if field.keyFlag>
            <#assign hasPk = true>
            <#break>
        </#if>
    </#list>

    <!-- 根据 ID 删除 -->
<#if hasPk >
    <delete id="deleteById" parameterType="java.lang.Long">
        DELETE FROM
            ${table.name}
        WHERE
        <#list table.fields as field><#if field.keyFlag>${field.name} = #${r'{'}${field.propertyName}${r'}'}  <#break></#if></#list>
    </delete>
</#if>

</mapper>
