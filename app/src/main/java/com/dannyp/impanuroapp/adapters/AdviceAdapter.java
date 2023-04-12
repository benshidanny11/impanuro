package com.dannyp.impanuroapp.adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.dannyp.impanuroapp.DisplayAdviceActivity;
import com.dannyp.impanuroapp.PaymentActivity;
import com.dannyp.impanuroapp.R;
import com.dannyp.impanuroapp.items.AdviceItem;
import com.dannyp.impanuroapp.models.User;
import com.dannyp.impanuroapp.utils.DateUtils;
import com.dannyp.impanuroapp.utils.SharedPrefs;
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

    void navigateToAdviceDetails(AdviceItem item ) throws Exception {
        Intent intent = new Intent(AdviceAdapter.this.context, DisplayAdviceActivity.class);
        intent.putExtra("title", item.getTitlte());
        intent.putExtra("image_link", item.getImageLink());
        intent.putExtra("advice",item.getAdvice());
        intent.putExtra("date", DateUtils.getExactDateNumber(item.getDate()));
        intent.putExtra("month_name", DateUtils.getExactMonthNameFromDate(item.getDate()));
        ((Activity) AdviceAdapter.this.context).startActivity(intent);

    }

    @Override
    @NonNull
    public AdviceHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new AdviceHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.advice_item, parent, false));
    }
    public void onBindViewHolder(@NonNull AdviceHolder holder, int position) {
        try {
            new RandomColor();
            final AdviceItem item = adviceItems.get(position);
            User user=  SharedPrefs.getUserData(context);
            if(position<5){
                holder.txtAdviceStatus.setText("Free");
                holder.txtAdviceStatus.setTextColor(context.getResources().getColor(R.color.free));
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        try {
                            navigateToAdviceDetails(item);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }else{
                assert user != null;
                if(user.getId().length()>0 && user.getId().equals(item.getUserId())){
                    holder.txtAdviceStatus.setText("Paid");
                    holder.txtAdviceStatus.setTextColor(context.getResources().getColor(R.color.paid));
                    holder.itemView.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            try {
                                navigateToAdviceDetails(item);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
                } else {
                    holder.txtAdviceStatus.setText("Pay 100 RWF");
                    holder.txtAdviceStatus.setTextColor(context.getResources().getColor(R.color.pay));
                    // Implement payment activity navigation
                    holder.itemView.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            try {
                                Intent intent=new Intent(context, PaymentActivity.class);
                                intent.putExtra("adviceid", item.getAdviceId());
                                context.startActivity(intent);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }

            }
            holder.txtAdviceTitle.setText(item.getTitlte());
            TextView textView = holder.txtAdviceDate;
            textView.setText("Impanuro ku itariki ya " + DateUtils.getExactDateNumber(item.getDate()) + ":");
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
        TextView txtAdviceTitle;
        TextView txtAdviceStatus;
        public AdviceHolder(View itemView) {
            super(itemView);
            this.txtAdviceTitle = (TextView) itemView.findViewById(R.id.txt_advice_title_in_item);
            this.txtAdviceDate = (TextView) itemView.findViewById(R.id.txt_advice_date_in_item);
            this.txtAdviceStatus=(TextView) itemView.findViewById(R.id.txt_advice_status_in_item);
        }
    }
}
