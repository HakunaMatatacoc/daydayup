package com.singleton;

public class MainWindowTest {
    public static void main(String[] args) {
        //编译不通过
//        MainWindow mainWindow = new MainWindow();
        MainWindow object = MainWindow.getInstance();
        object.showMessage();
    }
}
