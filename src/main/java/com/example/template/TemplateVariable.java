package com.example.template;

import com.example.config.GeneratorConfig;
import com.example.enums.CorrespondEnum;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

/**
 * 模板变量类
 * <p>
 * 该类用于定义并返回下放给 FreeMarker 模板的变量集合，
 * 包含作者、日期、包名、模块名等基本信息。
 * </p>
 *
 * @author BaiJinBo
 * @since 2026-04-30
 */
public class TemplateVariable {

    /**
     * 创建模板变量
     * <p>
     * 构建包含代码生成所需基本信息的变量映射，包括作者名称、生成日期、
     * 包路径、模块名称和应用名称等。
     * </p>
     *
     * @param config 代码生成器配置
     * @return 模板变量 Map，包含所有下放给模板的变量
     */
    public static Map<String, Object> create(GeneratorConfig config) {
        Map<String, Object> data = new HashMap<>();
        data.put("author", config.getAuthor());
        data.put("date", LocalDate.now().toString());
        data.put("pkg", config.getPackagePath());
        data.put("basePkg", config.getBasePackage());
        data.put("module", config.getModuleName());
        data.put("applicationName", config.getApplicationName());
        data.put("className", config.getApplicationClassName());
        CorrespondEnum[] enums = CorrespondEnum.values();
        for (CorrespondEnum correspondEnum : enums) {
            String templateName = extractTemplateName(correspondEnum.getFileName());
            String classPath = convertToClassPath(correspondEnum.getFilePath());
            data.put(templateName, classPath);
        }
        return data;
    }


    /**
     * 从模板文件名中提取变量名
     *
     * @param fileName 模板文件名
     * @return 提取后的变量名
     */
    private static String extractTemplateName(String fileName) {
        return fileName.substring(fileName.lastIndexOf('/') + 1).split("\\.")[0];
    }

    /**
     * 将文件路径转换为类路径
     *
     * @param filePath 文件路径
     * @return 类路径格式的字符串
     */
    private static String convertToClassPath(String filePath) {
        return filePath.split("\\.")[0].replace("/", ".").substring(1);
    }
}
