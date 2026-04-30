package com.example.enums;

/**
 * 文件映射枚举类
 * <p>
 * 该枚举类定义了代码生成过程中需要生成的额外文件映射关系，
 * 包括文件路径、模板文件名和生成完成后的日志信息。
 * </p>
 *
 * @author BaiJinBo
 * @since 2026-04-30
 */
public enum CorrespondEnum {
    /** 通用 VO 文件 */
    GlobalVo("/entity/vo/GlobalVo.java", "java_single/globalVo.java.ftl", ">>> 通用VO文件已生成"),
    /** 全局异常类 */
    Global_Exception("/exception/GlobalException.java", "java_single/exception/exception.java.ftl", ">>> GlobalException 已生成"),
    /** 全局异常处理器 */
    Global_Exception_Handler("/exception/GlobalExceptionHandler.java", "java_single/exception/globalExceptionHandler.java.ftl", ">>> GlobalExceptionHandler 已生成"),
    /** 通用响应枚举类 */
    Response_Enums("/entity/enums/ResponseEnums.java", "java_single/responseEnums.java.ftl", ">>> 通用响应式枚举文件已生成"),
    /** Redis 配置类 */
    Redis_Config("/redis/RedisConfig.java", "java_single/redis/redisConfig.java.ftl", ">>> Redis_Config 已生成"),
    /** Redis 服务类 */
    Redis_Service("/redis/RedisService.java", "java_single/redis/redisService.java.ftl", ">>> Redis_Service 已生成"),
    /** 初始化运行类 */
    Init_Run("/InitRun.java", "java_single/InitRun.java.ftl", ">>> Init_Run 已生成"),
    /** 应用启动类 */
    ApplicationGenerator("/OrderSystemApplication.java", "java_single/application.java.ftl", ">>> Application.java 已生成");


    /** 文件路径 */
    private final String filePath;
    /** 模板文件名 */
    private final String fileName;
    /** 完成日志 */
    private final String finishLog;

    /**
     * 构造函数
     *
     * @param filePath   文件路径
     * @param fileName   模板文件名
     * @param finishLog  完成日志
     */
    CorrespondEnum(String filePath, String fileName, String finishLog) {
        this.filePath = filePath;
        this.fileName = fileName;
        this.finishLog = finishLog;
    }

    /**
     * 获取文件路径
     *
     * @return 文件路径
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * 获取模板文件名
     *
     * @return 模板文件名
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * 获取完成日志
     *
     * @return 完成日志
     */
    public String getFinishLog() {
        return finishLog;
    }
}
