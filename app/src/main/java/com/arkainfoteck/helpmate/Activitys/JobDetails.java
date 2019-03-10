package com.arkainfoteck.helpmate.Activitys;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.arkainfoteck.helpmate.R;

import org.json.JSONObject;

import java.util.ArrayList;

public class JobDetails extends AppCompatActivity {
    LinearLayout paynow;
    TextView price,totalprice,subtotal,discount,taxs,cookname;
    ImageView back;
    SharedPreferences sharedPreferences,sharedPreferences1,sharedPreferences2;
    SharedPreferences.Editor editor1;
    String fooddetails;
    TextView cooktype,jobhours,jobcooks,jobdate,jobtime;
    String totalmoney;
    LinearLayout linearcook;
    DatabaseHelper database;
    ArrayList<DataModel> datamodel;
    String time_slats="";
    LinearLayout applycoupon;
    String timedata;
    String regilar_parttime_type;
    TextView type_of_delivary;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_details);
//        price=(TextView)findViewById(R.id.price);
        type_of_delivary=(TextView)findViewById(R.id.type_of_delivary);
        applycoupon=(LinearLayout)findViewById(R.id.applycoupon);
        totalprice=(TextView)findViewById(R.id.totalprice);
        subtotal=(TextView)findViewById(R.id.subtotal);
        discount=(TextView)findViewById(R.id.discount);
        taxs=(TextView)findViewById(R.id.taxs);
        back=(ImageView)findViewById(R.id.back);
        cooktype=(TextView)findViewById(R.id.cooktype);
        jobhours=(TextView)findViewById(R.id.jobhours);
        jobcooks=(TextView)findViewById(R.id.jobcook);
        jobdate=(TextView)findViewById(R.id.jobdate);
        jobtime=(TextView)findViewById(R.id.jobtime);
        linearcook=(LinearLayout)findViewById(R.id.linearcook);

        cookname=(TextView)findViewById(R.id.cookname);
        applycoupon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(JobDetails.this,"No Coupons Avalable Right Now",Toast.LENGTH_LONG).show();
            }
        });

        // hours and cooks
        sharedPreferences=getSharedPreferences("cookdetails",Context.MODE_PRIVATE);
        String shours=sharedPreferences.getString("hours",null);
        String scooks=sharedPreferences.getString("cooks",null);
        regilar_parttime_type=sharedPreferences.getString("regilar_parttime",null);

         System.out.print("get_find_type_tiii"+regilar_parttime_type+""+shours);

        jobhours.setText(shours);
        jobcooks.setText(scooks);
        type_of_delivary.setText(regilar_parttime_type);

        if(regilar_parttime_type.equals("Regular")){
            datamodel=new ArrayList<>();
            database = new DatabaseHelper(JobDetails.this);
            datamodel=  database.gettimedata();
            for(int i=0;i<datamodel.size();i++){
                time_slats+= datamodel.get(i).getTime_slat();
                timedata=datamodel.get(0).getTime_slat();

                System.out.println("whatgetting"+time_slats);
            }
            jobtime.setText(timedata);


        }else {
            sharedPreferences2 =getSharedPreferences("part_time_details",Context.MODE_PRIVATE);
            String timeslot_full_dates=sharedPreferences2.getString("time_slots",null);
            jobtime.setText(timeslot_full_dates);


        }
        // date
        sharedPreferences =getSharedPreferences("datedetails",Context.MODE_PRIVATE);
        String date=sharedPreferences.getString("date",null);
        System.out.println("get_bocking_date"+date);
        jobdate.setText(date);

        SharedPreferences sharedPreferences=getSharedPreferences("timeslot",Context.MODE_PRIVATE);
        String time=sharedPreferences.getString("time",null);


        // here product cost will get
        sharedPreferences =getSharedPreferences("Nameoftype",Context.MODE_PRIVATE);
        String productcost=sharedPreferences.getString("productcost",null);
        System.out.println("kjkfghjk"+date);


        sharedPreferences =getSharedPreferences("Nameoftype",Context.MODE_PRIVATE);
        String nameofimage=sharedPreferences.getString("nameofimage",null);
        cookname.setText(nameofimage);
        System.out.println("kjkfghjk"+date);

        Float hours=Float.parseFloat(shours);
        Float cooks=Float.parseFloat(scooks);

        double money=Double.parseDouble(productcost);

        // here you will get total hours with cost
        double hoursfinal=hours*money;
        System.out.println("totalhourscost"+hoursfinal);

        // here you will get final persons with cost
        double personsfinal=hoursfinal*cooks;
        System.out.println("personsfinal"+personsfinal);

        // converting total cost into string
        totalmoney=String.valueOf(personsfinal);




        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(JobDetails.this,TimeSlot.class);
                startActivity(intent);
            }
        });

//        price.setText("\u20B9 500.00");
        subtotal.setText("\u20B9"+personsfinal);
        discount.setText("\u20B9 0.00");
        taxs.setText("\u20B9 0.00");

        totalprice.setText("\u20B9 "+personsfinal);

        paynow=(LinearLayout)findViewById(R.id.paynow);
        paynow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sharedPreferences1=getSharedPreferences("totalamount",Context.MODE_PRIVATE);
                editor1 = sharedPreferences1.edit();
                editor1.putString("totalamount",totalmoney);
                editor1.apply();
                editor1.commit();
                Intent intent=new Intent(JobDetails.this,CheckOut.class);
                startActivity(intent);
            }
        });


        SharedPreferences sharedPreferences2=getSharedPreferences("Timeshedule",MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences2.edit();
        editor.putString("timeslats",jobtime.getText().toString());
        editor.apply();
        editor.commit();



    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        getApplicationContext().getSharedPreferences("cooktype", 0).edit().clear().commit();

    }
}
