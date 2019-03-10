package com.arkainfoteck.helpmate.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.arkainfoteck.helpmate.Activitys.OrderHistory;
import com.arkainfoteck.helpmate.Model.JobHistoryModel;
import com.arkainfoteck.helpmate.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class JobHistoryAdapter extends RecyclerView.Adapter<JobHistoryAdapter.ViewHolder> {
    Context context;
    ArrayList<JobHistoryModel>jobHistoryModels;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String pname1,type;

    public JobHistoryAdapter(Context context, ArrayList<JobHistoryModel> jobHistoryModels) {
        this.context = context;
        this.jobHistoryModels = jobHistoryModels;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater layoutInflater=LayoutInflater.from(context);
        View view=layoutInflater.inflate(R.layout.jobhistory,viewGroup,false);
        return new ViewHolder(view,context,jobHistoryModels);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        JobHistoryModel jobHistoryModel=jobHistoryModels.get(i);
        viewHolder.bookingid.setText(jobHistoryModel.getMaid_book_id());
        viewHolder.timings.setText(jobHistoryModel.getTimeing());
        viewHolder.date.setText(jobHistoryModel.getDates());
        viewHolder.typeperson.setText(jobHistoryModel.getType());
        viewHolder.noofmaids.setText(jobHistoryModel.getNo_maids());
        viewHolder.price.setText(jobHistoryModel.getNet_price());
        viewHolder.cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"we will revert back your payment in 7 working days",Toast.LENGTH_LONG).show();

                viewHolder.cancel.setText("Job Cancelled");
            }
        });
        Picasso.with(context)
                .load("http://broomsticks.in/assets/ns/"+jobHistoryModel.getImage())
                .into(viewHolder.personimage);
    }
    @Override
    public int getItemCount() {
        return jobHistoryModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener {
        Context context;
        List<JobHistoryModel>jobHistoryModels;
        TextView bookingid,timings,date,typeperson,noofmaids,price;
        ImageView personimage;
        Button cancel;
        public ViewHolder(@NonNull View itemView,Context context,List<JobHistoryModel>jobHistoryModels) {
            super(itemView);
            this.context=context;
            this.jobHistoryModels=jobHistoryModels;
            itemView.setClickable(true);
            itemView.setOnClickListener(this);
            bookingid=itemView.findViewById(R.id.bookingid);
            timings=itemView.findViewById(R.id.timingsslot);
            date=itemView.findViewById(R.id.Date);
            typeperson=itemView.findViewById(R.id.type);
            noofmaids=itemView.findViewById(R.id.noofmaids);
            price=itemView.findViewById(R.id.cost);
            personimage=itemView.findViewById(R.id.personimage);
            cancel=(Button)itemView.findViewById(R.id.cancel);

        }

        @Override
        public void onClick(View v) {
            int position=getAdapterPosition();

            Intent intent=new Intent(this.context, OrderHistory.class);
            sharedPreferences=context.getSharedPreferences("bookingid",Context.MODE_PRIVATE);
            editor = sharedPreferences.edit();
            int i=getAdapterPosition();
            JobHistoryModel jobHistoryModel=jobHistoryModels.get(i);
            pname1=jobHistoryModel.getMaid_book_id();
            type=jobHistoryModel.getType();
            editor.putString("booking",pname1);
            editor.putString("type",type);
            editor.apply();
            editor.commit();
            System.out.print("asdfbgg"+pname1);

            this.context.startActivity(intent);

        }
    }
}
