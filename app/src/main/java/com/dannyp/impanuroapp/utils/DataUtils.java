package com.dannyp.impanuroapp.utils;

import android.content.Context;
import android.widget.Toast;

import com.dannyp.impanuroapp.items.AdviceItem;
import com.dannyp.impanuroapp.items.MonthsItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;

public class DataUtils {
    public static ArrayList<MonthsItem> removeDuplicates(JSONArray jsonArray) {

        ArrayList<MonthsItem> dupFreeMonthItems = new ArrayList<>();
        ArrayList<Integer> months = new ArrayList<>();
        ArrayList<Integer> monthsDupFree = new ArrayList<>();
        try {
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jSONObject = jsonArray.getJSONObject(i);
                months.add(DateUtils.getExactMonthNumber(jSONObject.getString("adv_date")));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (int i = 0; i < months.size(); i++) {
            if (!monthsDupFree.contains(months.get(i))) {
                monthsDupFree.add(months.get(i));
            }
        }

        for (int i = 0; i < monthsDupFree.size(); i++) {
            dupFreeMonthItems.add(new MonthsItem(DateUtils.getExactMonthName(monthsDupFree.get(i)), monthsDupFree.get(i)));
        }
        Collections.sort(dupFreeMonthItems,new MonthComparatorUtl());
        return dupFreeMonthItems;
    }

    public static ArrayList<AdviceItem> getAdviceList(JSONArray jsonArray) {

        ArrayList<AdviceItem> adviceItems = new ArrayList<>();
        try {
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jSONObject = jsonArray.getJSONObject(i);
                    adviceItems.add(new AdviceItem(DateUtils.getExactDateNumber(jSONObject.getString("adv_date")),jSONObject.getString("adv_date"),jSONObject.getString("adv_title"),jSONObject.getString("adv_body"),jSONObject.getString("status"),jSONObject.getString("comment"),jSONObject.getString("adv_feature_img")));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Collections.sort(adviceItems,new AdviceComparatorUtil());
        return adviceItems;
    }
}
