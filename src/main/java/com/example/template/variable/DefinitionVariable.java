package com.example.template.variable;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class DefinitionVariable {

    /**
     * 定义下放给模版的变量
     * @param author 作者
     * @param applicationName Application 名称
     * @param moduleName 模块名称
     * @param basePackage 基础包位置
     * @return 模板变量 Map
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
