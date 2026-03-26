package com.example.enums;

public enum CorrespondEnum {
    baseFilePath("src/main/java", "baseFilePath"),
    GlobalVo("/entity/vo/GlobalVo.java", "globalVo.java.ftl"),
    Global_Exception("/exception/GlobalException.java",  "exception.java.ftl"),
    Global_Exception_Handler("/exception/GlobalExceptionHandler.java", "globalExceptionHandler.java.ftl"),
    Response_Enums("/entity/enums/ResponseEnums.java", "responseEnums.java.ftl");


    CorrespondEnum(String filePath, String fileName) {
        this.filePath = filePath;
        this.fileName = fileName;
    }

    private final String filePath;
    private final String fileName;

    public String getFilePath() {
        return filePath;
    }

    public String getFileName() {
        return fileName;
    }
}
