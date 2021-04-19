package com.dannyp.impanuroapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dannyp.impanuroapp.R;
import com.dannyp.impanuroapp.items.AdviceItem;
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
        RandomColor randomColor = new RandomColor();
      AdviceItem item=adviceItems.get(position);
      holder.txtAdviceTitle.setText(item.getTitlte());
      holder.txtAdviceDate.setText(item.getDate());
      holder.txtAdviceDay.setText(item.getDay());
        holder.txtAdviceDay.setTypeface(EasyFonts.robotoRegular(context));
        holder.txtAdviceDay.setTextColor(randomColor.randomColor());
    }

    @Override
    public int getItemCount() {
        return adviceItems.size();
    }

    class AdviceHolder extends RecyclerView.ViewHolder{
        TextView txtAdviceTitle,txtAdviceDate,txtAdviceDay;
        public AdviceHolder(@NonNull View itemView) {
            super(itemView);
            txtAdviceTitle=(TextView)itemView.findViewById(R.id.txt_advice_title_in_item);
            txtAdviceDate=(TextView)itemView.findViewById(R.id.txt_advice_date_in_item);
            txtAdviceDay=(TextView)itemView.findViewById(R.id.txt_advice_day_in_item);
        }
    }
}
