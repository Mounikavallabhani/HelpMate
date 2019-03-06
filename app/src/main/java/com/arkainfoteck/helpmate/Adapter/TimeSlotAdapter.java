package com.arkainfoteck.helpmate.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.arkainfoteck.helpmate.Activitys.DatabaseHelper;
import com.arkainfoteck.helpmate.Model.TimeSlotModel;
import com.arkainfoteck.helpmate.R;

import java.util.ArrayList;

public class TimeSlotAdapter  extends RecyclerView.Adapter<TimeSlotAdapter.ViewHolder> {

    Context context;
    ArrayList<TimeSlotModel>timeSlotModels;
    String hours;
    DatabaseHelper databaseHelper;
    public static   int counthours=1;
    public static int counttime=0;

    TimeSlotModel timeSlotModel;
    public  TimeSlotAdapter(Context context, ArrayList<TimeSlotModel> timeSlotModels, String hours, DatabaseHelper databaseHelper) {
        this.context = context;
        this.timeSlotModels = timeSlotModels;
        this.hours=hours;
        this.counthours=1;
        this.counttime=1;
        this.databaseHelper= databaseHelper;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        View view=layoutInflater.inflate(R.layout.timeslotadapter,viewGroup,false);
        return new ViewHolder(view,context,timeSlotModels,hours,databaseHelper);
        }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        timeSlotModel=timeSlotModels.get(i);
        viewHolder.timeslot.setText(timeSlotModel.getTime());

    }

    @Override
    public int getItemCount() {
        return timeSlotModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView timeslot;
        LinearLayout lineartime;
        ArrayList<TimeSlotModel>timeSlotModels;
        DatabaseHelper databaseHelper;
        Context context;
        int hours;
        TimeSlotModel timeSlotModel;



        public ViewHolder(@NonNull View itemView, Context context, ArrayList<TimeSlotModel>timeSlotModels, String hours, DatabaseHelper databaseHelper) {
            super(itemView);
            this.context=context;
            this.timeSlotModels=timeSlotModels;
            this.hours=Integer.parseInt(hours);
            this. databaseHelper= databaseHelper;
            itemView.setClickable(true);
            itemView.setOnClickListener(this);

            timeslot=itemView.findViewById(R.id.timeslot);
            lineartime=(LinearLayout)itemView.findViewById(R.id.lineartime);
        }

        @Override
        public void onClick(View v) {
            counttime++;

           if(counthours<=hours){

              counthours++;

               System.out.println("counthours"+counthours);
               System.out.print("counttime"+counttime);
               System.out.println("hours"+hours);

               lineartime.setBackgroundResource(R.drawable.selectitemshape);
               int position=getAdapterPosition();
               timeSlotModel=this.timeSlotModels.get(position);

              /// Toast.makeText(context,"You Selected "+timeSlotModel.getTime(),Toast.LENGTH_LONG).show();

              long count = databaseHelper.inserttimedata(timeSlotModel.getTime()+",");
              //Toast.makeText(context,"You Selected "+count,Toast.LENGTH_LONG).show();
               System.out.println("getcount"+count);
           }else {
          Toast.makeText(context,"You Selected "+hours+" only",Toast.LENGTH_LONG).show();

           }
        }
    }

}
