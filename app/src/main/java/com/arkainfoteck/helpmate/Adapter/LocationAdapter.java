package com.arkainfoteck.helpmate.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.arkainfoteck.helpmate.Activitys.DashBoard;
import com.arkainfoteck.helpmate.Activitys.DataModel;
import com.arkainfoteck.helpmate.R;

import java.util.ArrayList;

public class LocationAdapter extends RecyclerView.Adapter<LocationAdapter.ViewHolder> {
    Context context;
    ArrayList<DataModel> dataModels;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    public LocationAdapter(Context context, ArrayList<DataModel> dataModels) {
        this.context = context;
        this.dataModels = dataModels;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        View view=layoutInflater.inflate(R.layout.location,viewGroup,false);
        return new LocationAdapter.ViewHolder(view,context,dataModels);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        DataModel dataModel=dataModels.get(i);
        viewHolder.type_delivery.setText(dataModel.getName());
        Toast.makeText(context,dataModel.getName(),Toast.LENGTH_LONG).show();
        viewHolder.type_contact.setText(dataModel.getNear_by_place()+","+dataModel.getLocality()+","+dataModel.getApparent_house());

    }

    @Override
    public int getItemCount() {
        return  dataModels.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView type_contact,type_delivery;
        Context context;
        ArrayList<DataModel> dataModels;

        public ViewHolder(@NonNull View itemView, Context context, ArrayList<DataModel> dataModels) {
            super(itemView);
            this.context=context;
            this.dataModels=dataModels;
            itemView.setClickable(true);
            itemView.setOnClickListener(this);

            type_contact=(TextView)itemView.findViewById(R.id.type_contact);
            type_delivery=(TextView)itemView.findViewById(R.id.type_delivery);

        }
        @Override
        public void onClick(View v) {

            int position=getAdapterPosition();
            DataModel modelMilk=this.dataModels.get(position);
            Intent intent=new Intent(this.context, DashBoard.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);



            sharedPreferences=context.getSharedPreferences("AddressDetails",Context.MODE_PRIVATE);
            editor = sharedPreferences.edit();
            editor.putString("location",modelMilk.getNear_by_place()+","+modelMilk.getLocality()+","+modelMilk.getApparent_house());
            System.out.println("house_name"+modelMilk.getApparent_house()+"Apparemt_house"+modelMilk.getLocality());
            editor.commit();
            editor.apply();

            this.context.startActivity(intent);


        }

    }
 }