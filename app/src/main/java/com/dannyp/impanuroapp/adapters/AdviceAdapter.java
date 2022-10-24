package com.dannyp.impanuroapp.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.recyclerview.widget.RecyclerView;
import com.dannyp.impanuroapp.DisplayAdviceActivity;
import com.dannyp.impanuroapp.R;
import com.dannyp.impanuroapp.items.AdviceItem;
import com.dannyp.impanuroapp.utils.DateUtils;
import com.github.lzyzsd.randomcolor.RandomColor;
import java.util.ArrayList;

public class AdviceAdapter extends RecyclerView.Adapter<AdviceAdapter.AdviceHolder> {
    private ArrayList<AdviceItem> adviceItems;
    /* access modifiers changed from: private */
    public Context context;

    public AdviceAdapter(ArrayList<AdviceItem> adviceItems2, Context context2) {

        this.adviceItems = adviceItems2;
        this.context = context2;
    }

    public AdviceHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new AdviceHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.advice_item, parent, false));
    }

    public void onBindViewHolder(AdviceHolder holder, int position) {
        try {

            new RandomColor();
            final AdviceItem item = this.adviceItems.get(position);
            holder.txtAdviceTitle.setText(item.getTitlte());
            TextView textView = holder.txtAdviceDate;
            textView.setText("Impanuro ku itariki ya 1" + DateUtils.getExactDateNumber(item.getDate()) + ":");
            holder.txtAdviceDay.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.setAction("android.intent.action.SEND");
                    intent.putExtra("android.intent.extra.TEXT", "From Emma Rwanda\n\n" + item.getTitlte().concat("\n\n\n").concat(item.getTitlte()));
                    intent.setType("text/plain");
                    AdviceAdapter.this.context.startActivity(intent);
                }
            });
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    try {
                        Intent intent = new Intent(AdviceAdapter.this.context, DisplayAdviceActivity.class);
                        intent.putExtra("title", item.getTitlte());
                        intent.putExtra("image_link", item.getImageLink());
                        intent.putExtra("advice",item.getAdvice());
                        intent.putExtra("date", DateUtils.getExactDateNumber(item.getDate()));
                        intent.putExtra("month_name", DateUtils.getExactMonthNameFromDate(item.getDate()));
                        ((Activity) AdviceAdapter.this.context).startActivity(intent);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (Exception e) {
            Context context2 = this.context;
            Toast.makeText(context2, "Error " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public int getItemCount() {
        return this.adviceItems.size();
    }

    class AdviceHolder extends RecyclerView.ViewHolder {
        TextView txtAdviceDate;
        ImageView txtAdviceDay;
        TextView txtAdviceTitle;

        public AdviceHolder(View itemView) {
            super(itemView);
            this.txtAdviceTitle = (TextView) itemView.findViewById(R.id.txt_advice_title_in_item);
            this.txtAdviceDate = (TextView) itemView.findViewById(R.id.txt_advice_date_in_item);
            this.txtAdviceDay = (ImageView) itemView.findViewById(R.id.btn_share_in_advice_item);
        }
    }
}
