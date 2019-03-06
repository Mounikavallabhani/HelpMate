package com.arkainfoteck.helpmate.Activitys;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.arkainfoteck.helpmate.Adapter.JobHistoryAdapter;
import com.arkainfoteck.helpmate.Adapter.TimeSlotAdapter;
import com.arkainfoteck.helpmate.Model.JobHistoryModel;
import com.arkainfoteck.helpmate.Model.TimeSlotModel;
import com.arkainfoteck.helpmate.R;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static com.arkainfoteck.helpmate.Adapter.TimeSlotAdapter.counthours;
import static com.arkainfoteck.helpmate.Adapter.TimeSlotAdapter.counttime;
import static com.arkainfoteck.helpmate.R.drawable.buttonshape;

public class TimeSlot extends AppCompatActivity {
        LinearLayout next;
    TextView today,todate,tomorrowday,tomorrowdate,dayafterday,dayafterdate,later,displaydate;
    public DatePickerDialog fromDatePickerDialog;
    private SimpleDateFormat dateFormatter;
    TimeSlotAdapter timeSlotAdapter;
    ArrayList<TimeSlotModel>timeSlotModels;
    GridLayoutManager gridLayoutManager;
    LinearLayout lineartoday,lineartomorrow,lineardayaftertomorrow,linearfinalday;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String hours,cooks;
    String year,year1,year2;
    int hoursint;
    View view;

    String time,miniites;

    SharedPreferences sharedPreferences1,sharedPreferences2;
    SharedPreferences.Editor editor1,editor2;
    String Currentdate,currendate1,currentdate2;
    int count=1;
    DatabaseHelper databaseHelper;
    EditText special_instractions;
    LinearLayout totallineralayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_slot);

        dateFormatter = new SimpleDateFormat("dd/mm/yyyy", Locale.US);

        today=(TextView)findViewById(R.id.today);
        todate=(TextView)findViewById(R.id.todate);
        tomorrowday=(TextView)findViewById(R.id.tomorrowday);
        tomorrowdate=(TextView)findViewById(R.id.tomorrowdate);
        dayafterday=(TextView)findViewById(R.id.dayafterday);
        dayafterdate=(TextView)findViewById(R.id.dayafterdate);
        later=(TextView)findViewById(R.id.later);
        displaydate=(TextView)findViewById(R.id.displaydate);
        next=(LinearLayout)findViewById(R.id.next);
        special_instractions=(EditText)findViewById(R.id.special_instractions);
        totallineralayout=findViewById(R.id.totallineralayout);

        special_instractions.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });

        // linear selected item
        lineartoday=(LinearLayout)findViewById(R.id.lineartoday);
        lineartomorrow=(LinearLayout)findViewById(R.id.lineartomorrow);
        lineardayaftertomorrow=(LinearLayout)findViewById(R.id.lineardayaftertomorrow);
        linearfinalday=(LinearLayout)findViewById(R.id.linearfinalday);

        // get sharedpreference data
        sharedPreferences = getSharedPreferences("cookdetails", Context.MODE_PRIVATE);
        hours=sharedPreferences.getString("hours",null);
        cooks = sharedPreferences.getString("cooks", null);
        System.out.println("Noa"+hours);
        hoursint=Integer.parseInt(hours);


        // database for time slat
        databaseHelper = new DatabaseHelper(this);
        databaseHelper.deleteConformOrderData();

        // get complete today format here
        Date now = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(now);
        cal.add(Calendar.DAY_OF_YEAR, 0); // <--
        Date today1 = cal.getTime();
        String completetoday=""+today1;
        System.out.print("completer"+completetoday);

        Currentdate = dateFormatter.format(today1);

        System.out.println("completeedate"+completetoday);
        today.setText(""+completetoday.substring(0,4));
        todate.setText(""+completetoday.substring(8,10));
        time=""+completetoday.substring(11,13);
        miniites=""+completetoday.substring(14,16);
        System.out.println("minitesnow"+time);

        System.out.println("timenow"+time);
        year=completetoday.substring(30,34);
        System.out.print("dfhgjlk"+year);

        Date now1 = new Date();
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(now1);
        cal1.add(Calendar.DAY_OF_YEAR, 1); // <--
        Date completetomorrow1 = cal1.getTime();
        currendate1=dateFormatter.format(completetomorrow1);
        String completetomorrow=""+completetomorrow1;
        tomorrowday.setText(""+completetomorrow.substring(0,4));
        tomorrowdate.setText(""+completetomorrow.substring(8,10));
        year1=completetoday.substring(30,34);


        Date now2 = new Date();
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(now2);
        cal2.add(Calendar.DAY_OF_YEAR, 2); // <--
        Date tomorrow2 = cal2.getTime();
        String  completedayaftertomorrow=""+tomorrow2;
        currentdate2=dateFormatter.format(tomorrow2);
        dayafterday.setText(""+completedayaftertomorrow.substring(0,4));
        dayafterdate.setText(""+completedayaftertomorrow.substring(8,10));
        year2=completetoday.substring(30,34);

        final RecyclerView recyclerView=(RecyclerView)findViewById(R.id.recyclerViewtime);
        gridLayoutManager=new GridLayoutManager(getApplicationContext(),2);
        recyclerView.setLayoutManager(gridLayoutManager);

        timeSlotModels=new ArrayList<>();

        timeSlotModels.add(new TimeSlotModel("6 AM to 7 AM"));
        timeSlotModels.add(new TimeSlotModel("7 AM to 8 AM"));
        timeSlotModels.add(new TimeSlotModel("8 AM to 9 AM"));
        timeSlotModels.add(new TimeSlotModel("9 AM to 10 AM"));
        timeSlotModels.add(new TimeSlotModel("10 AM to 11 AM"));
        timeSlotModels.add(new TimeSlotModel("11 AM to 12 PM"));
        timeSlotModels.add(new TimeSlotModel("12 PM to 1 PM"));
        timeSlotModels.add(new TimeSlotModel("1 PM to 2 PM"));
        timeSlotModels.add(new TimeSlotModel("2 PM to 3 PM"));
        timeSlotModels.add(new TimeSlotModel("3 PM to 4 PM"));
        timeSlotModels.add(new TimeSlotModel("4 PM to 5 PM"));
        timeSlotModels.add(new TimeSlotModel("5 PM to 6 PM"));
        timeSlotModels.add(new TimeSlotModel("6 PM to 7 PM"));
        timeSlotAdapter=new TimeSlotAdapter(getApplicationContext(),timeSlotModels,hours,databaseHelper);

        recyclerView.setAdapter(timeSlotAdapter);

        getStartDate();


        // selecte one date
        lineartoday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseHelper.deleteConformOrderData();
                count++;
                displaydate.setVisibility(View.INVISIBLE);
                lineartoday.setBackgroundResource(R.drawable.selectitemshape);
                lineartomorrow.setBackgroundResource(R.drawable.timeshape);
                lineardayaftertomorrow.setBackgroundResource(R.drawable.timeshape);
                linearfinalday.setBackgroundResource(R.drawable.timeshape);
                sharedPreferences =getSharedPreferences("datedetails",Context.MODE_PRIVATE);
                editor=sharedPreferences.edit();
                editor.putString("date",Currentdate);
                editor.apply();
                editor.commit();


                ArrayList<TimeSlotModel> timeSlotModels1 = new ArrayList<>();
                timeSlotModels1.removeAll(timeSlotModels);
                int currenttime=Integer.parseInt(time);

                // this one for if time is more then 10 minites then didplay one more hour high
                if(Integer.parseInt(miniites)>=10){
                    currenttime=currenttime+1;
                }else {   // other wise it will display one hour more only
                    currenttime=currenttime;
                }
                for(int i=currenttime+1;i<=18;i++){
                    int j=i+1;
                    if(i==12) {
                        timeSlotModels1.add(new TimeSlotModel("12 PM to 1 PM"));
                    }else  if(i==13){
                        timeSlotModels1.add(new TimeSlotModel("1 PM to 2 PM"));
                    }else if(i==14){
                        timeSlotModels1.add(new TimeSlotModel("2 PM to 3 PM"));
                    }else if(i==15){
                        timeSlotModels1.add(new TimeSlotModel("3 PM to 4 PM"));
                    }else if(i==16){
                        timeSlotModels1.add(new TimeSlotModel("4 PM to 5 PM"));
                    }else if(i==17){
                        timeSlotModels1.add(new TimeSlotModel("5 PM to 6 PM"));
                    }else if(i==18){
                        timeSlotModels1.add(new TimeSlotModel("6 PM to 7 PM"));
                    }

                    else {
                        timeSlotModels1.add(new TimeSlotModel(i+" AM to "+j+" AM"));
                    }
                }

                timeSlotAdapter=new TimeSlotAdapter(getApplicationContext(),timeSlotModels1,hours,databaseHelper);
                recyclerView.setAdapter(timeSlotAdapter);

                recyclerView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                    }
                });

            }
        });
        lineartomorrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseHelper.deleteConformOrderData();
                count++;
                displaydate.setVisibility(View.INVISIBLE);
                lineartoday.setBackgroundResource(R.drawable.timeshape);

                    lineartomorrow.setBackgroundResource(R.drawable.selectitemshape);

                //change for oppo
             //   lineartomorrow.setBackgroundResource(R.drawable.timeshape);



                lineardayaftertomorrow.setBackgroundResource(R.drawable.timeshape);
                linearfinalday.setBackgroundResource(R.drawable.timeshape);

                sharedPreferences =getSharedPreferences("datedetails",Context.MODE_PRIVATE);
                editor=sharedPreferences.edit();
                editor.putString("date",currendate1);

                editor.apply();
                editor.commit();

                timeSlotModels=new ArrayList<>();

                timeSlotModels.add(new TimeSlotModel("6 AM to 7 AM"));
                timeSlotModels.add(new TimeSlotModel("7 AM to 8 AM"));
                timeSlotModels.add(new TimeSlotModel("8 AM to 9 AM"));
                timeSlotModels.add(new TimeSlotModel("9 AM to 10 AM"));
                timeSlotModels.add(new TimeSlotModel("10 AM to 11 AM"));
                timeSlotModels.add(new TimeSlotModel("11 AM to 12 PM"));
                timeSlotModels.add(new TimeSlotModel("12 PM to 1 PM"));
                timeSlotModels.add(new TimeSlotModel("1 PM to 2 PM"));
                timeSlotModels.add(new TimeSlotModel("2 PM to 3 PM"));
                timeSlotModels.add(new TimeSlotModel("3 PM to 4 PM"));
                timeSlotModels.add(new TimeSlotModel("4 PM to 5 PM"));
                timeSlotModels.add(new TimeSlotModel("5 PM to 6 PM"));
                timeSlotModels.add(new TimeSlotModel("6 PM to 7 PM"));

                timeSlotAdapter=new TimeSlotAdapter(getApplicationContext(),timeSlotModels,hours,databaseHelper);
                recyclerView.setAdapter(timeSlotAdapter);

            }
        });
        lineardayaftertomorrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseHelper.deleteConformOrderData();
                count++;
                displaydate.setVisibility(View.INVISIBLE);
                lineartoday.setBackgroundResource(R.drawable.timeshape);
                lineartomorrow.setBackgroundResource(R.drawable.timeshape);
                lineardayaftertomorrow.setBackgroundResource(R.drawable.selectitemshape);
                linearfinalday.setBackgroundResource(R.drawable.timeshape);
                sharedPreferences =getSharedPreferences("datedetails",Context.MODE_PRIVATE);
                editor=sharedPreferences.edit();
                editor.putString("date",currentdate2);
                editor.apply();
                editor.commit();


                timeSlotModels=new ArrayList<>();

                timeSlotModels.add(new TimeSlotModel("6 AM to 7 AM"));
                timeSlotModels.add(new TimeSlotModel("7 AM to 8 AM"));
                timeSlotModels.add(new TimeSlotModel("8 AM to 9 AM"));
                timeSlotModels.add(new TimeSlotModel("9 AM to 10 AM"));
                timeSlotModels.add(new TimeSlotModel("10 AM to 11 AM"));
                timeSlotModels.add(new TimeSlotModel("11 AM to 12 PM"));
                timeSlotModels.add(new TimeSlotModel("12 PM to 1 PM"));
                timeSlotModels.add(new TimeSlotModel("1 PM to 2 PM"));
                timeSlotModels.add(new TimeSlotModel("2 PM to 3 PM"));
                timeSlotModels.add(new TimeSlotModel("3 PM to 4 PM"));
                timeSlotModels.add(new TimeSlotModel("4 PM to 5 PM"));
                timeSlotModels.add(new TimeSlotModel("5 PM to 6 PM"));
                timeSlotModels.add(new TimeSlotModel("6 PM to 7 PM"));

                timeSlotAdapter=new TimeSlotAdapter(getApplicationContext(),timeSlotModels,hours,databaseHelper);
                recyclerView.setAdapter(timeSlotAdapter);
            }
        });
        linearfinalday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseHelper.deleteConformOrderData();
                count++;
                fromDatePickerDialog.show();
                fromDatePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
                displaydate.setVisibility(View.VISIBLE);

                linearfinalday.setBackgroundResource(R.drawable.selectitemshape);
                lineartoday.setBackgroundResource(R.drawable.timeshape);
                lineartomorrow.setBackgroundResource(R.drawable.timeshape);
                lineardayaftertomorrow.setBackgroundResource(R.drawable.timeshape);


                timeSlotModels=new ArrayList<>();

                timeSlotModels.add(new TimeSlotModel("6 AM to 7 AM"));
                timeSlotModels.add(new TimeSlotModel("7 AM to 8 AM"));
                timeSlotModels.add(new TimeSlotModel("8 AM to 9 AM"));
                timeSlotModels.add(new TimeSlotModel("9 AM to 10 AM"));
                timeSlotModels.add(new TimeSlotModel("10 AM to 11 AM"));
                timeSlotModels.add(new TimeSlotModel("11 AM to 12 PM"));
                timeSlotModels.add(new TimeSlotModel("12 PM to 1 PM"));
                timeSlotModels.add(new TimeSlotModel("1 PM to 2 PM"));
                timeSlotModels.add(new TimeSlotModel("2 PM to 3 PM"));
                timeSlotModels.add(new TimeSlotModel("3 PM to 4 PM"));
                timeSlotModels.add(new TimeSlotModel("4 PM to 5 PM"));
                timeSlotModels.add(new TimeSlotModel("5 PM to 6 PM"));
                timeSlotModels.add(new TimeSlotModel("6 PM to 7 PM"));
                timeSlotAdapter=new TimeSlotAdapter(getApplicationContext(),timeSlotModels,hours,databaseHelper);
                recyclerView.setAdapter(timeSlotAdapter);
            }
        });

        // count>=2&&
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if( count>=2) {

                    if (counttime >=2) {
                        System.out.print("counthoursss"+counthours);
                        System.out.print("hoursint"+hoursint);

                        if (hoursint+1<=counthours) {

                            String special_instraction=""+special_instractions.getText().toString();
                            sharedPreferences2=getSharedPreferences("SpecialInstactions",MODE_PRIVATE);
                            editor2=sharedPreferences2.edit();
                            editor2.putString("special_instraction",special_instraction);
                            editor2.apply();
                            editor2.commit();
                            Intent intent = new Intent(TimeSlot.this, JobDetails.class);
                            startActivity(intent);

                        }
                        else
                        {
                            Toast.makeText(TimeSlot.this, "You have selcect currect hours", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else
                    {
                        Toast.makeText(TimeSlot.this, "Please Select  time slot", Toast.LENGTH_SHORT).show();

                    }
                }
                else
                {
                    System.out.println("Sdfghjggfds"+counttime);
                    Toast.makeText(TimeSlot.this, "Please Select date ", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        count=0;

    }

    private void getStartDate() {
        Calendar newCalendar = Calendar.getInstance();
        fromDatePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth+0);
                displaydate.setText(dateFormatter.format(newDate.getTime()));
                sharedPreferences =getSharedPreferences("datedetails",Context.MODE_PRIVATE);
                editor=sharedPreferences.edit();
                editor.putString("date",displaydate.getText().toString());
                editor.apply();
                editor.commit();
            }
        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
    }
    public void hideKeyboard(View view) {
//        InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
//        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        InputMethodManager imm = (InputMethodManager) getSystemService(
                Activity.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
    }
}
