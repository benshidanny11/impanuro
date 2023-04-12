package com.dannyp.impanuroapp.utils;

import com.dannyp.impanuroapp.items.AdviceItem;
import java.util.Comparator;

public class AdviceComparatorUtil implements Comparator<AdviceItem> {
    @Override
    public int compare(AdviceItem o1, AdviceItem o2) {
        try {
            return Integer.compare(DateUtils.getExactDateNumber(o1.getDate()), DateUtils.getExactDateNumber(o2.getDate()));
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
