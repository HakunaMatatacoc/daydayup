package com.jianli.util;

import com.joowing.ds.commons.Utils;
import lombok.Data;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.junit.jupiter.api.Test;

import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

public class FarmHashTest {
    @Test
    public void Test() {
        Long farmhashcode = Utils.farmHash64("jinyaolan");
        System.out.println("farmhashcode: " + farmhashcode);
    }

    @Test
    public void Test02() throws Exception {
        String json = "{\"url\" : \"jdbc:jtds:sqlserver://58.215.179.xxx:xxx/xxxx\", \"username\": \"xx\", \"password\": \"xxxxxx\"}\n";
        String retailer_id = Utils.encrypt(json, "1^nSJoW1eBrd$kJASAGjINVFXm!u!74H");
        System.out.println("retailer_id: " + retailer_id);
    }

    @Test
    public void InsertTest() throws Exception {
        Reader in = new FileReader("./FarmHashRetailers.csv");
        Iterable<CSVRecord> records = CSVFormat.EXCEL.parse(in);
        ArrayList<Retailer> retailers = new ArrayList();

        for (CSVRecord record : records) {
            Retailer retailer = new Retailer();

            //8是erp_version的下标
            retailer.setErp_id(getErp(record.get(8)));
            retailer.setRetailer_id(Utils.farmHash64(record.get(0)));
            retailer.setRetailer_code(record.get(0));
            retailer.setRetailer_name(record.get(1));
            if (record.get(2).equals("sql_server")) {
                String connection_properties = "{\"url\" : \"jdbc:jtds:sqlserver://" + record.get(3) +
                        ":" + record.get(4) + "/" + record.get(5) + "\", \"username\": \"" + record.get(6)
                        + "\", \"password\": \"" + record.get(7) + "\"}";
                retailer.setConnection_properties(Utils.encrypt(connection_properties, "KtoaUuyS7a4whV0ki2wAjP6XQZhjF1cAtkcnP1t3OYPVzSDJrisndyVOKDs2rMZq"));

            } else if (record.get(2).equals("mysql")) {
                String connection_properties = "{\"url\" : \"jdbc:mysql://" + record.get(3) +
                        ":" + record.get(4) + "/" + record.get(5) + "\", \"username\": \"" + record.get(6)
                        + "\", \"password\": \"" + record.get(7) + "\"}";
                retailer.setConnection_properties(Utils.encrypt(connection_properties, "KtoaUuyS7a4whV0ki2wAjP6XQZhjF1cAtkcnP1t3OYPVzSDJrisndyVOKDs2rMZq"));
            }

            retailers.add(retailer);
        }

        //连接数据库
        Class.forName("ru.yandex.clickhouse.ClickHouseDriver");

        //CLICKHOUSE数据库配置
        Properties pro = new Properties();
        pro.put("database", "xxx");
        pro.put("username", "xxx");
        pro.put("password", "xxx");
        Connection connection = DriverManager.getConnection("jdbc:clickhouse://192.168.10.xxx:xxx", pro);
        Statement stmt = connection.createStatement();

        for (Retailer retailer : retailers) {
            int erp_id = retailer.getErp_id();
            Long retailer_id = retailer.getRetailer_id();
            String retailer_code = retailer.getRetailer_code();
            String retailer_name = retailer.getRetailer_name();
            String connection_properties = retailer.getConnection_properties();

            String sql = "INSERT INTO pumper.retailers (erp_id, retailer_code, retailer_id, retailer_name, connection_type, " +
                    "connection_properties, status, pumping_types, compensation_types, memo) VALUES(" + erp_id + ",\'" + retailer_code + "\'," + retailer_id
                    + ",\'" + retailer_name + "\',\'" + "SQL" + "\',\'" + connection_properties + "\'," + "1" + ",\'"
                    + "member,order,order_coupon,order_line,stock" + "\',\'" + "" + "\',\'" + "" + "\'" + ");";
            stmt.execute(sql);
        }
        stmt.close();
        connection.close();
    }

    public int getErp(String org_code) {
        int erp_id = 9999;
        switch (org_code) {
            case "eshop6":
                erp_id = 0;
                break;
            case "wangsheng":
                erp_id = 1;
                break;
            case "eshop5":
                erp_id = 2;
                break;
            case "huiyun":
                erp_id = 3;
                break;
            case "chaowang":
                erp_id = 4;
                break;
            case "baiwei_v10":
                erp_id = 5;
                break;
            case "eshop3":
                erp_id = 6;
                break;
            case "eshop8":
                erp_id = 7;
                break;
            case "eshop9":
                erp_id = 8;
                break;
            case "eshop10":
                erp_id = 9;
                break;
            case "kemai_waxiao":
                erp_id = 10;
                break;
            case "eshop4":
                erp_id = 11;
                break;
            case "haoke":
                erp_id = 12;
                break;
            case "kaibao":
                erp_id = 13;
                break;
            case "guanjiapo":
                erp_id = 14;
                break;
            case "bainian":
                erp_id = 15;
                break;
            case "chaoying":
                erp_id = 16;
                break;
            case "haopu":
                erp_id = 18;
                break;
            case "chaoyingSQL":
                erp_id = 19;
                break;
            case "boyou":
                erp_id = 20;
                break;
            case "huachuang":
                erp_id = 21;
                break;
            case "jingyingshengshou":
                erp_id = 22;
                break;
            case "lianwang":
                erp_id = 23;
                break;
            case "shanghaihaidian":
                erp_id = 24;
                break;
            case "yongyou":
                erp_id = 25;
                break;
            case "changjietong":
                erp_id = 26;
                break;
            case "yingtong":
                erp_id = 27;
                break;
            case "kemai_kemai":
                erp_id = 28;
                break;
            case "lanlingkeji_v5":
                erp_id = 29;
                break;
            case "baiwei":
                erp_id = 30;
                break;
            case "ziri":
                erp_id = 31;
                break;
            case "aokai":
                erp_id = 32;
                break;
            case "eshop7":
                erp_id = 33;
                break;
            case "taige":
                erp_id = 34;
                break;
        }
        return erp_id;
    }

    @Data
    class Retailer {
        private int erp_id;
        private Long retailer_id;
        private String retailer_code;
        private String retailer_name;
        private String connection_properties;
    }
}
