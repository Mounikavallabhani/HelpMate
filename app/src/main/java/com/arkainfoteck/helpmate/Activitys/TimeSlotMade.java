package com.arkainfoteck.helpmate.Activitys;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.text.UnicodeSetSpanner;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.arkainfoteck.helpmate.Adapter.CustomAdapter;
import com.arkainfoteck.helpmate.Adapter.CustomAdapterMade;
import com.arkainfoteck.helpmate.Adapter.TimeSlotAdapter;
import com.arkainfoteck.helpmate.Adapter.TimeSlotAdapterMode;
import com.arkainfoteck.helpmate.Model.Person;
import com.arkainfoteck.helpmate.Model.PersonMade;
import com.arkainfoteck.helpmate.Model.TimeSlotModel;
import com.arkainfoteck.helpmate.Model.TimeSlotModelMade;
import com.arkainfoteck.helpmate.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import static com.arkainfoteck.helpmate.Adapter.TimeSlotAdapter.counthours;
import static com.arkainfoteck.helpmate.Adapter.TimeSlotAdapter.counttime;

public class TimeSlotMade  extends AppCompatActivity {
    LinearLayout next;
    TextView today, todate, tomorrowday, tomorrowdate, dayafterday, dayafterdate, later, displaydate;
    public DatePickerDialog fromDatePickerDialog;
    private SimpleDateFormat dateFormatter;
    TimeSlotAdapterMode timeSlotAdaptermode;
    ArrayList<TimeSlotModelMade> timeSlotModelsMode;
    GridLayoutManager gridLayoutManager, mLayoutManager;
    LinearLayout lineartoday, lineartomorrow, lineardayaftertomorrow, linearfinalday;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String hours, cooks, regilar_parttime;
    String year, year1, year2;
    int hoursint;
    View view;

    String time, miniites;

    SharedPreferences sharedPreferences1, sharedPreferences2;
    SharedPreferences.Editor editor1, editor2;
    String Currentdate, currendate1, currentdate2;
    int count = 1;
    DatabaseHelper databaseHelper;
    EditText special_instractions;
    LinearLayout totallineralayout, time_details, dates_details;
    TextView total_days;
    LinearLayout layoutone, layouttwo;
    RecyclerView recyclerView, mRecyclerView;
    public CustomAdapterMade mAdaptermade;
    private ArrayList<PersonMade> mPersonListmade;
    PersonMade personmode;
    String complete_time_formet;
    StringRequest stringRequest;
    String Regular_id, Regular_time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_slot_made);
        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        time_details = (LinearLayout) findViewById(R.id.Regular_time_details);
        dates_details = (LinearLayout) findViewById(R.id.dates_details);


        total_days = (TextView) findViewById(R.id.total_days);
        today = (TextView) findViewById(R.id.today);
        todate = (TextView) findViewById(R.id.todate);
        tomorrowday = (TextView) findViewById(R.id.tomorrowday);
        tomorrowdate = (TextView) findViewById(R.id.tomorrowdate);
        dayafterday = (TextView) findViewById(R.id.dayafterday);
        dayafterdate = (TextView) findViewById(R.id.dayafterdate);
        later = (TextView) findViewById(R.id.later);
        displaydate = (TextView) findViewById(R.id.displaydate);
        next = (LinearLayout) findViewById(R.id.next);
        special_instractions = (EditText) findViewById(R.id.special_instractions);
        totallineralayout = findViewById(R.id.totallineralayout);
        special_instractions.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });


        // get complete today format here
        Date now = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(now);
        cal.add(Calendar.DAY_OF_YEAR, 0); // <--
        Date today1 = cal.getTime();
        String completetoday = "" + today1;
        System.out.print("completer" + completetoday);

        Currentdate = dateFormatter.format(today1);

        System.out.println("completeedate" + completetoday);
        String today_day = completetoday.substring(0, 10);


        // get current time


        // System.out.print("dea_time_find"+today_day);

        today.setText("" + completetoday.substring(0, 4));
        todate.setText("" + completetoday.substring(8, 10));
        time = "" + completetoday.substring(11, 13);
        miniites = "" + completetoday.substring(14, 16);
        System.out.println("minitesnow" + time);

        System.out.println("timenow" + time);
        year = completetoday.substring(30, 34);
        System.out.print("dfhgjlk" + year);

        Date now1 = new Date();
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(now1);
        cal1.add(Calendar.DAY_OF_YEAR, 1); // <--
        Date completetomorrow1 = cal1.getTime();
        currendate1 = dateFormatter.format(completetomorrow1);
        String completetomorrow = "" + completetomorrow1;
        tomorrowday.setText("" + completetomorrow.substring(0, 4));
        tomorrowdate.setText("" + completetomorrow.substring(8, 10));
        year1 = completetoday.substring(30, 34);


        Date now2 = new Date();
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(now2);
        cal2.add(Calendar.DAY_OF_YEAR, 2); // <--
        Date tomorrow2 = cal2.getTime();
        String completedayaftertomorrow = "" + tomorrow2;
        currentdate2 = dateFormatter.format(tomorrow2);
        dayafterday.setText("" + completedayaftertomorrow.substring(0, 4));
        dayafterdate.setText("" + completedayaftertomorrow.substring(8, 10));
        year2 = completetoday.substring(30, 34);


        // database for time slat
        databaseHelper = new DatabaseHelper(this);
        databaseHelper.deleteConformOrderData();

        // linear selected item
        lineartoday = (LinearLayout) findViewById(R.id.lineartoday);
        lineartomorrow = (LinearLayout) findViewById(R.id.lineartomorrow);
        lineardayaftertomorrow = (LinearLayout) findViewById(R.id.lineardayaftertomorrow);
        linearfinalday = (LinearLayout) findViewById(R.id.linearfinalday);

        // get sharedpreference data
        sharedPreferences = getSharedPreferences("cookdetails", Context.MODE_PRIVATE);
        hours = sharedPreferences.getString("hours", null);
        cooks = sharedPreferences.getString("cooks", null);
        regilar_parttime = sharedPreferences.getString("regilar_parttime", null);

        System.out.print("");
        System.out.println("regular_parttime" + regilar_parttime);

        // get current time now
        complete_time_formet = completetoday.substring(11, 19);
        System.out.println("comeeeeeeee" + complete_time_formet);

        // if regular means regular data will display part time means part time data will display
        if (regilar_parttime.equals("Regular")) {

            // total days
            total_days.setText("This  plan applicable " + completetoday.substring(0, 10) + " to 30 days");

            // database for time slat
            databaseHelper = new DatabaseHelper(this);
            databaseHelper.deleteConformOrderData();

            //        layouttwo.setVisibility(View.GONE);
            Toast.makeText(TimeSlotMade.this, "dates_details", Toast.LENGTH_LONG).show();
            dates_details.setVisibility(View.GONE);
            //  time_detai1ls.setVisibility(View.VISIBLE);


            System.out.print("Currentdare" + Currentdate);
            sharedPreferences = getSharedPreferences("datedetails", Context.MODE_PRIVATE);
            editor = sharedPreferences.edit();
            editor.putString("date", Currentdate);
            editor.apply();
            editor.commit();


            recyclerView = (RecyclerView) findViewById(R.id.recyclerViewtime);
            gridLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
            recyclerView.setLayoutManager(gridLayoutManager);
            recyclerView.setLayoutManager(gridLayoutManager);
            complete_time_formet = completetoday.substring(0, 10);

            System.out.println("get_complete_time" + complete_time_formet);


            getTimeSlatFromServer("00:00:00", Currentdate);


        } else {


            //      layoutone.setVisibility(View.GONE);
            mRecyclerView = (RecyclerView) findViewById(R.id.recyclerViewsecond);
            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            mRecyclerView.setHasFixedSize(true);
            // use a linear layout manager
            mLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
            mRecyclerView.setLayoutManager(mLayoutManager);
            mRecyclerView.addOnItemTouchListener(
                    new RecyclerItemClickListener(getApplicationContext(), new RecyclerItemClickListener.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, int position) {
                            try {
                                mAdaptermade.setSelected(position);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    })
            );
            complete_time_formet = completetoday.substring(11, 19);
            System.out.println("comeeeeeeee" + complete_time_formet);
            setPart_time("00:00:00", "10-11-2019");
            Toast.makeText(TimeSlotMade.this, "time+details", Toast.LENGTH_LONG).show();
            total_days.setText("get complete data");
            time_details.setVisibility(View.GONE);
            dates_details.setVisibility(View.VISIBLE);

        }

        System.out.println("Noa" + hours);
        hoursint = Integer.parseInt(hours);

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
                sharedPreferences = getSharedPreferences("datedetails", Context.MODE_PRIVATE);
                editor = sharedPreferences.edit();
                System.out.println("get_current_date" + Currentdate);
                editor.putString("date", Currentdate);
                editor.apply();
                editor.commit();


                mRecyclerView = (RecyclerView) findViewById(R.id.recyclerViewsecond);
                // use this setting to improve performance if you know that changes
                // in content do not change the layout size of the RecyclerView
                mRecyclerView.setHasFixedSize(true);
                // use a linear layout manager
                mLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
                mRecyclerView.setLayoutManager(mLayoutManager);
                mRecyclerView.addOnItemTouchListener(
                        new RecyclerItemClickListener(getApplicationContext(), new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                try {
                                    mAdaptermade.setSelected(position);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        })
                );
                //     setupPersonList();

                System.out.print("complete_time_formet" + complete_time_formet);
                setPart_time(complete_time_formet, Currentdate);

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

                sharedPreferences = getSharedPreferences("datedetails", Context.MODE_PRIVATE);
                editor = sharedPreferences.edit();
                editor.putString("date", currendate1);

                editor.apply();
                editor.commit();

                mRecyclerView = (RecyclerView) findViewById(R.id.recyclerViewsecond);
                // use this setting to improve performance if you know that changes
                // in content do not change the layout size of the RecyclerView
                mRecyclerView.setHasFixedSize(true);
                // use a linear layout manager
                mLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
                mRecyclerView.setLayoutManager(mLayoutManager);
                mRecyclerView.addOnItemTouchListener(
                        new RecyclerItemClickListener(getApplicationContext(), new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                try {
                                    mAdaptermade.setSelected(position);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        })
                );
                setPart_time("00:00:00", currendate1);


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
                sharedPreferences = getSharedPreferences("datedetails", Context.MODE_PRIVATE);
                editor = sharedPreferences.edit();
                editor.putString("date", currentdate2);
                editor.apply();
                editor.commit();


                mRecyclerView = (RecyclerView) findViewById(R.id.recyclerViewsecond);
                // use this setting to improve performance if you know that changes
                // in content do not change the layout size of the RecyclerView
                mRecyclerView.setHasFixedSize(true);
                // use a linear layout manager
                mLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
                mRecyclerView.setLayoutManager(mLayoutManager);
                mRecyclerView.addOnItemTouchListener(
                        new RecyclerItemClickListener(getApplicationContext(), new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                try {
                                    mAdaptermade.setSelected(position);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        })
                );
                setPart_time("00:00:00", currentdate2);

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
                String dates = displaydate.getText().toString();
                System.out.print("dattes_findimg" + dates);
                linearfinalday.setBackgroundResource(R.drawable.selectitemshape);
                lineartoday.setBackgroundResource(R.drawable.timeshape);
                lineartomorrow.setBackgroundResource(R.drawable.timeshape);
                lineardayaftertomorrow.setBackgroundResource(R.drawable.timeshape);


                mRecyclerView = (RecyclerView) findViewById(R.id.recyclerViewsecond);
                // use this setting to improve performance if you know that changes
                // in content do not change the layout size of the RecyclerView
                mRecyclerView.setHasFixedSize(true);
                // use a linear layout manager
                mLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
                mRecyclerView.setLayoutManager(mLayoutManager);
                mRecyclerView.addOnItemTouchListener(
                        new RecyclerItemClickListener(getApplicationContext(), new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                try {
                                    mAdaptermade.setSelected(position);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        })
                );

                setPart_time("00:00:00", dates);

            }
        });

        // count>=2&&
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (regilar_parttime.equals("Regular")) {


                    String special_instraction = "" + special_instractions.getText().toString();
                    sharedPreferences2 = getSharedPreferences("SpecialInstactions", MODE_PRIVATE);
                    editor2 = sharedPreferences2.edit();
                    editor2.putString("special_instraction", special_instraction);
                    editor2.apply();
                    editor2.commit();
                    Intent intent = new Intent(TimeSlotMade.this, JobDetails.class);
                    startActivity(intent);


                } else {

                                String special_instraction = "" + special_instractions.getText().toString();
                                sharedPreferences2 = getSharedPreferences("SpecialInstactions", MODE_PRIVATE);
                                editor2 = sharedPreferences2.edit();
                                editor2.putString("special_instraction", special_instraction);
                                editor2.apply();
                                editor2.commit();
                                Intent intent = new Intent(TimeSlotMade.this, JobDetails.class);
                                startActivity(intent);


                }
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        count = 0;

    }

    private void getStartDate() {
        Calendar newCalendar = Calendar.getInstance();
        fromDatePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth + 0);
                displaydate.setText(dateFormatter.format(newDate.getTime()));
                sharedPreferences = getSharedPreferences("datedetails", Context.MODE_PRIVATE);
                editor = sharedPreferences.edit();
                editor.putString("date", displaydate.getText().toString());
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

    private void setPart_time(final String complete_time_formet, final String date) {


        RequestQueue rq = Volley.newRequestQueue(TimeSlotMade.this);
        stringRequest = new StringRequest(Request.Method.POST,
                "http://broomsticks.in/index.php/api/maid_time_slates",


                new Response.Listener<String>() {

                    public void onResponse(String response) {
                        System.out.println("response" + response);

                        mPersonListmade = new ArrayList<PersonMade>();
                        mPersonListmade.clear();

                        try {
                            JSONArray jsonArray1 = new JSONArray(response);
                            for (int i = 0; i < jsonArray1.length(); i++) {

                                JSONObject jsonObject = jsonArray1.getJSONObject(i);
                                Regular_id = jsonObject.getString("id");
                                Regular_time = jsonObject.getString("time_slate");

                                personmode = new PersonMade(Regular_time);
                                mPersonListmade.add(personmode);
                                mAdaptermade = new CustomAdapterMade(mPersonListmade, TimeSlotMade.this);

                            }
                            mRecyclerView.setAdapter(mAdaptermade);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }

                    ;
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError arg0) {
                // TODO Auto-generated method stub
                //   pd.hide();
            }
        }) {
            @Override
            public Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("time", complete_time_formet);
                params.put("date", date);

                return params;
            }
        };
        rq.add(stringRequest);
    }


    public void getTimeSlatFromServer(final String complete, final String date) {

        System.out.println("get_complete_time_date" + complete);


        RequestQueue rq = Volley.newRequestQueue(TimeSlotMade.this);
        stringRequest = new StringRequest(Request.Method.POST,
                "http://broomsticks.in/index.php/api/maid_time_slates",


                new Response.Listener<String>() {

                    public void onResponse(String response) {
                        System.out.println("response" + response);

                        timeSlotModelsMode = new ArrayList<>();

                        try {
                            JSONArray jsonArray1 = new JSONArray(response);
                            for (int i = 0; i < jsonArray1.length(); i++) {

                                JSONObject jsonObject = jsonArray1.getJSONObject(i);
                                Regular_id = jsonObject.getString("id");
                                Regular_time = jsonObject.getString("time_slate");

                                System.out.print("get_time" + Regular_time);
                                timeSlotModelsMode.add(new TimeSlotModelMade(Regular_time));

                            }

                            timeSlotAdaptermode = new TimeSlotAdapterMode(getApplicationContext(), timeSlotModelsMode, hours, databaseHelper);

                            recyclerView.setAdapter(timeSlotAdaptermode);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }

                    ;
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError arg0) {
                // TODO Auto-generated method stub
                //   pd.hide();
            }
        }) {
            @Override
            public Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("time", complete);
                params.put("date", date);

                return params;
            }
        };
        rq.add(stringRequest);
    }
}