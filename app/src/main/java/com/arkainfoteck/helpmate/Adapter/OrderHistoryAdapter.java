package com.arkainfoteck.helpmate.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.arkainfoteck.helpmate.Model.OrderHistoryModel;
import com.arkainfoteck.helpmate.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class OrderHistoryAdapter extends RecyclerView.Adapter<OrderHistoryAdapter.ViewHolder> {
    Context context;
    ArrayList<OrderHistoryModel>orderHistoryModels;

    public OrderHistoryAdapter(Context context, ArrayList<OrderHistoryModel> orderHistoryModels) {
        this.context = context;
        this.orderHistoryModels = orderHistoryModels;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        View view=layoutInflater.inflate(R.layout.orderhistoryadapter,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        OrderHistoryModel orderHistoryModel=orderHistoryModels.get(i);
        viewHolder.name.setText(orderHistoryModel.getName());
        viewHolder.phone.setText(orderHistoryModel.getPhone());
        viewHolder.active.setText(orderHistoryModel.getActive());
        Picasso.with(context)
                .load("http://arkainfoteck.xyz/helpmate/dynamic/assets/image/"+orderHistoryModel.getImage())
                .into(viewHolder.image);

    }

    @Override
    public int getItemCount() {
        return orderHistoryModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name,phone,active;
        ImageView image;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image=itemView.findViewById(R.id.productimage);
            name=itemView.findViewById(R.id.name);
            phone=itemView.findViewById(R.id.phone);
            active=itemView.findViewById(R.id.active);

        }
    }
}
