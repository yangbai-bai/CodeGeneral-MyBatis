package com.example.utils;

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
     * @param data 下放的模版变量
     * @param outDir 输出的目录
     * @param cfg 模板配置
     * @param msg 消息
     * @param templateName 模版名称
     * @throws IOException IO 异常
     * @throws freemarker.template.TemplateException 模板异常
     */
    public static void generateExtraFile(Map<String, Object> data, String outDir, Configuration cfg, String msg, String templateName) throws IOException, freemarker.template.TemplateException {
        Path outPath = Paths.get(outDir);
        Files.createDirectories(outPath.getParent());
        try (Writer w = Files.newBufferedWriter(outPath, StandardCharsets.UTF_8)) {
            Template tpl = cfg.getTemplate(templateName);
            tpl.process(data, w);
        }
        System.out.println(msg);
    }
}
