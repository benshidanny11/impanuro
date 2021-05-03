package com.dannyp.impanuroapp.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dannyp.impanuroapp.DisplayAdviceActivity;
import com.dannyp.impanuroapp.R;
import com.dannyp.impanuroapp.items.AdviceItem;
import com.dannyp.impanuroapp.utils.DateUtils;
import com.github.lzyzsd.randomcolor.RandomColor;
import com.vstechlab.easyfonts.EasyFonts;

import java.util.ArrayList;

public class AdviceAdapter extends RecyclerView.Adapter<AdviceAdapter.AdviceHolder> {

    private ArrayList<AdviceItem> adviceItems;
    private Context context;

    public AdviceAdapter(ArrayList<AdviceItem> adviceItems, Context context) {
        this.adviceItems = adviceItems;
        this.context = context;
    }

    @NonNull
    @Override
    public AdviceHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AdviceHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.advice_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull AdviceHolder holder, int position) {
        try {
            RandomColor randomColor = new RandomColor();
            AdviceItem item = adviceItems.get(position);
            holder.txtAdviceTitle.setText(item.getTitlte());
            holder.txtAdviceDate.setText("Impanuro kumunsi wa "+ DateUtils.getExactDateNumber(item.getDate())+":");
            if (position % 2 == 1) {

                holder.txtAdviceDay.setText("Subscribed");
                holder.txtAdviceDay.setEnabled(false);
                holder.txtAdviceDay.setBackgroundColor(context.getResources().getColor(R.color.colorSeparator));
            }
//      holder.txtAdviceDay.setText(item.getDay());
//      holder.txtAdviceDay.setTypeface(EasyFonts.robotoRegular(context));
//      holder.txtAdviceDay.setTextColor(randomColor.randomColor());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    try {
                        Intent intent=new Intent(context, DisplayAdviceActivity.class);
                        intent.putExtra("title",item.getTitlte());
                        intent.putExtra("content",item.getDescription());
                        intent.putExtra("image_link",item.getImageLink());
                        intent.putExtra("comment",item.getComment());
                        intent.putExtra("date",DateUtils.getExactDateNumber(item.getDate()));
                        intent.putExtra("month_name",DateUtils.getExactMonthNameFromDate(item.getDate()));
                        ((Activity)context).startActivity(intent);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                }
            });
        }catch (Exception e){
            Toast.makeText(context, "Error "+e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public int getItemCount() {
        return adviceItems.size();
    }

    class AdviceHolder extends RecyclerView.ViewHolder {
        TextView txtAdviceTitle, txtAdviceDate;
        Button txtAdviceDay;

        public AdviceHolder(@NonNull View itemView) {
            super(itemView);
            txtAdviceTitle = (TextView) itemView.findViewById(R.id.txt_advice_title_in_item);
            txtAdviceDate = (TextView) itemView.findViewById(R.id.txt_advice_date_in_item);
            txtAdviceDay = (Button) itemView.findViewById(R.id.txt_advice_day_in_item);
        }
    }
}
