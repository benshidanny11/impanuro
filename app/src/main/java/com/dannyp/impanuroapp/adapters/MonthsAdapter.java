package com.dannyp.impanuroapp.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dannyp.impanuroapp.AdviceActivity;
import com.dannyp.impanuroapp.R;
import com.dannyp.impanuroapp.items.MonthsItem;
import com.dannyp.impanuroapp.utils.ColorUtil;
import com.dannyp.impanuroapp.utils.SharedPrefs;
import com.github.lzyzsd.randomcolor.RandomColor;
import com.vstechlab.easyfonts.EasyFonts;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MonthsAdapter  extends RecyclerView.Adapter<MonthsAdapter.MonthsHolder> {

    private ArrayList<MonthsItem> monthsItems;
    private Context context;

    public MonthsAdapter(ArrayList<MonthsItem> monthsItems, Context context) {
        this.monthsItems = monthsItems;
        this.context = context;
    }

    @NonNull
    @Override
    public MonthsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MonthsHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.months_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MonthsHolder holder, int position) {
      MonthsItem item=monthsItems.get(position);
      holder.txtMonthNumber.setText(""+item.getMonthNumber());
      holder.txtMonthName.setText(item.getMonthName());
      holder.txtMonthNumber.setTypeface(EasyFonts.robotoRegular(context));
      holder.txtMonthName.setTypeface(EasyFonts.robotoRegular(context));
      holder.txtMonthName.setTextColor(context.getResources().getColor(ColorUtil.getMonthColors()[position]));
      holder.itemView.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Intent intent=new Intent(context,AdviceActivity.class);
              intent.putExtra("month",item.getMonthNumber());
              SharedPrefs.setMonthData(context, item.getMonthNumber());
             // intent.putExtra("month_name",item.getMonthName());
              ((Activity)context).startActivity(intent);
          }
      });
    }

    @Override
    public int getItemCount() {
        return monthsItems.size();
    }

    class MonthsHolder extends RecyclerView.ViewHolder{
        TextView txtMonthNumber,txtMonthName;
        public MonthsHolder(@NonNull View itemView) {
            super(itemView);
            txtMonthName=(TextView)itemView.findViewById(R.id.txt_month__name_in_item);
            txtMonthNumber=(TextView)itemView.findViewById(R.id.txt_month_number_in_item);
        }
    }
}
