package com.example.template;

import com.example.config.GeneratorConfig;
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
 * 模板引擎类
 * <p>
 * 该类提供 FreeMarker 模板引擎的相关操作，用于根据模板文件
 * 生成额外的代码文件。
 * </p>
 *
 * @author BaiJinBo
 * @since 2026-04-30
 */
public class TemplateEngine {

    private static final Configuration CONFIG = initConfiguration();

    /**
     * 初始化 FreeMarker 配置
     *
     * @return 配置好的 FreeMarker 配置对象
     */
    private static Configuration initConfiguration() {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_31);
        cfg.setClassLoaderForTemplateLoading(GeneratorConfig.class.getClassLoader(), "templates");
        cfg.setDefaultEncoding("UTF-8");
        return cfg;
    }

    /**
     * 根据配置生成文件
     *
     * @param templateData 模板数据模型
     * @param fileEnum     文件映射枚举
     * @param baseFilePath 基础文件路径
     * @throws IOException        IO 异常
     * @throws freemarker.template.TemplateException 模板异常
     */
    public static void generateFile(Map<String, Object> templateData, CorrespondEnum fileEnum, String baseFilePath) throws IOException, freemarker.template.TemplateException {
        Path outPath = Paths.get(baseFilePath + fileEnum.getFilePath());

        Files.createDirectories(outPath.getParent());

        try (Writer writer = Files.newBufferedWriter(outPath, StandardCharsets.UTF_8)) {
            Template template = CONFIG.getTemplate(fileEnum.getFileName());
            template.process(templateData, writer);
        }

        if (!fileEnum.getFinishLog().isEmpty()) {
            System.out.println(fileEnum.getFinishLog());
        }
    }
}
