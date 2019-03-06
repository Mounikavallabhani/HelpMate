package com.arkainfoteck.helpmate.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.arkainfoteck.helpmate.Model.MenuModel;
import com.arkainfoteck.helpmate.R;

import java.util.ArrayList;

public class MenuAdapters extends RecyclerView.Adapter<MenuAdapters.ViewHolder> {
    Context context;
    ArrayList<MenuModel> jobHistoryModels;

    public MenuAdapters(Context context, ArrayList<MenuModel> jobHistoryModels) {
        this.context = context;
        this.jobHistoryModels = jobHistoryModels;
    }

    @NonNull
    @Override
    public MenuAdapters.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater layoutInflater=LayoutInflater.from(context);
        View view=layoutInflater.inflate(R.layout.menu_items_list,viewGroup,false);
        return new MenuAdapters.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuAdapters.ViewHolder viewHolder, int i) {
        MenuModel jobHistoryModel=jobHistoryModels.get(i);
        viewHolder.textView1.setText(jobHistoryModel.getName());
        viewHolder.imageView.setImageResource(jobHistoryModel.getImage());
    }

    @Override
    public int getItemCount() {
        return jobHistoryModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView1;
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView1=itemView.findViewById(R.id.menuname);
            imageView=itemView.findViewById(R.id.menuimage);


        }
    }
}
