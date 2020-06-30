package com.jianli.util;

import com.jianli.util.lambda.Employee;
import com.jianli.util.lambda.MyPredicate;

public class FilterEmployeeByAge implements MyPredicate<Employee> {

    @Override
    public boolean test(Employee t) {
        return t.getAge() > 35;
    }

}
