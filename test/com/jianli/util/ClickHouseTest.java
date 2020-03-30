package com.jianli.util;

import java.sql.*;
import java.util.Properties;

public class ClickHouseTest {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Class.forName("ru.yandex.clickhouse.ClickHouseDriver");

        //CLICKHOUSE数据库配置
        Properties pro = new Properties();
        pro.put("database", "pumper");
        pro.put("username", "xxxx");
        pro.put("password", "xxxx");
        String sql = "SELECT * FROM pumper.erps;";
        Connection connection = DriverManager.getConnection("jdbc:clickhouse://192.168.xxx.xxx:xxxx", pro);
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        //把retailer_code,retailer_name add进retailers中
        while (rs.next()) {
            String erp_name = rs.getString("erp_name");
            String erp_display_name = rs.getString("erp_display_name");
            System.out.println("erp_name： " + erp_name + " , erp_display_name: " + erp_display_name);
        }

        rs.close();
        stmt.close();
        connection.close();
    }
}

