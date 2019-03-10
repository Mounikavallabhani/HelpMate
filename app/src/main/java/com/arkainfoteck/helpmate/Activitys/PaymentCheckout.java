package com.arkainfoteck.helpmate.Activitys;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.arkainfoteck.helpmate.R;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class PaymentCheckout extends AppCompatActivity implements PaymentResultListener {
    //CardView cashondelivery,razerpay;
    int counter =1;
    int count=1;
    LinearLayout payment;
    private static final String TAG = PaymentActivity.class.getSimpleName();
    LinearLayout next;
    View view;
    Fragment fragment,fragment1;
    FragmentManager fm;
    FragmentTransaction ft;
    LinearLayout cashondelivery,onlinepayment;
   // int counter=1;
    int countets=1;
    SharedPreferences sharedPreferences,sharedPreferences1,sharedPreferences2,sharedPreferences3,sharedPreferences4,sharedPreferences5;
    double finalamount,finalamountserver;
    String scname,snoumber,gmail,nameofimage,imagedata;
    String registerid;
    String scooks,shours,date,timeslats,special_instraction,completeaddress,completetoday;
    String razorpayPaymentID;
    String cooktypedetails;
    String regilar_parttime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_checkout);
        cashondelivery=findViewById(R.id.cashondelivery);
        onlinepayment=findViewById(R.id.onlinepayment);


        // here  we will get  product name and image
        sharedPreferences2=getSharedPreferences("Nameoftype",Context.MODE_PRIVATE);
        nameofimage=sharedPreferences2.getString("nameofimage",null);
        imagedata=sharedPreferences2.getString("imagedata",null);


        // final amount get here
        sharedPreferences =getSharedPreferences("totalamount", Context.MODE_PRIVATE);
        String amount=sharedPreferences.getString("totalamount",null);
        finalamount=Double.parseDouble(amount);
        finalamountserver=finalamount*100;

        // login details get here
        sharedPreferences1=getSharedPreferences("logindetails",Context.MODE_PRIVATE);
        registerid=sharedPreferences1.getString("registerid",null);
        scname=sharedPreferences1.getString("sname",null);
        snoumber=sharedPreferences1.getString("snoumber",null);
        gmail=sharedPreferences1.getString("gmail",null);


        // here number of hours and cooks data
        sharedPreferences3=getSharedPreferences("cookdetails",Context.MODE_PRIVATE);
        shours=sharedPreferences3.getString("hours",null);
        scooks=sharedPreferences3.getString("cooks",null);

        // date
        sharedPreferences4 =getSharedPreferences("datedetails",Context.MODE_PRIVATE);
        date=sharedPreferences4.getString("date",null);

        // timeslat
        sharedPreferences4 =getSharedPreferences("Timeshedule",Context.MODE_PRIVATE);
        timeslats=sharedPreferences4.getString("timeslats",null);

        // special instractions
        sharedPreferences4 =getSharedPreferences("SpecialInstactions",Context.MODE_PRIVATE);
        special_instraction=sharedPreferences4.getString("special_instraction",null);

        // adresss
        sharedPreferences5 =getSharedPreferences("AddressData",Context.MODE_PRIVATE);
        completeaddress=sharedPreferences5.getString("completeaddress",null);

        // booking type
        regilar_parttime=sharedPreferences.getString("regilar_parttime",null);

        // get current time
        Date now = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(now);
        cal.add(Calendar.DAY_OF_YEAR, 0); // <--
        Date today1 = cal.getTime();
        completetoday=""+today1;
        System.out.print("getcurrenttimeanddate"+completetoday);

        // get veg details
        sharedPreferences5 =getSharedPreferences("cooktype",Context.MODE_PRIVATE);
        cooktypedetails=sharedPreferences5.getString("cooktypedetails",null);

          System.out.println("registerid"+registerid);
          System.out.println("scname"+scname);
          System.out.print("cooktypedetails"+cooktypedetails);
          System.out.println("shours"+shours);

        onlinepayment.setOnClickListener(
                new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startPayment();
            }
        });
        cashondelivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                System.out.println("user_id"+registerid);
                System.out.println("user_name"+scname);
                System.out.println("item_type"+cooktypedetails);

                System.out.println("no_hour"+shours);
                System.out.println("no_cook"+scooks);
                System.out.println("dates"+date);
                System.out.println("timeing"+timeslats);
                System.out.println("special"+special_instraction);
                System.out.println("name"+scname);

                System.out.println("phone"+snoumber);
                System.out.println("email"+gmail);
                System.out.println("location"+"No location");

                System.out.println("address"+completeaddress);
                System.out.println("orderedtime"+completetoday);
                System.out.println("price"+String.valueOf(finalamount));

                System.out.println("offer_amount"+"0");
                System.out.println("orderedtime"+completetoday);
                System.out.println("price"+String.valueOf(finalamount));
                System.out.println("tax"+"0");
                System.out.println("net_price"+String.valueOf(finalamount));
                System.out.println("razorpay_id"+"no razorid");
                System.out.println("payment_method"+"Cash on delivery");
                System.out.println("bookingtype"+nameofimage);
                System.out.println("type"+nameofimage);
                System.out.println("image"+imagedata);
                System.out.println("booking_type"+regilar_parttime);



                RequestQueue rq = Volley.newRequestQueue(PaymentCheckout.this);
                StringRequest stringRequest = new StringRequest(Request.Method.POST,
                        "http://broomsticks.in/index.php/api/booking",

                        new Response.Listener<String>() {

                            public void onResponse(String response) {

                                Toast.makeText(getApplicationContext(),"Cash on deleviry ok",Toast.LENGTH_LONG).show();
                                Intent intent=new Intent(getApplicationContext(),Successpayment.class);
                                startActivity(intent);
                            }

                        }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError arg0) {
                        // TODO Auto-generated method stub
                        //   pd.hide();
                    }
                })

                {
                    @Override
                    public Map<String, String> getParams () throws AuthFailureError {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("user_id", registerid);
                        params.put("user_name", scname);
                        params.put("item_type",cooktypedetails);
                        params.put("no_hour", shours);
                        params.put("no_cook", scooks);
                        params.put("dates", date);
                        params.put("timeing", timeslats);
                        params.put("special", ""+special_instraction);
                        params.put("name", ""+scname);
                        params.put("phone", ""+snoumber);
                        params.put("email", ""+gmail);
                        params.put("location", "No_Locations");
                        params.put("address", completeaddress);
                        params.put("address_type", "Home");
                        params.put("orderedtime",completetoday );
                        params.put("price",String.valueOf(finalamount) );
                        params.put("offer_amount","0" );
                        params.put("tax", "0");
                        params.put("net_price",""+String.valueOf(finalamount));
                        params.put("razorpay_id", "no razorid");
                        params.put("razorpay_status","false" );
                        params.put("payment_method","Cash on delivery" );
                        params.put("bookingtype",""+nameofimage);
                        params.put("type",""+nameofimage);
                        params.put("image",imagedata);
                        params.put("booking_type",""+regilar_parttime);

                        return params;
                    }
                } ;
                rq.add(stringRequest);


            }
        });

    }
    public void startPayment() {
        /*
          You need to pass current activity in order to let Razorpay create CheckoutActivity
         */
        final Activity activity = PaymentCheckout.this;

        final Checkout co = new Checkout();

        try {
            JSONObject options = new JSONObject();
            options.put("name", nameofimage);
            //   options.put("description", "Demoing Charges");
            //You can omit the image option to fetch the image from dashboard
            options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.png");
            options.put("currency", "INR");
            options.put("amount", finalamountserver);

            JSONObject preFill = new JSONObject();
            preFill.put("name", gmail);
            preFill.put("contact", snoumber);

            options.put("prefill", preFill);

            co.open(activity, options);
        } catch (Exception e) {
            Toast.makeText(activity, "Error in payment: " + e.getMessage(), Toast.LENGTH_SHORT)
                    .show();
            e.printStackTrace();
        }
    }

    /**
     * The name of the function has to be
     * onPaymentSuccess
     * Wrap your code in try catch, as shown, to ensure that this method runs correctly
     */
    @SuppressWarnings("unused")
    @Override
    public void onPaymentSuccess(String razorpayPaymentID) {
        try {
            this.razorpayPaymentID=razorpayPaymentID;

            onlinepaymentmethod();


        } catch (Exception e) {
            Log.e(TAG, "Exception in onPaymentSuccess", e);
        }
    }

    /**
     * The name of the function has to be
     * onPaymentError
     * Wrap your code in try catch, as shown, to ensure that this method runs correctly
     */
    @SuppressWarnings("unused")
    @Override
    public void onPaymentError(int code, String response) {
        try {
            Toast.makeText(getApplicationContext(), "Payment failed: ", Toast.LENGTH_SHORT).show();
            Intent intent =new Intent(getApplicationContext(),JobDetails.class);
            getApplicationContext().startActivity(intent);
        } catch (Exception e) {
            Log.e(TAG, "Exception in onPaymentError", e);
        }
    }


    public void   onlinepaymentmethod(){

        RequestQueue rq = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                "http://broomsticks.in/index.php/api/booking",
                new Response.Listener<String>() {

                    public void onResponse(String response) {

                        Toast.makeText(getApplicationContext(), "Payment Successful: " + razorpayPaymentID, Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(getApplicationContext(),Successpayment.class);
                        startActivity(intent);


//                        fragment=new fragmentSuccess();
//                        fm= getFragmentManager();
//                        ft = fm.beginTransaction();
//                        ft.replace(R.id.fragment_container, fragment);
//                        ft.addToBackStack(null);
//                        ft.commit();
//                        payment.setBackgroundResource(R.drawable.circlewithcolor);
                    }

                    ;
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError arg0) {
                // TODO Auto-generated method stub
                //   pd.hide();
            }
        })

        {
            @Override
            public Map<String, String> getParams () throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("user_id", registerid);
                params.put("user_name", scname);
                params.put("item_type",cooktypedetails);
                params.put("no_hour", shours);
                params.put("no_cook", scooks);
                params.put("dates", date);
                params.put("timeing", timeslats);
                params.put("special", special_instraction);
                params.put("name", scname);
                params.put("phone", snoumber);
                params.put("email", gmail);
                params.put("location", "No_Locations");
                params.put("address", completeaddress);
                params.put("address_type", "Home");
                params.put("orderedtime",completetoday );
                params.put("price",String.valueOf(finalamount) );
                params.put("offer_amount","0" );
                params.put("tax", "0");
                params.put("net_price",String.valueOf(finalamount));
                params.put("razorpay_id", razorpayPaymentID);
                params.put("razorpay_status","paid" );
                params.put("payment_method","Online" );
                params.put("bookingtype",nameofimage);
                params.put("booking_type",regilar_parttime);

                return params;
            }
        } ;
        rq.add(stringRequest);

    }
}
