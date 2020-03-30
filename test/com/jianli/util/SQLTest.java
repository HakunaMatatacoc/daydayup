package com.jianli.util;

import lombok.Data;
import org.apache.commons.text.StringSubstitutor;
import org.codehaus.janino.ClassBodyEvaluator;
import org.yaml.snakeyaml.Yaml;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;
import java.util.stream.Collectors;

public class SQLTest {
    private static List<Retailer> retailers = new ArrayList<>();

    private static List<SQLTest.Table> tables = new ArrayList<>();

    static {
        tables.add(SQLTest.Table.load("guiders.yaml"));
    }


    public static void  main(String args[])throws Exception{
        Class.forName("ru.yandex.clickhouse.ClickHouseDriver");

        //CLICKHOUSE数据库配置
        Properties pro_c = new Properties();
        pro_c.put("database", "xxxx");
        pro_c.put("username", "xxxx");
        pro_c.put("password", "xxxx");

        String sql = "SELECT * FROM pumper.retailers;";
        Connection connection = DriverManager.getConnection("jdbc:clickhouse://192.168.xxx.xxx:xxx", pro_c);
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        //把retailer_code,retailer_name add进retailers中
        while (rs.next()) {
            String retailer_code = rs.getString("retailer_code");
            String retailer_name = rs.getString("retailer_name");
            retailers.add(new SQLTest.Retailer(retailer_code, retailer_name));
        }
        //提取出retailerCode
        List<String> retailerCodes = retailers.stream()
                .map(SQLTest.Retailer::getRetailerCode)
                .collect(Collectors.toList());

        for (String retailerCode : retailerCodes) {
            for (SQLTest.Table table : tables) {
                //拿到TableCases
                List<SQLTest.TableTestCase> testCases = table.getTestCases();
                testCases.forEach(
                        tableTestCase -> {String sql_x = tableTestCase.getSql(retailerCode);
                            System.out.println(sql_x);});
            }
        }
    }

    @Data
    private static class Table {
        private String name;
        private List<SQLTest.TableTestCase> tableTestCases = new ArrayList<>();

        public Table(String name) {
            this.name = name;
        }

        public List<SQLTest.TableTestCase> getTestCases() {
            return tableTestCases;
        }

        public void addTestCase(SQLTest.TableTestCase tableTestCase) {
            tableTestCases.add(tableTestCase);
        }

        //解析yaml
        public static SQLTest.Table load(String fileName) {
            Yaml yaml = new Yaml();
            SQLTest.Table table = new SQLTest.Table(fileName.split("\\.")[0]);
            Map<String, Map<String, Object>> raw = yaml.load(SQLTest.Table.class.getResourceAsStream(fileName));
            raw.forEach((key, value) -> {//key即title
                SQLTest.TableTestCase tableTestCase = new SQLTest.TableTestCase(key, value.get("name").toString(),
                        value.get("result").toString(), value.get("sql").toString());
                if (value.get("options") != null)
                    tableTestCase.setOptionMap((Map<String, Map<String, String>>) value.get("options"));
                table.addTestCase(tableTestCase);
            });
            return table;
        }
    }

    @Data
    private static class TableTestCase {
        private String name;
        private String title;
        private String result;
        private String sqlTemplate;
        private Map<String, Map<String, String>> optionMap = new HashMap<>();

        private TableTestCase(String name, String title, String result, String sqlTemplate) {
            this.name = name;
            this.title = title;
            this.result = result;
            this.sqlTemplate = sqlTemplate;
        }

        public String getSql(String retailerCode) {
            Map<String, String> parametersMap = new HashMap<>();
            //将retailerCode put进parameterMap中，方便optionMap去查
            parametersMap.put("retailer_code", retailerCode);
            optionMap.forEach((key, value) -> {//取option中的条件
                parametersMap.put(key, value.get(retailerCode));
            });
            return StringSubstitutor.replace(sqlTemplate, parametersMap);
        }

        //验证结果
        public boolean validate(ResultSet resultSet) throws Exception {
            ClassBodyEvaluator classBodyEvaluator = new ClassBodyEvaluator();
            classBodyEvaluator.setDefaultImports(new String[]{"java.sql.ResultSet"});
            classBodyEvaluator.cook("public static Boolean validate(ResultSet resultSet) { " +
                    "try {" + result + " } catch(Exception ex) { throw new RuntimeException(ex.getMessage(), ex); } }");
            return (Boolean) classBodyEvaluator.getClazz()
                    .getMethod("validate", ResultSet.class).invoke(null, resultSet);
        }
    }

    @Data
    private static class Retailer {
        private String retailerCode;
        private String retailerName;

        public Retailer(String retailerCode, String retailerName) {
            this.retailerCode = retailerCode;
            this.retailerName = retailerName;
        }
    }
}
