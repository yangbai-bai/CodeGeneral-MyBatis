package com.example.core;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.example.config.GeneratorConfig;
import com.example.constant.GlobalPrintConstants;
import com.example.enums.CorrespondEnum;
import com.example.template.TemplateEngine;
import com.example.template.TemplateVariable;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * 代码生成操作类
 * <p>
 * 该类是代码生成的核心操作类，使用 MyBatis-Plus 的 FastAutoGenerator
 * 进行代码生成，包括 Entity、Mapper、Service、Controller 等基础代码，
 * 同时还负责生成额外的通用类文件。
 * </p>
 *
 * @author BaiJinBo
 * @since 2026-04-30
 */
public class CodeGeneratorOperation {

    private final GeneratorConfig config;

    /**
     * 构造函数
     *
     * @param config 代码生成器配置
     */
    public CodeGeneratorOperation(GeneratorConfig config) {
        this.config = config;
    }

    /**
     * 开始执行代码生成
     * <p>
     * 该方法首先连接数据库获取所有表名，然后使用 MyBatis-Plus 生成器
     * 生成基础代码，最后生成额外的通用类文件。
     * </p>
     *
     * @throws Exception 如果代码生成过程中发生异常
     */
    public void start() throws Exception {
        List<String> tableNames = DataBase.getAllTableNames(
                config.getJdbcUrl(),
                config.getUsername(),
                config.getPassword());

        create(tableNames);
        generateExtraFiles();

        System.out.println(GlobalPrintConstants.FINALLY + config.getOutDir().replace("/", "\\"));
    }

    /**
     * 执行代码生成
     * <p>
     * 使用 MyBatis-Plus 的 FastAutoGenerator 配置并执行代码生成，
     * 生成 Entity、Mapper、Service、Controller 等基础代码。
     * </p>
     *
     * @param tableNames 要生成代码的表名列表
     */
    private void create(List<String> tableNames) {
        FastAutoGenerator.create(config.getJdbcUrl(), config.getUsername(), config.getPassword())
                .globalConfig(builder -> builder.author(config.getAuthor())
                        .outputDir(config.getOutDir() + "/src/main/java")
                        .disableOpenDir())
                .packageConfig(builder -> builder.parent(config.getBasePackage())
                        .moduleName(config.getModuleName())
                        .pathInfo(Collections.singletonMap(OutputFile.xml,
                                config.getOutDir() + "/src/main/resources/mapper")))
                .strategyConfig(builder -> builder
                        .addInclude(tableNames)
                        .addTablePrefix("t_", "sys_")
                        .entityBuilder()
                        .enableFileOverride()
                        .enableLombok()
                        .disableSerialVersionUID()
                        .mapperBuilder()
                        .enableBaseResultMap()
                        .enableBaseColumnList()
                        .enableFileOverride()
                        .serviceBuilder()
                        .enableFileOverride()
                        .controllerBuilder()
                        .enableFileOverride()
                        .enableRestStyle()
                )
                .templateConfig(builder -> builder
                        .mapper("/templates/mybatis/mapper.java")
                        .xml("/templates/mybatis/mapper.xml")
                        .service("/templates/mybatis/service.java")
                        .serviceImpl("/templates/mybatis/serviceImpl.java")
                )
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();
    }

    /**
     * 生成额外的通用类文件
     * <p>
     * 根据模板变量和文件映射枚举，生成 GlobalVo、异常类、Redis配置等额外文件。
     * </p>
     *
     * @throws Exception 如果生成过程中发生异常
     */
    private void generateExtraFiles() throws Exception {
        Map<String, Object> templateData = TemplateVariable.create(config);
        for (CorrespondEnum correspondEnum : CorrespondEnum.values()) {
            TemplateEngine.generateFile(templateData, correspondEnum, config.getBaseFilePath());
        }
    }
}
