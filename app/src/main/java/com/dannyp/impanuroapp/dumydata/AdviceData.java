package com.dannyp.impanuroapp.dumydata;

import com.dannyp.impanuroapp.items.AdviceItem;

import java.util.ArrayList;

public class AdviceData {

    public static ArrayList<AdviceItem> getAdviceData(){
        String[] days={"Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"};

        int count=0;
        ArrayList<AdviceItem> adviceItems=new ArrayList<>();
        for(int i=1;i<32;i++){
            adviceItems.add(new AdviceItem(days[count],i+"/2/"+"2021","Advice for day "+i,"","",""));
            count++;
            if(count==days.length){
                count=0;
            }
        }
       return adviceItems;
    }

}
