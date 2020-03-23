package com.mvc;

public class MVCTest {
    public static void main(String[] args) {
        Employee model = new Employee();
        model.setName("Michael");
        model.setId("1");

        EmployeeView view = new EmployeeView();

        EmployeeController employeeController = new EmployeeController(model,view);
        employeeController.updateView();
        employeeController.setEmployeeName("Big Michael");
        employeeController.updateView();
    }
}
