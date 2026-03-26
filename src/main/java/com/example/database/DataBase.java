package com.example.database;

import com.example.constant.GlobalPrintConstants;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DataBase {
    /**
     * 自动获取数据库中所有表名
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
