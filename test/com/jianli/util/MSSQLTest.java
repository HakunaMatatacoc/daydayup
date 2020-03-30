package com.jianli.util;

import java.sql.*;

public class MSSQLTest {

    private static String DATABASE_DRIVER = null;
    private static String HOST = null;
    private static String PORT = null;
    private static String DATABASE = null;
    private static String DATABASE_TYPE = null;
    private static String USERNAME = null;
    private static String PASSWORD = null;
    private static String DATABASE_NAME = null;

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        MSSQLTest();
    }

    public static void MSSQLTest() throws ClassNotFoundException, SQLException {
        DATABASE_DRIVER = "net.sourceforge.jtds.jdbc.Driver";
        DATABASE_TYPE = "sqlserver";
        DATABASE_NAME = "爱婴超市";
        HOST = "122.112.xxx.xxx";
        PORT = "xxxx";
        DATABASE = "xxx";
        USERNAME = "xxx";
        PASSWORD = "xxx";

        Class.forName(DATABASE_DRIVER);

        String sql = "    SELECT\n" +
                "        branch_no AS shop_code,\n" +
                "        sale_id AS guider_code,\n" +
                "        sale_name AS guider_name,\n" +
                "        sale_status AS status,\n" +
                "        memo\n" +
                "    FROM dbo.t_rm_saleman;";
        Connection connection = DriverManager.getConnection("jdbc:jtds:" + DATABASE_TYPE + "://" + HOST + ":" + PORT + "/" + DATABASE,
                USERNAME, PASSWORD);
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        System.out.println(DATABASE_NAME);
        while (rs.next()) {
            String shop_code = rs.getString("shop_code");
            String guider_code = rs.getString("guider_code");
            String guider_name = rs.getString("guider_name");
            String status = rs.getString("status");

            System.out.println("shop_code: " + shop_code + "    guider_code: " + guider_code
                    + "     guider_name: " + guider_name + "    status: " + status);
        }
    }
}
