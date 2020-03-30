package com.jianli.util;

import java.sql.*;

public class MYSQLTest {
    private static String DATABASE_DRIVER = null;
    private static String HOST = null;
    private static String PORT = null;
    private static String DATABASE = null;
    private static String DATABASE_TYPE = null;
    private static String USERNAME = null;
    private static String PASSWORD = null;
    private static String DATABASE_NAME = null;

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        MYSQLTest();
    }

    public static void MYSQLTest() throws ClassNotFoundException, SQLException {
        DATABASE_DRIVER = "com.mysql.jdbc.Driver";
        DATABASE_TYPE = "mysql";
        DATABASE_NAME = "xxx";
        HOST = "47.108.xxx.xxx";
        PORT = "xxxx";
        DATABASE = "arm_xxx";
        USERNAME = "xxxxx";
        PASSWORD = "xxxxx";

        Class.forName(DATABASE_DRIVER);

        String sql = "    SELECT\n" +
                "        CASE WHEN b.code IS NULL THEN '' ELSE b.code END AS shop_codes,\n" +
                "        a.no AS guider_code,\n" +
                "        a.name AS guider_name,\n" +
                "        a.useable AS status,\n" +
                "        '' AS memo\n" +
                "    FROM sys_user a\n" +
                "    LEFT JOIN Sys_shop b ON a.office_id = b.id;";

        Connection connection = DriverManager.getConnection("jdbc:" + DATABASE_TYPE + "://" + HOST + ":" + PORT + "/" + DATABASE,
                USERNAME, PASSWORD);
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        System.out.println(DATABASE_NAME);
        while (rs.next()) {
            String shop_codes = rs.getString("shop_codes");
            String guider_code = rs.getString("guider_code");
            String guider_name = rs.getString("guider_name");
            String status = rs.getString("status");
            String memo = rs.getString("memo");

            System.out.println("shop_code: " + shop_codes + "    guider_code: " + guider_code
                    + "     guider_name: " + guider_name + "    status: " + status);
        }

    }
}
