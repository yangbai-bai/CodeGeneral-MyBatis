package com.example.template.exception;

import com.example.constant.GlobalPrintConstants;
import com.example.enums.CorrespondEnum;
import com.example.utils.GeneralUtils;
import freemarker.template.Configuration;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.util.Map;

public class ExceptionGeneral {

    /**
     * 生成全局异常处理和方法
     * @param data 下放的变量
     * @param cfg 模板配置
     * @throws TemplateException 模板异常
     * @throws IOException IO 异常
     */
    public static void generalException(Map<String, Object> data, String baseFilePath,  Configuration cfg) throws TemplateException, IOException {
        GeneralUtils.generateExtraFile(data, baseFilePath + CorrespondEnum.Global_Exception.getFilePath(), cfg, GlobalPrintConstants.Global_Exception ,CorrespondEnum.Global_Exception.getFileName());
        GeneralUtils.generateExtraFile(data, baseFilePath + CorrespondEnum.Global_Exception_Handler.getFilePath(), cfg, GlobalPrintConstants.Global_Exception_Handler ,CorrespondEnum.Global_Exception_Handler.getFileName());
    }

}
