package com.jianli.util;

import com.joowing.ds.commons.Utils;

import java.io.File;
import java.io.FileWriter;
import java.sql.*;
import java.util.Properties;

public class JsonArrayTest {
    public static void main(String[] args) throws Exception {
        //加载clickhouse引擎
        Class.forName("ru.yandex.clickhouse.ClickHouseDriver");

        //CLICKHOUSE数据库配置
        Properties pro = new Properties();
        pro.put("database", "pumper");
        pro.put("username", "xxx");
        pro.put("password", "xxx");
        String sql = "SELECT " +
                "a.erp_id," +
                "a.retailer_code," +
                "b.erp_name," +
                "b.driver," +
                "b.database_type," +
                "a.connection_properties " +
                "FROM " +
                "pumper.retailers AS a " +
                "ALL LEFT JOIN pumper.erps AS b " +
                "USING erp_id;";
        Connection connection = DriverManager.getConnection("jdbc:clickhouse://192.168.xxx.xxx:xxx", pro);
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        File file = new File("./json.txt");
        file.createNewFile();
        FileWriter fileWriter = new FileWriter(file);
        StringBuilder stringBuilder = new StringBuilder("{\n" +
                "  \"retailer_infos\" : [\n" +
                "    {");
        //把retailer_code,retailer_name add进retailers中
        while (rs.next()) {
            String erp_id = rs.getString("erp_id");
            String retailer_code = rs.getString("retailer_code");
            String erp_name = rs.getString("erp_name");
            String driver = rs.getString("driver");
            String database_type = rs.getString("database_type");
            String connection_properties = Utils.decrypt(rs.getString("connection_properties"),
                    "KtoaUuyS7a4whV0ki2wAjP6XQZhjF1cAtkcnP1t3OYPVzSDJrisndyVOKDs2rMZq").replace("\"","\\\"");
            stringBuilder.append("\n" + "      \"erp_id\" : " + erp_id + ",\n" +
                    "      \"retailer_code\" : \"" + retailer_code + "\",\n" +
                    "      \"erp_vendor\" : \"" + erp_name + "\",\n" +
                    "      \"driver\" : \"" + driver +"\",\n" +
                    "      \"database_type\" : \"" + database_type + "\",\n" +
                    "      \"pumping_types\" : [\"shop\",\"guider\",\"member\",\"product\",\"product_category\",\"order\",\"order_coupon\",\"order_line\",\"stock\"],\n" +
                    "      \"raw_connection_properties\" : \"" + connection_properties + "\"\n" +
                    "    },\n" +
                    "    {");
//            System.out.println("erp_id： " + erp_id + " , retailer_code: " + retailer_code
//                    + " , erp_name: " + erp_name + " , driver: " + driver + " , database_type: " +
//                    database_type + " , connetion_properties: " + connection_properties);
        }
        String sub = stringBuilder.substring(0,stringBuilder.length()-7);
        String result = sub + "\n" + "  ]\n" +
                "}";
        fileWriter.write(result);
        fileWriter.close();
    }
}
