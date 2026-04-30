# MyBatis Code Generator

基于 MyBatis-Plus 的代码生成器，快速生成 Spring Boot 项目的基础代码。

## 项目简介

这是一个高度可配置的 MyBatis 代码生成工具，基于 MyBatis-Plus 的 `FastAutoGenerator` 实现。通过连接数据库，自动生成 Entity、Mapper、Service、Controller 等基础代码，同时支持生成通用的公共类（如全局异常处理、Redis 配置、响应封装等）。

## 环境要求

- **Java**: JDK 21+
- **Maven**: 3.8+
- **数据库**: MySQL 5.7+ (或其他支持 JDBC 的数据库)

## 技术栈

| 技术 | 版本 |
|------|------|
| MyBatis-Plus Generator | 3.5.3.2 |
| FreeMarker | 2.3.31 |
| MySQL Connector | 8.0.33 |
| SLF4J | 1.7.36 |

## 项目结构

```
src/main/java/com/example/
├── config/                           # 配置层
│   └── GeneratorConfig.java          # 代码生成器配置类
├── runner/                           # 启动入口
│   └── CodeGenerator.java           # 主入口类
├── core/                             # 核心逻辑层
│   ├── CodeGeneratorOperation.java  # 核心操作类
│   └── DataBase.java                # 数据库操作类
├── constant/                         # 常量层
│   └── GlobalPrintConstants.java    # 全局打印常量
├── enums/                            # 枚举层
│   └── CorrespondEnum.java           # 文件映射枚举
└── template/                         # 模板引擎层
    ├── TemplateEngine.java           # FreeMarker 模板引擎
    └── TemplateVariable.java         # 模板变量定义
```

## 快速开始

### 1. 克隆项目

```bash
git clone <repository-url>
cd MyBatisCodeGeneration
```

### 2. 修改数据库配置

编辑 `src/main/java/com/example/runner/CodeGenerator.java` 中的数据库配置：

```java
public static void main(String[] args) throws Exception {
    GeneratorConfig config = new GeneratorConfig.Builder()
            .jdbcUrl("jdbc:mysql://localhost:3306/your_database")
            .username("your_username")
            .password("your_password")
            .outDir("F:/YourProject/src/main/java")
            .basePackage("com.yourcompany")
            .moduleName("yourmodule")
            .build();

    CodeGeneratorOperation generator = new CodeGeneratorOperation(config);
    generator.start();
}
```

### 3. 运行生成器

```bash
mvn compile exec:java -Dexec.mainClass="com.example.runner.CodeGenerator"
```

或在 IDE 中直接运行 `CodeGenerator.java` 的 `main` 方法。

## 配置说明

| 参数 | 必填 | 说明 | 示例 |
|------|------|------|------|
| jdbcUrl | ✅ | 数据库连接 URL | `jdbc:mysql://localhost:3306/dbname` |
| username | ✅ | 数据库用户名 | `root` |
| password | ✅ | 数据库密码 | `123456` |
| outDir | ✅ | 代码输出目录 | `F:/projects/myapp` |
| basePackage | ✅ | 基础包名 | `com.example` |
| moduleName | ✅ | 模块名称 | `user` |
| author | ✅ | 作者名称（默认 Unknown） | `BaiJinBo` |
| applicationName | ❌ | 应用名称（默认等于 moduleName） | `UserSystem` |

## 生成内容

### 基础代码（MyBatis-Plus）

- **Entity**: 实体类（支持 Lombok）
- **Mapper**: Mapper 接口
- **Mapper XML**: MyBatis 映射文件
- **Service**: 业务层接口
- **ServiceImpl**: 业务层实现
- **Controller**: RESTful 控制器

### 公共类（额外生成）

| 文件 | 说明 |
|------|------|
| GlobalVo.java | 统一响应封装 |
| GlobalException.java | 自定义全局异常 |
| GlobalExceptionHandler.java | 全局异常处理器 |
| ResponseEnums.java | 响应状态枚举 |
| RedisConfig.java | Redis 配置类 |
| RedisService.java | Redis 服务类 |
| InitRun.java | 初始化运行类 |
| Application.java | Spring Boot 启动类 |

## 优势特点

### 1. 配置灵活
- 采用 Builder 模式，链式配置
- 必填参数强制校验，避免运行时错误

### 2. 代码解耦
- 配置与逻辑分离
- 清晰的分层架构（config / runner / core / template）

### 3. 易于扩展
- 模板文件独立存放
- 文件映射通过枚举管理，添加新文件只需修改 `CorrespondEnum`

### 4. 开箱即用
- 生成代码已配置好 Lombok、RESTful 风格
- 包含完整的公共类，无需手动创建

## 模板自定义

模板文件位于 `src/main/resources/templates/` 目录：

```
templates/
├── entity.java.ftl          # 实体类模板
├── mapper.java.ftl          # Mapper 接口模板
├── mapper.xml.ftl          # Mapper XML 模板
├── service.java.ftl        # Service 接口模板
├── serviceImpl.java.ftl    # ServiceImpl 实现模板
└── java_single/            # 额外生成文件
    ├── application.java.ftl
    ├── globalVo.java.ftl
    ├── responseEnums.java.ftl
    ├── InitRun.java.ftl
    ├── redis/
    │   ├── redisConfig.java.ftl
    │   └── redisService.java.ftl
    └── exception/
        ├── exception.java.ftl
        └── globalExceptionHandler.java.ftl
```

如需自定义模板，直接修改对应 `.ftl` 文件即可。

## 后续功能规划

- [ ] **多数据库支持**: 支持 PostgreSQL、Oracle、SQLServer 等
- [ ] **UI 配置界面**: 提供图形化配置界面
- [ ] **增量生成**: 支持增量更新代码，不覆盖已有文件
- [ ] **模板变量增强**: 支持更多自定义模板变量
- [ ] **代码校验**: 生成前校验代码规范
- [ ] **历史版本管理**: 支持回滚到历史生成版本
- [ ] **插件扩展**: 支持自定义插件（如 Swagger 注解增强）

## 注意事项

1. **表前缀**: 默认忽略 `t_` 和 `sys_` 前缀的表，可在 `CodeGeneratorOperation.java` 中修改
2. **输出目录**: 确保指定目录存在且有写权限
3. **覆盖策略**: 默认启用文件覆盖，确认不会丢失重要代码
4. **数据库驱动**: 根据数据库类型添加对应驱动依赖

## 许可证

MIT License

## 作者

BaiJinBo

## 版本历史

- **v1.0.0** (2026-04-30): 初始版本，支持 MySQL 数据库代码生成
