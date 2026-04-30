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

public class GeneralUtils {

    /**
     * 通用生成额外代码
     * @throws IOException IO 异常
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
     * 设置模板规则（复用 com.example.CodeGenerator 类的类加载器）
     *
     * @return Freemarker 配置类
     */
    private static Configuration templateRule() {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_31);
        // 关键：仍然使用 com.example.CodeGenerator 类的类加载器，与方法所在类无关
        cfg.setClassLoaderForTemplateLoading(CodeGenerator.class.getClassLoader(), "templates");
        cfg.setDefaultEncoding("UTF-8");
        return cfg;
    }

}
