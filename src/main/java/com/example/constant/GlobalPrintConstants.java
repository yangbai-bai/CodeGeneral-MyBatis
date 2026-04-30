package com.example.constant;

/**
 * 全局打印常量类
 * <p>
 * 该类定义了代码生成器在执行过程中使用的各类日志输出常量，
 * 用于统一控制台输出的格式和内容。
 * </p>
 *
 * @author BaiJinBo
 * @since 2026-04-30
 */
public class GlobalPrintConstants {
    /** 模板变量下放成功的提示信息 */
    public static String VARIABLE = ">>> 模板变量下放成功";
    /** 读取到表数量的提示信息前缀 */
    public static String TABLE_SIZE = ">>> 读取到表数量: ";
    /** 表名的提示信息前缀 */
    public static String TABLE_NAME = ">>> 表名: ";
    /** 代码生成完成后的输出目录提示信息前缀 */
    public static String FINALLY = "代码已生成, 保存在: ";
}
