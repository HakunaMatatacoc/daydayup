package com.jianli;

import lombok.ToString;

public enum Week {
    MONDAY(1,"星期一"),
    TUESDAY(2,"星期二"),
    WENDSDAY(3,"星期三"),
    THURSDAY(4,"星期四"),
    FRIDAY(5,"星期五"),
    SATURDAY(6,"星期六"),
    SUNDAY(7,"星期日");

    private final int weekday;
    private final String weekname;

    Week(int weekday,String weekname){
        this.weekday = weekday;
        this.weekname = weekname;
    }

    @Override
    public String toString() {
        return weekname;
    }
}
