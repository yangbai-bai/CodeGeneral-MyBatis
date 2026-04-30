package com.example.enums;

import com.example.CodeGenerator;

public enum CorrespondEnum {
    GlobalVo("/entity/vo/GlobalVo.java", "java/globalVo.java.ftl", ">>> 通用VO文件已生成"),
    Global_Exception("/exception/GlobalException.java", "java/exception.java.ftl", ">>> GlobalException 已生成"),
    Global_Exception_Handler("/exception/GlobalExceptionHandler.java", "java/globalExceptionHandler.java.ftl", ">>> GlobalExceptionHandler 已生成"),
    Response_Enums("/entity/enums/ResponseEnums.java", "java/responseEnums.java.ftl", ">>> 通用响应式枚举文件已生成"),
    Redis_Config("/redis/RedisConfig.java", "java/redisConfig.java.ftl", ">>> Redis_Config 已完成"),
    Redis_Service("/redis/RedisService.java", "java/redisService.java.ftl", ">>> Redis_Service 已完成"),
    Init_Run("/InitRun.java", "java/InitRun.java.ftl", ">>> Init_Run 已完成"),
    ApplicationGenerator("/" + CodeGenerator.applicationName + "Application.java", "java/application.java.ftl", ">>> Application.java 已完成");


    CorrespondEnum(String filePath, String fileName, String finishLog) {
        this.filePath = filePath;
        this.fileName = fileName;
        this.finishLog = finishLog;
    }

    private final String filePath;
    private final String fileName;
    private final String finishLog;

    public String getFilePath() {
        return filePath;
    }

    public String getFileName() {
        return fileName;
    }

    public String getFinishLog() {
        return finishLog;
    }
}
