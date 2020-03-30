package com.jianli.util;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class CSVParseTest {
    @Test
    @DisplayName("ReadCsv")
    public void testCSV() throws IOException {
        Reader in = new FileReader("/Users/lijian/Documents/superhirn自动化测试/retialers-3307相关信息.csv");
        Iterable<CSVRecord> records = CSVFormat.EXCEL.parse(in);
        for (CSVRecord record : records) {
            String org_code = record.get(3);
            String name = record.get(4);
            String database_type = record.get(5);
            String host = record.get(6);
            String port = record.get(7);
            String database = record.get(9);
            String password = record.get(10);
            String username = record.get(11);
            String erp_version = record.get(13);
            System.out.println("ord_code: " + org_code + "\n" + "name: " + name + "\n" + "database_type: " + database_type
                    + "\n" + "host: " + host + "\n" + "port: " + port + "\n" + "database: " + database + "\n" + "password: "
                    + password + "\n" + "username: " + username + "\n" + "erp_version: " + erp_version);
            System.out.println("----------------------");
        }
    }
}
