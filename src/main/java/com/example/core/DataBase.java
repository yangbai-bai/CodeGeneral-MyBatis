package com.example.core;

import com.example.constant.GlobalPrintConstants;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * 数据库操作类
 * <p>
 * 该类提供数据库相关的操作方法，用于连接数据库并获取数据库中的表信息。
 * </p>
 *
 * @author BaiJinBo
 * @since 2026-04-30
 */
public class DataBase {

    /**
     * 自动获取数据库中所有表名
     * <p>
     * 通过 JDBC 连接数据库，使用数据库元数据获取当前数据库中所有表的名称。
     * </p>
     *
     * @param jdbcUrl  JDBC 数据库连接 URL
     * @param username 数据库用户名
     * @param password 数据库密码
     * @return 包含所有表名的列表
     * @throws Exception 如果获取表名过程中发生异常
     */
    public static List<String> getAllTableNames(String jdbcUrl, String username, String password) throws Exception {
        List<String> tables = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(jdbcUrl, username, password);
             ResultSet rs = conn.getMetaData().getTables(conn.getCatalog(), null, "%", new String[]{"TABLE"})) {
            while (rs.next()) {
                tables.add(rs.getString("TABLE_NAME"));
            }
        }
        System.out.println(GlobalPrintConstants.TABLE_SIZE + tables.size());
        System.out.println(GlobalPrintConstants.TABLE_NAME + String.join(", ", tables));
        return tables;
    }
}
