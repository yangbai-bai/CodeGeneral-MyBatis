package com.example.utils;

import com.example.CodeGenerator;
import com.example.enums.CorrespondEnum;
import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.IOException;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

/**
 * 通用工具类
 * <p>
 * 该类提供代码生成过程中使用的通用工具方法，包括使用 FreeMarker
 * 模板引擎生成额外的代码文件等。
 * </p>
 *
 * @author BaiJinBo
 * @since 2026-04-30
 */
public class GeneralUtils {

    /**
     * 通用生成额外代码
     * <p>
     * 根据指定的文件映射枚举和模板数据，使用 FreeMarker 模板引擎
     * 生成额外的代码文件。
     * </p>
     *
     * @param data 模板数据模型
     * @param file 文件映射枚举，包含文件路径、模板文件名和完成日志
     * @throws IOException        IO 异常
     * @throws freemarker.template.TemplateException 模板异常
     */
    public static void generateExtraFile(Map<String, Object> data ,CorrespondEnum file) throws IOException, freemarker.template.TemplateException {
        Path outPath = Paths.get(CodeGenerator.baseFilePath + file.getFilePath());
        /* 配置模板规则 */
        Configuration cfg = templateRule();
        Files.createDirectories(outPath.getParent());
        try (Writer w = Files.newBufferedWriter(outPath, StandardCharsets.UTF_8)) {
            Template tpl = cfg.getTemplate(file.getFileName());
            tpl.process(data, w);
        }
        if (!file.getFinishLog().isEmpty()) {
            System.out.println(file.getFinishLog());
        }
    }

    /**
     * 设置模板规则
     * <p>
     * 配置 FreeMarker 模板引擎的加载规则，使用 CodeGenerator 类的类加载器
     * 从 classpath 下的 templates 目录加载模板文件。
     * </p>
     *
     * @return 配置好的 Freemarker 配置类
     */
    private static Configuration templateRule() {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_31);
        // 关键：仍然使用 com.example.CodeGenerator 类的类加载器，与方法所在类无关
        cfg.setClassLoaderForTemplateLoading(CodeGenerator.class.getClassLoader(), "templates");
        cfg.setDefaultEncoding("UTF-8");
        return cfg;
    }

}
