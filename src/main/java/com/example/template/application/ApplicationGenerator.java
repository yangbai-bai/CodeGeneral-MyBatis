package com.example.template.application;

import com.example.constant.GlobalPrintConstants;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

public class ApplicationGenerator {
    public static void generateApplication(Map<String, Object> data, String baseFilePath, Configuration cfg) throws IOException, TemplateException {
        if (data.get("applicationName") == null) {
            return;
        }
        Files.createDirectories(Paths.get(baseFilePath));
        String fileName = baseFilePath + data.get("applicationName") + "Application.java";
        try (Writer w = Files.newBufferedWriter(
                Paths.get(fileName),
                StandardCharsets.UTF_8)) {
            Template tpl = cfg.getTemplate("application.java.ftl");
            tpl.process(data, w);
        }
        System.out.println(GlobalPrintConstants.APPLICATION);
    }
}
