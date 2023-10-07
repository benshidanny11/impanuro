package com.dannyp.impanuroapp.dumydata;

import java.util.ArrayList;

import com.dannyp.impanuroapp.items.MonthsItem;

public class MonthsData {

    public static ArrayList<MonthsItem> getMothsData(){
        String[] months={"Mutarama","Gashyantare","Werurwe","Mata","Gicurasi","Kamena","Nyakanga","Kanama","Nzeli","Ukwakira","Ugushyingo","Ukuboza"};
        ArrayList<MonthsItem> monthsItems=new ArrayList<>();
        for(int i=0;i<months.length;i++){
            monthsItems.add(new MonthsItem(months[i],i+1));
        }
        return monthsItems;
    }
}
