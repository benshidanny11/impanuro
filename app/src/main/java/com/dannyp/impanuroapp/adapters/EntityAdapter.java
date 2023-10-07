package com.dannyp.impanuroapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.dannyp.impanuroapp.R;
import com.dannyp.impanuroapp.ViewEntityActivity;
import com.dannyp.impanuroapp.items.EntityItem;
import java.util.List;

public class EntityAdapter extends RecyclerView.Adapter<EntityAdapter.EntityHolder> {

    private Context context;
    private List<EntityItem> entityItems;

    private String entityType;

    public EntityAdapter(Context context,List<EntityItem> entityItems, String entityType) {
        this.context=context;
        this.entityItems=entityItems;
        this.entityType=entityType;
    }

    @NonNull
    @Override
    public EntityHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new EntityHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.entity_itemm, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull EntityHolder holder, int position) {
        final EntityItem item = entityItems.get(position);
        holder.txtTitle.setText(item.getTitle());
        holder.txtDescription.setText(item.getDescription());
        holder.txtDate.setText(item.getDate());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, ViewEntityActivity.class);
                intent.putExtra("entity_type",entityType);
                intent.putExtra("title",item.getTitle());
                intent.putExtra("description",item.getDescription());
                intent.putExtra("eid",item.getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return entityItems.size();
    }

    class EntityHolder extends RecyclerView.ViewHolder{
        TextView txtTitle, txtDescription, txtDate;
        public EntityHolder(@NonNull View itemView) {
            super(itemView);
            txtTitle=itemView.findViewById(R.id.txt_entity_title_in_item);
            txtDescription=itemView.findViewById(R.id.txt_entity_short_description);
            txtDate=itemView.findViewById(R.id.txt_entity_date_in_item);
        }
    }
}
