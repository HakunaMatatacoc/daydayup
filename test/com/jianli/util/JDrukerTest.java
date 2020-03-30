package com.jianli.util;

import org.braisdom.drucker.JDrucker;
import org.braisdom.drucker.xsql.XSQLDefinition;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class JDrukerTest {
    private static final String DEFAULT_XSQL_PATH = "./xsql";

    @Test
    @DisplayName("ReadXsql")
    public void testJDruker() throws IOException {
        JDrucker.loadXsqlFile(DEFAULT_XSQL_PATH);
        XSQLDefinition.XSQLDeclaration xsqlDeclaration = JDrucker.getXSQLDeclaration("./xsql/wangsheng.xsql");
        System.out.println(xsqlDeclaration.getSqlStatement("order_line").getSqlStatement());
    }
}
