package com.dannyp.impanuroapp.items;

public class MonthsItem {
    String monthName;
    int monthNumber;

    public MonthsItem(String monthName, int monthNumber) {
        this.monthName = monthName;
        this.monthNumber = monthNumber;
    }

    public String getMonthName() {
        return monthName;
    }

    public int getMonthNumber() {
        return monthNumber;
    }
}
