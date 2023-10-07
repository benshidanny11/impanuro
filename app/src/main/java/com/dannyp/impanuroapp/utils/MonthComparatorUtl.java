package com.dannyp.impanuroapp.utils;

import com.dannyp.impanuroapp.items.MonthsItem;

import java.util.Comparator;

public class MonthComparatorUtl implements Comparator<MonthsItem> {


    @Override
    public int compare(MonthsItem o1, MonthsItem o2) {
        return Integer.compare(o1.getMonthNumber(), o2.getMonthNumber());
    }
}
