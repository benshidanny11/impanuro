package com.dannyp.impanuroapp.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.dannyp.impanuroapp.models.User;

public class SharedPrefs {

    public static void saveUserData(Context context, User user) {
        SharedPreferences sharedPref = context.getSharedPreferences("paymentprefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("id", user.getId());
        editor.putString("name", user.getName());
        editor.putString("email", user.getEmail());
        editor.putString("phonenumber", user.getPhoneNumber());
        editor.apply();
    }

    public static User getUserData(Context context) {
        SharedPreferences sharedPref = context.getSharedPreferences("paymentprefs", Context.MODE_PRIVATE);
        if (sharedPref != null) {
            User user = new User(sharedPref.getString("name", null), sharedPref.getString("email", null), sharedPref.getString("phonenumber", null));
            user.setId(sharedPref.getString("id", null));
            return user;
        } else {
            return null;
        }
    }

    public static void setMonthData(Context context, int monthNumber) {
        SharedPreferences sharedPref = context.getSharedPreferences("paymentprefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt("month",monthNumber);
        editor.apply();
    }

    public static int getMonthData(Context context) {
        SharedPreferences sharedPref = context.getSharedPreferences("paymentprefs", Context.MODE_PRIVATE);
       return sharedPref.getInt("month",0);
    }




}
