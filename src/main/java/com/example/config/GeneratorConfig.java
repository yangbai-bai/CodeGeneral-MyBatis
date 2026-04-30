package com.example.config;

import java.nio.file.Paths;

/**
 * 代码生成器配置类
 * <p>
 * 封装代码生成器所需的所有配置参数，包括数据库连接信息、
 * 输出目录、包路径、模块名称等。
 * </p>
 *
 * @author BaiJinBo
 * @since 2026-04-30
 */
public class GeneratorConfig {

    /** JDBC 数据库连接 URL */
    private final String jdbcUrl;
    /** 数据库用户名 */
    private final String username;
    /** 数据库密码 */
    private final String password;
    /** 代码输出根目录 */
    private final String outDir;
    /** 基础包名 */
    private final String basePackage;
    /** 模块名称 */
    private final String moduleName;
    /** 作者名称 */
    private final String author;
    /** 应用名称 */
    private final String applicationName;
    /** 基础文件路径 */
    private final String baseFilePath;

    /**
     * 私有构造函数，通过 Builder 构建
     */
    private GeneratorConfig(Builder builder) {
        this.jdbcUrl = builder.jdbcUrl;
        this.username = builder.username;
        this.password = builder.password;
        this.outDir = builder.outDir;
        this.basePackage = builder.basePackage;
        this.moduleName = builder.moduleName;
        this.author = builder.author;
        this.applicationName = builder.applicationName;
        this.baseFilePath = Paths.get(outDir, "src/main/java",
                basePackage.replace(".", "/"),
                moduleName) + "/";
    }

    public String getJdbcUrl() {
        return jdbcUrl;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getOutDir() {
        return outDir;
    }

    public String getBasePackage() {
        return basePackage;
    }

    public String getModuleName() {
        return moduleName;
    }

    public String getAuthor() {
        return author;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public String getBaseFilePath() {
        return baseFilePath;
    }

    /**
     * 获取包路径（模块包）
     *
     * @return 完整包路径
     */
    public String getPackagePath() {
        return basePackage + "." + moduleName;
    }

    /**
     * 获取应用启动类名称
     *
     * @return 应用启动类名称
     */
    public String getApplicationClassName() {
        return moduleName.substring(0, 1).toUpperCase() + moduleName.substring(1) + "Application";
    }

    /**
     * Builder 静态内部类
     */
    public static class Builder {
        private String jdbcUrl = "jdbc:mysql://localhost:3306/order_system";
        private String username = "root";
        private String password = "Bai159357";
        private String outDir = "F:/WorkSpace/JavaProjects/CodeGeneration/OrderSystem";
        private String basePackage = "com.yangbaibai";
        private String moduleName = "ordersystem";
        private String author = "BaiJinBo";
        private String applicationName = "OrderSystem";

        public Builder jdbcUrl(String jdbcUrl) {
            this.jdbcUrl = jdbcUrl;
            return this;
        }

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder outDir(String outDir) {
            this.outDir = outDir;
            return this;
        }

        public Builder basePackage(String basePackage) {
            this.basePackage = basePackage;
            return this;
        }

        public Builder moduleName(String moduleName) {
            this.moduleName = moduleName;
            return this;
        }

        public Builder author(String author) {
            this.author = author;
            return this;
        }

        public Builder applicationName(String applicationName) {
            this.applicationName = applicationName;
            return this;
        }

        public GeneratorConfig build() {
            return new GeneratorConfig(this);
        }
    }
}
