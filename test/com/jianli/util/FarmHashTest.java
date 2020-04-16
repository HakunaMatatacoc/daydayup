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
        String json = "{\"url\" : \"jdbc:jtds:sqlserver://122.112.225.135:24330/EYAYCS\", \"username\": \"aycs\", \"password\": \"aycs\"}";
        String retailer_id = Utils.encrypt(json, "KtoaUuyS7a4whV0ki2wAjP6XQZhjF1cAtkcnP1t3OYPVzSDJrisndyVOKDs2rMZq");
        System.out.println("retailer_id: " + retailer_id);

        String result = Utils.decrypt("e492d00ea1dac7e3b7719c80653f97c29177919bc50a33cf911465923e20f5be4f36211907af6eeaa86551ca8dcd6370b3ff61e5eaab93375c37c0631cfdee901d00dead08994474479a3200cb0e3dd1bda59395acdc3a5181368fe13b3031ad86753440eb30b0854069df947d30c43d",
                "KtoaUuyS7a4whV0ki2wAjP6XQZhjF1cAtkcnP1t3OYPVzSDJrisndyVOKDs2rMZq");
        System.out.println(result);
    }

    @Test
    public void InsertTest() throws Exception {
        Reader in = new FileReader("/Users/lijian/Documents/superhirn自动化测试/ds-sit配置文件/FarmHashRetailers.csv");
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
        pro.put("database", "pumper");
        pro.put("username", "default");
        pro.put("password", "joowing");
        Connection connection = DriverManager.getConnection("jdbc:clickhouse://192.168.xx.xxx:xxxx", pro);
        Statement stmt = connection.createStatement();

        for (Retailer retailer : retailers) {
            Long erp_id = retailer.getErp_id();
            Long retailer_id = retailer.getRetailer_id();
            String retailer_code = retailer.getRetailer_code();
            String retailer_name = retailer.getRetailer_name();
            String connection_properties = retailer.getConnection_properties();

            String sql = "INSERT INTO pumper.retailers (erp_id, retailer_code, retailer_id, retailer_name, connection_type, " +
                    "connection_properties, status, pumping_types, compensation_types, memo) VALUES(" + erp_id + ",\'" + retailer_code + "\'," + retailer_id
                    + ",\'" + retailer_name + "\',\'" + "SQL" + "\',\'" + connection_properties + "\'," + "0" + ",\'"
                    + "member,order,order_coupon,order_line,stock" + "\',\'" + "" + "\',\'" + "" + "\'" + ");";
            stmt.execute(sql);
        }
        stmt.close();
        connection.close();
    }

    public long getErp(String org_code) {
        long erp_id = 9999;
        switch (org_code) {
            case "eshop6":
                erp_id = Utils.farmHash64("eshop6");
                break;
            case "wangsheng":
                erp_id = Utils.farmHash64("wangsheng");
                break;
            case "eshop5":
                erp_id = Utils.farmHash64("eshop5");
                break;
            case "huiyun":
                erp_id = Utils.farmHash64("huiyun");
                break;
            case "chaowang":
                erp_id = Utils.farmHash64("chaowang");
                break;
            case "baiwei_v10":
                erp_id = Utils.farmHash64("baiwei_v10");
                break;
            case "eshop3":
                erp_id = Utils.farmHash64("eshop3");
                break;
            case "eshop8":
                erp_id = Utils.farmHash64("eshop8");
                break;
            case "eshop9":
                erp_id = Utils.farmHash64("eshop9");
                break;
            case "eshop10":
                erp_id = Utils.farmHash64("eshop10");
                break;
            case "kemai_waxiao":
                erp_id = Utils.farmHash64("kemai_waxiao");
                break;
            case "eshop4":
                erp_id = Utils.farmHash64("eshop4");
                break;
            case "haoke":
                erp_id = Utils.farmHash64("haoke");
                break;
            case "kaibao":
                erp_id = Utils.farmHash64("kaibao");
                break;
            case "guanjiapo":
                erp_id = Utils.farmHash64("guanjiapo");
                break;
            case "bainian":
                erp_id = Utils.farmHash64("bainian");
                break;
            case "chaoying":
                erp_id = Utils.farmHash64("chaoying");
                break;
            case "haopu":
                erp_id = Utils.farmHash64("haopu");
                break;
            case "chaoyingSQL":
                erp_id = Utils.farmHash64("chaoyingSQL");
                break;
            case "boyou":
                erp_id = Utils.farmHash64("boyou");
                break;
            case "huachuang":
                erp_id = Utils.farmHash64("huachuang");
                break;
            case "jingyingshengshou":
                erp_id = Utils.farmHash64("jingyingshengshou");
                break;
            case "lianwang":
                erp_id = Utils.farmHash64("lianwang");
                break;
            case "shanghaihaidian":
                erp_id = Utils.farmHash64("shanghaihaidian");
                break;
            case "yongyou":
                erp_id = Utils.farmHash64("yongyou");
                break;
            case "changjietong":
                erp_id = Utils.farmHash64("changjietong");
                break;
            case "yingtong":
                erp_id = Utils.farmHash64("yingtong");
                break;
            case "kemai_kemai":
                erp_id = Utils.farmHash64("kemai_kemai");
                break;
            case "lanlingkeji_v5":
                erp_id = Utils.farmHash64("lanlingkeji_v5");
                break;
            case "baiwei":
                erp_id = Utils.farmHash64("baiwei");
                break;
            case "ziri":
                erp_id = Utils.farmHash64("ziri");
                break;
            case "aokai":
                erp_id = Utils.farmHash64("aokai");
                break;
            case "eshop7":
                erp_id = Utils.farmHash64("eshop7");
                break;
            case "taige":
                erp_id = Utils.farmHash64("taige");
                break;
        }
        return erp_id;
    }

    @Data
    class Retailer {
        private Long erp_id;
        private Long retailer_id;
        private String retailer_code;
        private String retailer_name;
        private String connection_properties;
    }
}
