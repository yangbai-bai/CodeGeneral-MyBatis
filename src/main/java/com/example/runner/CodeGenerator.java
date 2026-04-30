package com.example.runner;

import com.example.config.GeneratorConfig;
import com.example.core.CodeGeneratorOperation;

/**
 * MyBatis 代码生成器启动类
 * <p>
 * 作为代码生成器的主入口，负责初始化配置并启动代码生成流程。
 * </p>
 *
 * @author BaiJinBo
 * @since 2026-04-30
 */
public class CodeGenerator {

    /**
     * 主启动方法
     * <p>
     * 初始化代码生成器配置，并执行代码生成流程。
     * </p>
     *
     * @param args 命令行参数（当前未使用）
     * @throws Exception 如果代码生成过程中发生异常
     */
    public static void main(String[] args) throws Exception {
        GeneratorConfig config = new GeneratorConfig.Builder()
                .jdbcUrl("jdbc:mysql://localhost:3306/order_system")
                .username("root")
                .password("Bai159357")
                .outDir("F:/WorkSpace/JavaProjects/CodeGeneration/OrderSystem")
                .basePackage("com.yangbaibai")
                .moduleName("ordersystem")
                .author("BaiJinBo")
                .applicationName("OrderSystem")
                .build();

        CodeGeneratorOperation generator = new CodeGeneratorOperation(config);
        generator.start();
    }
}
