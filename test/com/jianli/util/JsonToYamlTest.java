package com.jianli.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import net.sf.json.JSONObject;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class JsonToYamlTest {
    @Test
    public void ReadCSV() throws IOException {
        Reader in = new FileReader("./JsonToYamlConfig.csv");
        Iterable<CSVRecord> records = CSVFormat.EXCEL.parse(in);
        for (CSVRecord record : records) {
            String org_code = record.get(0);
            String erp_brand_id = record.get(1);
            String config = record.get(2);
            System.out.println(org_code + " ," + erp_brand_id + " ," + config);
        }
    }

    @Test
    @SuppressWarnings("unchecked")
    public void ParseJson() {
        String jsonStr = "{\"weight\":51.3,\"name\":\"露西\",\"age\":26,\"gender\":\"female\",\"grades\":\"三班\"}";
        Map<String, Object> map = new HashMap<String, Object>();
        JSONObject jsonObject = JSONObject.fromObject(jsonStr);
        Iterator<String> keys = jsonObject.keys();//定义迭代器
        String key = null;
        Object value = null;
        while (keys.hasNext()) {
            key = keys.next();
            value = jsonObject.get(key);
            map.put(key, value);
        }
        System.out.println(map.get("weight"));
        System.out.println(map.get("name"));
        System.out.println(map.get("age"));
        System.out.println(map.get("gender"));
        System.out.println(map.get("grades"));
    }

    public static void main(String[] args) throws Exception {
        //读取csv文件
        Reader in = new FileReader("./JsonToYamlConfig.csv");
        Iterable<CSVRecord> records = CSVFormat.EXCEL.parse(in);
        String path;
        BufferedWriter out = null;
        for (CSVRecord record : records) {
//            if (record.get(1).equals("0")) {
//                path = "unknown.yaml";
//                out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path, true), "UTF-8"));
//                out.write("org_code: " + record.get(0));
//                out.write(asYaml(record.get(2)));
//                out.write("\n");
//                out.close();
//            } else if (record.get(1).equals("1")) {
//                path = "eshop9.yaml";
//                out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path, true), "UTF-8"));
//                out.write("org_code: " + record.get(0));
//                out.write(asYaml(record.get(2)));
//                out.write("\n");
//                out.close();
//            } else
            if (record.get(1).equals("2")) {
//                path = "eshop3.yaml";
//                out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path, true), "UTF-8"));
//                out.write(record.get(0));
//                out.write(asYaml(record.get(2)));
//                out.write("\n");
//                out.close();
                Map<String,Object> map = new HashMap<String,Object>();
                Map<String,Object> mapBilling = new HashMap<String,Object>();
                Map<String,Object> mapProduct = new HashMap<String,Object>();
                Map<String,Object> mapBilling_coupon = new HashMap<String,Object>();
                JSONObject jsonObject = JSONObject.fromObject(record.get(2));
                String key = null;
                Object value = null;
                Iterator<String> keys = jsonObject.keys();//定义迭代器
                while (keys.hasNext()) {
                    key = keys.next();
                    value = jsonObject.get(key);
                    map.put(key, value);
                }

                JSONObject jsonObjectBilling = JSONObject.fromObject(map.get("billing"));
                JSONObject jsonObjectProduct = JSONObject.fromObject(map.get("product"));
                JSONObject jsonObjectBillingCoupon = JSONObject.fromObject(map.get("billing_coupon"));

                keys = jsonObjectBilling.keys();
                while (keys.hasNext()) {
                    key = keys.next();
                    value = jsonObjectBilling.get(key);
                    mapBilling.put(key, value);
                }

                keys = jsonObjectProduct.keys();
                while (keys.hasNext()) {
                    key = keys.next();
                    value = jsonObjectProduct.get(key);
                    mapProduct.put(key, value);
                }

                keys = jsonObjectBillingCoupon.keys();
                while (keys.hasNext()) {
                    key = keys.next();
                    value = jsonObjectBillingCoupon.get(key);
                    mapBilling_coupon.put(key, value);
                }
                String org_code = record.get(0);
                String pay_way = (String) mapBilling.get("pay_way");
                String barcode = (String) mapProduct.get("barcode");
                String code_length = (String) mapBilling_coupon.get("card_no_length");
                String result = "{\"" + org_code + "\" : {\"product\" : {\"barcode\" : \""
                        + barcode + "\"},\"order\" : {\"pay_way\" : \"" + pay_way + "\"},\"order_coupon\" : {\"code_length\" : "
                        + code_length + "}}}";
                // parse JSON
                JsonNode jsonNodeTree = new ObjectMapper().readTree(result);
                // save it as YAML
                String jsonAsYaml = new YAMLMapper().writeValueAsString(jsonNodeTree);
                path = "eshop3.yaml";
                out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path, true), "UTF-8"));
                out.write(jsonAsYaml);
                out.close();
            }
//             else if (record.get(1).equals("3")) {
//                path = "haopu.yaml";
//                out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path, true), "UTF-8"));
//                out.write("org_code: " + record.get(0));
//                out.write(asYaml(record.get(2)));
//                out.write("\n");
//                out.close();
//            } else if (record.get(1).equals("4")) {
//                path = "huiyun.yaml";
//                out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path, true), "UTF-8"));
//                out.write("org_code: " + record.get(0));
//                out.write(asYaml(record.get(2)));
//                out.write("\n");
//                out.close();
//            } else if (record.get(1).equals("5")) {
//                path = "eshop5.yaml";
//                out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path, true), "UTF-8"));
//                out.write("org_code: " + record.get(0));
//                out.write(asYaml(record.get(2)));
//                out.write("\n");
//                out.close();
//            } else if (record.get(1).equals("6")) {
//                path = "huachuang.yaml";
//                out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path, true), "UTF-8"));
//                out.write("org_code: " + record.get(0));
//                out.write(asYaml(record.get(2)));
//                out.write("\n");
//                out.close();
//            } else if (record.get(1).equals("7")) {
//                path = "kemai.yaml";
//                out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path, true), "UTF-8"));
//                out.write("org_code: " + record.get(0));
//                out.write(asYaml(record.get(2)));
//                out.write("\n");
//                out.close();
//            } else if (record.get(1).equals("8")) {
//                path = "baiwei.yaml";
//                out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path, true), "UTF-8"));
//                out.write("org_code: " + record.get(0));
//                out.write(asYaml(record.get(2)));
//                out.write("\n");
//                out.close();
//            } else if (record.get(1).equals("9")) {
//                path = "kaibao.yaml";
//                out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path, true), "UTF-8"));
//                out.write("org_code: " + record.get(0));
//                out.write(asYaml(record.get(2)));
//                out.write("\n");
//                out.close();
//            }
        }
    }

    @SuppressWarnings("rawTypes")
    public static String asYaml(String org_code, String jsonString) throws Exception {
        Map<String, Map<String,String>> map = JSON.parse(jsonString, new TypeReference<Map<String, Map<String,String>>>(){}, false);
        String pay_way = "";
        String barcode = "";
        String code_length = "";
        if (map != null) {
            if (map.get("billing") != null)
                if (map.get("billing").get("pay_way") != null)
                    pay_way = map.get("billing").get("pay_way");
            if (map.get("product") != null)
                if (map.get("product").get("barcode") != null)
                    barcode = map.get("product").get("barcode");
            if (map.get("billing_coupon") != null)
                if (map.get("billing_coupon").get("card_no_length") != null)
                    code_length = map.get("billing_coupon").get("card_no_length");
            String result = "{\"" + org_code + "\" : {\"product\" : {\"barcode\" : \""
                    + barcode + "\"},\"order\" : {\"pay_way\" : \"" + pay_way + "\"},\"order_coupon\" : {\"code_length\" : "
                    + code_length + "}}}";
            // parse JSON
            JsonNode jsonNodeTree = new ObjectMapper().readTree(result);
            // save it as YAML
            String jsonAsYaml = new YAMLMapper().writeValueAsString(jsonNodeTree);
            return jsonAsYaml;
        } else return "";
    }
}
