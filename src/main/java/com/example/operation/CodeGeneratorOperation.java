package com.example.operation;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.example.constant.GlobalPrintConstants;
import com.example.database.DataBase;
import com.example.enums.CorrespondEnum;
import com.example.utils.GeneralUtils;
import com.example.template.variable.DefinitionVariable;
import com.example.CodeGenerator;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public record CodeGeneratorOperation(String applicationName, String author, String moduleName, String basePackage,
                                     String outDir, String password, String username, String jdbcUrl) {


//    private static final Logger log = LoggerFactory.getLogger(CodeGeneratorOperation.class);

    public void start() throws Exception {
        // ====== 连接数据库，获取所有的数据库表名 ======
        List<String> tableNames = DataBase.getAllTableNames(jdbcUrl, username, password);
        // 生成基础类
        create(jdbcUrl, username, password, author, outDir, basePackage, moduleName, tableNames);
        // 下放模板变量
        CorrespondEnum[] enums = CorrespondEnum.values();
        Map<String, Object> data = DefinitionVariable.templateVariable(CodeGenerator.author, CodeGenerator.applicationName, CodeGenerator.moduleName, CodeGenerator.basePackage);
        for(CorrespondEnum correspondEnum: enums) {
            data.put(changeTemplateName(correspondEnum.getFileName()), changePathToClassPath(correspondEnum.getFilePath()));
        }
        System.out.println(GlobalPrintConstants.VARIABLE);
        // 生成额外单个类
        for(CorrespondEnum correspondEnum: CorrespondEnum.values()) {
            GeneralUtils.generateExtraFile(data, correspondEnum);
        }
        System.out.println(GlobalPrintConstants.FINALLY + outDir.replace("/", "\\"));
    }

    // 控制模板变量
    private static String changeTemplateName(String fileName) {
        // 1. 取路径最后一段（文件名部分）
        return fileName.substring(fileName.lastIndexOf('/') + 1).split("\\.")[0];
    }

    // 将路径中的 / 替换成 .
    private static String changePathToClassPath(String filePath) {
        return filePath.split("\\.")[0].replace("/", ".").substring(1);
    }

    private void create(String jdbcUrl, String username, String password, String author, String outDir, String basePackage, String moduleName, List<String> tableNames) {
        FastAutoGenerator.create(jdbcUrl, username, password)
                .globalConfig(builder -> builder.author(author)
                        .outputDir(outDir + "/src/main/java")
                        .disableOpenDir())
                .packageConfig(builder -> builder.parent(basePackage)
                        .moduleName(moduleName)
                        .pathInfo(Collections.singletonMap(OutputFile.xml,
                                outDir + "/src/main/resources/mapper")))
                .strategyConfig(builder -> builder
                        .addInclude(tableNames)
                        .addTablePrefix("t_", "sys_")
                        // Entity
                        .entityBuilder()
                        .enableFileOverride()
                        .enableLombok()
                        .disableSerialVersionUID()
                        // Mapper
                        .mapperBuilder()
                        .enableBaseResultMap()
                        .enableBaseColumnList()
                        .enableFileOverride()
                        // Service
                        .serviceBuilder()
                        .enableFileOverride()
                        // Controller
                        .controllerBuilder()
                        .enableFileOverride()
                        .enableRestStyle()
                )
                .templateConfig(builder -> builder.mapper("/templates/mapper.java")
                        .xml("/templates/mapper.xml")
                        .service("/templates/service.java") // 自定义 Service 接口模板
                        .serviceImpl("/templates/serviceImpl.java") // 自定义 ServiceImpl 模板
                )

                .templateEngine(new FreemarkerTemplateEngine())
                .execute();
    }
}
