package com.singleton;

public class MainWindow {
    //private static:为静态方法提供私有静态属性
    private static MainWindow instance = new MainWindow();

    private MainWindow(){

    }

    public static MainWindow getInstance(){
        return instance;
    }

    public void showMessage(){
        System.out.println("Nice!");
    }
}
