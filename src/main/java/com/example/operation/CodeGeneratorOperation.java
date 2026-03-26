package com.example.operation;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.example.constant.GlobalPrintConstants;
import com.example.database.DataBase;
import com.example.enums.CorrespondEnum;
import com.example.template.exception.ExceptionGeneral;
import com.example.utils.GeneralUtils;
import freemarker.template.Configuration;
import com.example.template.application.ApplicationGenerator;
import com.example.template.variable.DefinitionVariable;
import com.example.CodeGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public record CodeGeneratorOperation(String applicationName, String author, String moduleName, String basePackage,
                                     String outDir, String password, String username, String jdbcUrl, Boolean isGeneralGlobalVo, Boolean isGeneralGlobalException, Boolean isGeneralGlobalEnums) {


//    private static final Logger log = LoggerFactory.getLogger(CodeGeneratorOperation.class);

    public void start() throws Exception {
        String baseFilePath = Paths.get(outDir, CorrespondEnum.baseFilePath.getFilePath(),
                basePackage.replace(".", "/"),
                moduleName)+ "/";
        // ====== 连接数据库，获取所有的数据库表名 ======
        List<String> tableNames = DataBase.getAllTableNames(jdbcUrl, username, password);
        // ====== 定义下放给模版的变量 =======
        Map<String, Object> data = DefinitionVariable.templateVariable(author, applicationName, moduleName, basePackage);
        create(jdbcUrl, username, password, author, outDir, basePackage, moduleName, tableNames, baseFilePath);
        // ====== 配置模板规则 ======
        Configuration cfg = templateRule();
        // 创建 VO
        if (isGeneralGlobalVo) {
            GeneralUtils.generateExtraFile(data, baseFilePath + CorrespondEnum.GlobalVo.getFilePath(), cfg, GlobalPrintConstants.GLOBAL_VO, CorrespondEnum.GlobalVo.getFileName());
        }
        // 创建全局异常
        if (isGeneralGlobalException) {
            ExceptionGeneral.generalException(data, baseFilePath, cfg);
        }
        // 创通用返回项 Enum
        if (isGeneralGlobalEnums) {
            GeneralUtils.generateExtraFile(data, baseFilePath + CorrespondEnum.Response_Enums.getFilePath(), cfg, GlobalPrintConstants.Response_Enums ,CorrespondEnum.Response_Enums.getFileName());
        }
        ApplicationGenerator.generateApplication(data, baseFilePath, cfg);
        System.out.println(GlobalPrintConstants.FINALLY + outDir.replace("/", "\\"));
    }

    /**
     * 设置模板规则（复用 com.example.CodeGenerator 类的类加载器）
     *
     * @return Freemarker 配置类
     */
    private Configuration templateRule() {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_31);
        // 关键：仍然使用 com.example.CodeGenerator 类的类加载器，与方法所在类无关
        cfg.setClassLoaderForTemplateLoading(CodeGenerator.class.getClassLoader(), "templates");
        cfg.setDefaultEncoding("UTF-8");
        return cfg;
    }

    private void create(String jdbcUrl, String username, String password, String author, String outDir, String basePackage, String moduleName, List<String> tableNames, String baseFilePath) {
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
