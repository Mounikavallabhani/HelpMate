package com.arkainfoteck.helpmate.Activitys;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import com.github.silvestrpredko.dotprogressbar.DotProgressBar;
import com.github.silvestrpredko.dotprogressbar.DotProgressBarBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Registration extends AppCompatActivity {
    Button register;
    EditText rname,rnumber,rmail,rpassword;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    DotProgressBar dot_progress_bar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        register=(Button)findViewById(R.id.register);
        rname=findViewById(R.id.rname);
        rnumber=findViewById(R.id.rnumber);
        rmail=findViewById(R.id.rmail);
        rpassword=findViewById(R.id.rpassword);

        dot_progress_bar=findViewById(R.id.dot_progress_bar);
        dot_progress_bar.setVisibility(View.GONE);
        new DotProgressBarBuilder(this)
                .setDotAmount(3)
                .setStartColor(Color.BLACK)
                .setAnimationDirection(DotProgressBar.LEFT_DIRECTION)
                .build();
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = rmail.getText().toString().trim();

                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                if(rname.getText().toString().isEmpty()) {
                    rname.requestFocus();
                    rname.setError("provide Name");
                }else if(!email.matches(emailPattern))
                {
                    rmail.requestFocus();
                    rmail.setError("provide proper mail id");
                }
                else if(rnumber.length()!=10)
                {
                    rnumber.requestFocus();
                    rnumber.setError("provide proper mobile number");
                }
                else
                if(rpassword.getText().toString().length()<=6)
                {
                    rpassword.requestFocus();
                    rpassword.setError("Use 6 characters or more for your password");
                }
                else {
                    RegistorJsondata();

                }
            }
        });
    }

    public void RegistorJsondata(){
        dot_progress_bar.setVisibility(View.VISIBLE);

        RequestQueue rq = Volley.newRequestQueue(Registration.this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                "http://broomsticks.in/index.php/api/registration",

                new Response.Listener<String>() {

                    public void onResponse(String response) {

                        try {
                             Toast.makeText(Registration.this," "+response,Toast.LENGTH_LONG).show();
                            JSONObject jsonObject1=new JSONObject(response);
                            String message=jsonObject1.getString("message");

                            if(message.equals("Email Already Exist")){
                                rmail.requestFocus();
                                rmail.setError("This Mail alredy Exist");
                            }else if(message.equals("Mobile number Already Exist")){
                                rnumber.requestFocus();
                                rnumber.setError("This Phone Number alredy Exist");

                            }else {
                                dot_progress_bar.setVisibility(View.INVISIBLE);
                                Toast.makeText(Registration.this,"Registerd Successfully",Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(Registration.this, OtpActivity.class);
                                sharedPreferences=getSharedPreferences("registerdetails",Context.MODE_PRIVATE);
                                editor=sharedPreferences.edit();
                                String srname=rname.getText().toString();
                                String srmail=rmail.getText().toString();
                                String srnumber=rnumber.getText().toString();
                                System.out.println("name"+srname);
                                System.out.println("mail"+srmail);
                                System.out.println("number"+srnumber);
                                editor.putString("name",srname);
                                editor.putString("mail",srmail);
                                editor.putString("number",srnumber);
                                editor.commit();
                                editor.apply();
                                startActivity(intent);
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
                System.out.println("getdata"+arg0);
            }
        })

        {
            @Override
            public Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();

                params.put("fname",rname.getText().toString());
                params.put("email",rmail.getText().toString());
                params.put("phone",rnumber.getText().toString());
                params.put("password",rpassword.getText().toString());

                return params;
            }
        };
        rq.add(stringRequest);
    }
}
