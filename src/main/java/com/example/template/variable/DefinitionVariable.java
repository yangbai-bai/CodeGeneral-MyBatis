package com.example.template.variable;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

/**
 * 模板变量定义类
 * <p>
 * 该类用于定义并返回下放给 FreeMarker 模板的变量集合，
 * 包含作者、日期、包名、模块名等基本信息。
 * </p>
 *
 * @author BaiJinBo
 * @since 2026-04-30
 */
public class DefinitionVariable {

    /**
     * 定义下放给模版的变量
     * <p>
     * 构建包含代码生成所需基本信息的变量映射，包括作者名称、生成日期、
     * 包路径、模块名称和应用名称等。
     * </p>
     *
     * @param author         作者名称
     * @param applicationName Application 名称
     * @param moduleName     模块名称
     * @param basePackage    基础包位置
     * @return 模板变量 Map，包含所有下放给模板的变量
     */
    public static Map<String, Object> templateVariable(String author, String applicationName, String moduleName, String basePackage) {
        // 定义下放给模版的变量
        Map<String, Object> data = new HashMap<>();
        data.put("author", author);
        data.put("date", LocalDate.now().toString());
        data.put("pkg", basePackage + "." + moduleName);   // 改成 pkg
        data.put("basePkg", basePackage);
        data.put("module", moduleName);                   // com.example
        data.put("applicationName", applicationName);
        data.put("className", moduleName.substring(0, 1).toUpperCase() + moduleName.substring(1) + "Application");
        return data;
    }
}
