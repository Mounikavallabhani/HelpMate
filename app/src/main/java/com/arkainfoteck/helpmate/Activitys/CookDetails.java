package com.arkainfoteck.helpmate.Activitys;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.arkainfoteck.helpmate.R;

import java.util.ArrayList;

public class CookDetails extends AppCompatActivity {
    LinearLayout next;
    Button hdecrease,cdecrease,hincrease,cincrease;
    TextView add,hadd,cadd,regular,parttime;
    int count = 1;
    int counts=1;
    int countss=1;
    RadioGroup veg;
    RadioButton vegbutton;
    int selectedId;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    TextView details,hours,cooks;
    LinearLayout linearLayout;
    String cookdetails="";
    String regilar_parttime="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cook_details);

        next=(LinearLayout)findViewById(R.id.next);

        hdecrease=(Button) findViewById(R.id.hdecrease);
        cdecrease=(Button) findViewById(R.id.cdecrease);

        hincrease=(Button) findViewById(R.id.hincrease);
        cincrease=(Button) findViewById(R.id.cincrease);
        details=(TextView)findViewById(R.id.details);
        hours=(TextView)findViewById(R.id.hours);
        cooks=(TextView)findViewById(R.id.cooks);
        linearLayout=(LinearLayout)findViewById(R.id.linearlayout);
        parttime=(TextView)findViewById(R.id.parttime);
        regular=(TextView)findViewById(R.id.regular);

        parttime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parttime.setBackgroundResource(R.drawable.selectitemshape);
                regular.setBackgroundResource(R.drawable.timeshape);
                regilar_parttime= "Part time";
            }
        });

        regular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                regular.setBackgroundResource(R.drawable.selectitemshape);
                parttime.setBackgroundResource(R.drawable.timeshape);
                regilar_parttime= "Regular";
            }
        });
        cookdetails=""+getIntent().getStringExtra("cookdetails");
        System.out.println("edfgh"+cookdetails);
        String noofhours=""+getIntent().getStringExtra("noofhours");
        String noofcooks=""+getIntent().getStringExtra("noofcooks");
        try {

            details.setText(""+cookdetails);
            hours.setText(noofhours);
            cooks.setText(noofcooks);
        }catch (Exception e){

        }

        try {

            if(cookdetails.equals("Cook Details"))
            {
                linearLayout.setVisibility(View.VISIBLE);

            }
        }catch (Exception e){

        }

        hadd=(TextView)findViewById(R.id.hadd);
        cadd=(TextView)findViewById(R.id.cadd);


        try {

            cincrease.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    counts++;
                    cadd.setText(String.valueOf(counts));

                }
            });

        }catch (Exception e){

        }

        try {

            cdecrease.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (counts > 1) {
                        counts--;
                        cadd.setText(String.valueOf(counts));
                    }

                }
            });

        }catch (Exception e){

        }

        try {

            hincrease.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    countss++;
                    hadd.setText(""+String.valueOf(countss));

                }
            });
        }catch (Exception e){

        }
        try {

            hdecrease.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (countss > 1) {
                        countss--;
                        hadd.setText(""+String.valueOf(countss));
                    }
                }
            });
        }catch (Exception e){

        }
        try {

            next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    System.out.println("regular_time"+regilar_parttime);

                    if (cookdetails.equals("Cook Details")) {

                        if (veg.getCheckedRadioButtonId() == -1) {
                            Toast.makeText(getApplicationContext(), "Please select Cooktype", Toast.LENGTH_SHORT).show();
                        } else if(regilar_parttime.equals("")) {
                            Toast.makeText(getApplicationContext(), "Please select type", Toast.LENGTH_SHORT).show();

                        }else {
                                Intent intent = new Intent(CookDetails.this, TimeSlot.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                sharedPreferences = getSharedPreferences("cookdetails", Context.MODE_PRIVATE);
                                editor = sharedPreferences.edit();
                                editor.putString("hours", hadd.getText().toString());
                                editor.putString("cooks", cadd.getText().toString());
                                editor.putString("regilar_parttime",regilar_parttime);
                                editor.apply();
                                editor.commit();

                                startActivity(intent);
                            }
                       } else if(regilar_parttime.equals("")) {
                        Toast.makeText(getApplicationContext(), "Please select type", Toast.LENGTH_SHORT).show();

                       }else {
                            Intent intent = new Intent(CookDetails.this, TimeSlotMade.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);

                            sharedPreferences = getSharedPreferences("cookdetails", Context.MODE_PRIVATE);
                            editor = sharedPreferences.edit();
                            editor.putString("hours", hadd.getText().toString());
                            editor.putString("cooks", cadd.getText().toString());
                            editor.putString("regilar_parttime",regilar_parttime);
                            editor.apply();
                            editor.commit();

                            startActivity(intent);
                        }
                    }
            });
        }catch (Exception e)
        {
                 e.printStackTrace();
        }
        try {

            veg=(RadioGroup)findViewById(R.id.veg);


            vegbutton = (RadioButton) findViewById(selectedId);

            veg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {

                    final View radioButton = veg.findViewById(checkedId);
                    selectedId = veg.indexOfChild(radioButton);

                    radioButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            int selectdataId = veg .getCheckedRadioButtonId();
                            vegbutton=findViewById(selectdataId);

                            String    strmehhire=vegbutton.getText().toString();
                            SharedPreferences sharedPreferences=getSharedPreferences("cooktype",Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("cooktypedetails",strmehhire);
                            editor.apply();
                            editor.commit();

                        }
                    });


                }
            });
        }catch (Exception e){

        }
    }
}

//
