package com.example;

import com.example.operation.CodeGeneratorOperation;

import java.nio.file.Paths;
import java.util.*;

/**
 * MyBatis 代码生成器主入口类
 * <p>
 * 该类负责配置代码生成所需的基本参数，包括数据库连接信息、
 * 输出目录、包路径、模块名称等，并提供代码生成的启动入口。
 * </p>
 *
 * @author BaiJinBo
 * @since 2026-04-30
 */
public class CodeGenerator {

    /** JDBC 数据库连接 URL */
    public static String jdbcUrl = "jdbc:mysql://localhost:3306/order_system";
    /** 数据库用户名 */
    public static String username = "root";
    /** 数据库密码 */
    public static String password = "Bai159357";
    /** 代码输出根目录 */
    public static String outDir = "F:/WorkSpace/JavaProjects/CodeGeneration/OrderSystem";
    /** 基础包名 */
    public static String basePackage = "com.yangbaibai";
    /** 模块名称 */
    public static String moduleName = "ordersystem";
    /** 作者名称 */
    public static String author = "BaiJinBo";
    /** 应用名称 */
    public static String applicationName = "OrderSystem";
    /** 基础文件路径 */
    public static String baseFilePath = Paths.get(CodeGenerator.outDir,"src/main/java",
            CodeGenerator.basePackage.replace(".", "/"),
            CodeGenerator.moduleName)+ "/";


    /**
     * 代码生成器主启动方法
     * <p>
     * 初始化代码生成操作实例并执行代码生成流程。
     * </p>
     *
     * @param args 命令行参数（当前未使用）
     * @throws Exception 如果代码生成过程中发生异常
     */
    public static void main(String[] args) throws Exception {
        CodeGeneratorOperation codeGeneratorOperation = new CodeGeneratorOperation(applicationName, author, moduleName, basePackage, outDir, password, username, jdbcUrl);
        // ======== 开始生成文件 ========
        codeGeneratorOperation.start();
    }
}
