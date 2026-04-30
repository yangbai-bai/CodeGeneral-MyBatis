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
public record CodeGeneratorOperation(String applicationName, String author, String moduleName, String basePackage,
                                     String outDir, String password, String username, String jdbcUrl) {


//    private static final Logger log = LoggerFactory.getLogger(CodeGeneratorOperation.class);

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

    /**
     * 控制模板变量名称
     * <p>
     * 从模板文件名中提取变量名，用于在数据模型中作为 key。
     * </p>
     *
     * @param fileName 模板文件名
     * @return 提取后的变量名
     */
    private static String changeTemplateName(String fileName) {
        // 1. 取路径最后一段（文件名部分）
        return fileName.substring(fileName.lastIndexOf('/') + 1).split("\\.")[0];
    }

    /**
     * 将文件路径转换为类路径
     * <p>
     * 将文件系统路径中的目录分隔符替换为 Java 包的分隔符。
     * </p>
     *
     * @param filePath 文件路径
     * @return 类路径格式的字符串
     */
    private static String changePathToClassPath(String filePath) {
        return filePath.split("\\.")[0].replace("/", ".").substring(1);
    }

    /**
     * 执行代码生成
     * <p>
     * 使用 MyBatis-Plus 的 FastAutoGenerator 配置并执行代码生成，
     * 生成 Entity、Mapper、Service、Controller 等基础代码。
     * </p>
     *
     * @param jdbcUrl     JDBC 数据库连接 URL
     * @param username    数据库用户名
     * @param password    数据库密码
     * @param author      作者名称
     * @param outDir      输出目录
     * @param basePackage 基础包名
     * @param moduleName  模块名称
     * @param tableNames  要生成代码的表名列表
     */
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
