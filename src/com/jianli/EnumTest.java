package com.jianli;

import javax.print.attribute.standard.PrinterResolution;

public class EnumTest {
    public static void main(String[] args) {
        printWeek(Week.THURSDAY);
        System.out.println(Week.THURSDAY.name());
        System.out.println(Week.MONDAY.ordinal());
        System.out.println(Week.THURSDAY.ordinal());
    }

    public static void printWeek(Week week){
        switch (week){
            case MONDAY:
                System.out.println("周一");
                break;
            case TUESDAY:
                System.out.println("周二");
                break;
            case WENDSDAY:
                System.out.println("周三");
                break;
            case THURSDAY:
                System.out.println("周四");
                break;
            case FRIDAY:
                System.out.println("周五");
                break;
            case SATURDAY:
                System.out.println("周六");
                break;
            case SUNDAY:
                System.out.println("周日");
                break;
            default:
                System.out.println("WRONG");
        }
    }
}
