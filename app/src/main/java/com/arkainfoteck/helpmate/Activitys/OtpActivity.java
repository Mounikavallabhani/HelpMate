package com.arkainfoteck.helpmate.Activitys;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.arkainfoteck.helpmate.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class OtpActivity extends AppCompatActivity {
    Button submit;
    EditText et1,et2,et3,et4,et5,et6;
    String otp;
    SharedPreferences sharedPreferences;
    String number;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        submit=(Button)findViewById(R.id.submitotp);

        sharedPreferences=getSharedPreferences("registerdetails",Context.MODE_PRIVATE);
        number=sharedPreferences.getString("number",null);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                otp=et1.getText().toString()+et2.getText().toString()+et3.getText().toString()+et4.getText().toString()+et5.getText().toString()+et6.getText().toString();
                Toast.makeText(OtpActivity.this," "+otp,Toast.LENGTH_LONG).show();
                OtpJsondata();

            }
        });

        et1=findViewById(R.id.et1);
        et2=findViewById(R.id.et2);
        et3=findViewById(R.id.et3);
        et4=findViewById(R.id.et4);
        et5=findViewById(R.id.et5);
        et6=findViewById(R.id.et6);


        et1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (et1.getText().toString().length() == 1)     //size as per your requirement
                {
                    et2.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        et2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (et2.getText().toString().length() == 1)     //size as per your requirement
                {
                    et3.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        et3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (et3.getText().toString().length() == 1)     //size as per your requirement
                {
                    et4.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        et4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (et4.getText().toString().length() == 1)     //size as per your requirement
                {
                    et5.requestFocus();

                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        et5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (et5.getText().toString().length() == 1)     //size as per your requirement
                {
                    et6.requestFocus();

                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        et6.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (et6.getText().toString().length() == 1)     //size as per your requirement
                {


                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
    public void OtpJsondata(){
        RequestQueue rq = Volley.newRequestQueue(OtpActivity.this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                "http://broomsticks.in/index.php/api/verifyOTP",
                new Response.Listener<String>() {

                    public void onResponse(String response) {

                        try {
                           // Toast.makeText(OtpActivity.this," "+response,Toast.LENGTH_LONG).show();
                            JSONObject jsonObject1=new JSONObject(response);
                            System.out.println("getdatsa"+response);
                            String message=jsonObject1.getString("message");
                              //Toast.makeText(OtpActivity.this,""+message,Toast.LENGTH_LONG).show();
                              if(message.equals("OTP Is Valid")){
                                  Toast.makeText(OtpActivity.this,"Varified Successfully",Toast.LENGTH_LONG).show();

                                  Intent intent=new Intent(OtpActivity.this,Login.class);
                                    startActivity(intent);
                              }else {
                                  Toast.makeText(OtpActivity.this,"Enter Correct Otp",Toast.LENGTH_LONG).show();

                              }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    };
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError arg0) {
                // TODO Auto-generated method stub
                //   pd.hide();
            }
        })
        {
            @Override
            public Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                 params.put("phone",number.toString());
                params.put("otp",otp.toString());


                return params;
            }
        };
        rq.add(stringRequest);
    }
}
