package com.dannyp.impanuroapp.utils;

import com.dannyp.impanuroapp.items.AdviceItem;
import java.util.Comparator;

public class AdviceComparatorUtil implements Comparator<AdviceItem> {
    @Override
    public int compare(AdviceItem o1, AdviceItem o2) {
        return Integer.compare(o1.getMonthNumber(), o2.getMonthNumber());
    }
}
