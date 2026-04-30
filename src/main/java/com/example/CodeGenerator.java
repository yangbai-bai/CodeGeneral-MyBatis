package com.example;
import com.example.operation.CodeGeneratorOperation;

import java.nio.file.Paths;
import java.util.*;


public class CodeGenerator {

    public static String jdbcUrl = "jdbc:mysql://localhost:3306/order_system";
    public static String username = "root";
    public static String password = "Bai159357";
    public static String outDir = "F:/WorkSpace/JavaProjects/CodeGeneration/OrderSystem";
    public static String basePackage = "com.yangbaibai";
    public static String moduleName = "ordersystem";
    public static String author = "BaiJinBo";
    public static String applicationName = "OrderSystem";
    public static String baseFilePath = Paths.get(CodeGenerator.outDir,"src/main/java",
            CodeGenerator.basePackage.replace(".", "/"),
            CodeGenerator.moduleName)+ "/";


    public static void main(String[] args) throws Exception {
        CodeGeneratorOperation codeGeneratorOperation = new CodeGeneratorOperation(applicationName, author, moduleName, basePackage, outDir, password, username, jdbcUrl);
        // ======== 开始生成文件 ========
        codeGeneratorOperation.start();
    }
}
