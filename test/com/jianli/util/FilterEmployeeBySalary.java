package com.jianli.util;

import com.jianli.util.lambda.Employee;
import com.jianli.util.lambda.MyPredicate;

public class FilterEmployeeBySalary implements MyPredicate<Employee> {

    @Override
    public boolean test(Employee t) {
        return t.getSalary() > 5000;
    }
}
