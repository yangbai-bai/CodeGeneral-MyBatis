package com.example;
import com.example.operation.CodeGeneratorOperation;

import java.util.*;


public class CodeGenerator {

    public static void main(String[] args) throws Exception {
        // ======== 基本配置 ========
        String jdbcUrl = "jdbc:mysql://localhost:3306/yangcommunity";
        String username = "root";
        String password = "Bai159357";
        String outDir = "F:/WorkSpace/JavaProjects/CodeGeneration/YangCommunity";
        String basePackage = "com.yangbaibai";
        String moduleName = "yangcommunity";
        String author = "BaiJinBo";
        String applicationName = "YangCommunity"; // 是否生成Application, 若为null则不生成
        Boolean isGeneralGlobalVo = true; // 是否生成全局 Vo
        Boolean isGeneralGlobalException = true; // 是否生成全局异常处理
        Boolean isGeneralGlobalEnums = true;

        CodeGeneratorOperation codeGeneratorOperation = new CodeGeneratorOperation(applicationName, author, moduleName, basePackage, outDir, password, username, jdbcUrl, isGeneralGlobalVo, isGeneralGlobalException, isGeneralGlobalEnums);

        // ======== 开始生成文件 ========
        codeGeneratorOperation.start();
    }
}
