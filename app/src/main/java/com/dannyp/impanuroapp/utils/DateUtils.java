package com.dannyp.impanuroapp.utils;

import android.content.Context;
import android.net.ParseException;
import android.util.Log;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
// 2021-04-29
    public static int getExactDateNumber(String date) throws Exception{
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
      return   sd.parse(date).getDate();
    }

    public static int getExactMonthNumber(String date) throws Exception{
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");

            return sd.parse(date).getMonth()+1;

    }

    public static String getExactDay(String date) throws Exception{
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
            sd.parse(date);
        return ""+ sd.parse(date).getDay();
    }
    public static String getExactMonthName(int month){
        String monthName="";
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");


        switch ( month) {
            case 1:
                monthName+="Mutarama";
                break;
            case 2:
                monthName+="Gashyantare";
                break;
            case 3:
                monthName+="Werurwe";
                break;
            case 4:
                monthName+="Mata";
                break;
            case 5:
                monthName+="Gicurasi";
                break;
            case 6:
                monthName+="Kamena";
                break;
            case 7:
                monthName+="Nyakanga";
                break;
            case 8:
                monthName+="Kamena";
                break;
            case 9:
                monthName+="Nzeli";
                break;
            case 10:
                monthName+="Ukwakira";
                break;
            case 11:
                monthName+="Ugushyingo";
                break;
            case 12:
                monthName+="Ukuboza";
                break;
        }


        return monthName;
    }

    public static String getExactMonthNameFromDate(String date)throws Exception{
        String monthName="";
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");


        switch (sd.parse(date).getMonth()+1) {
            case 1:
                monthName+="Mutarama";
                break;
            case 2:
                monthName+="Gashyantare";
                break;
            case 3:
                monthName+="Werurwe";
                break;
            case 4:
                monthName+="Mata";
                break;
            case 5:
                monthName+="Gicurasi";
                break;
            case 6:
                monthName+="Kamena";
                break;
            case 7:
                monthName+="Nyakanga";
                break;
            case 8:
                monthName+="Kamena";
                break;
            case 9:
                monthName+="Nzeli";
                break;
            case 10:
                monthName+="Ukwakira";
                break;
            case 11:
                monthName+="Ugushyingo";
                break;
            case 12:
                monthName+="Ukuboza";
                break;
        }


        return monthName;
    }

    public static String getMonthForQuery(int month){
        if(month<10){
            return "0"+month;
        }
        else {
            return String.valueOf(month);
        }
    }
}
