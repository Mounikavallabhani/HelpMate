package com.arkainfoteck.helpmate.Activitys;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ChangePassword extends AppCompatActivity {
    Button Submit;
    SharedPreferences sharedPreferences;
    String registerid;
    EditText oldpassword,newpassword,confirmpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        sharedPreferences=getSharedPreferences("logindetails",Context.MODE_PRIVATE);
        registerid=sharedPreferences.getString("registerid",null);
        System.out.print("dfghjgh"+registerid);

        Submit=findViewById(R.id.Submit);
        oldpassword=(EditText)findViewById(R.id.oldpassword);
        newpassword=(EditText)findViewById(R.id.newpassword);
        confirmpassword=(EditText)findViewById(R.id.confirmpassword);
        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(oldpassword.getText().toString().isEmpty())
                {
                    oldpassword.requestFocus();
                    oldpassword.setError("Provide old password");

                }
                else if(newpassword.getText().toString().isEmpty())
                {
                    newpassword.requestFocus();
                    newpassword.setError("Provide new Password");

                }
                else if(confirmpassword.getText().toString().isEmpty())
                {
                    confirmpassword.requestFocus();
                    newpassword.setError("provide Confirm password");
                }
                else if(!confirmpassword .getText().toString().equals(newpassword.getText().toString())) {
                    Toast.makeText(ChangePassword.this, "Password Not matching", Toast.LENGTH_SHORT).show();
                }
                else {
//                    Intent intent = new Intent(getApplicationContext(), DashBoard.class);
//                    startActivity(intent);
                    ChangePasswordJsondata();
                }
            }
        });


    }

    protected void ChangePasswordJsondata() {
        RequestQueue rq = Volley.newRequestQueue(ChangePassword.this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
               "http://broomsticks.in/index.php/api/change_password",
                /* "http://carshuru.com/tst/grephor/register.php",*/
                new Response.Listener<String>() {

                    public void onResponse(String response) {

                        try {


                            JSONObject jsonObject1=new JSONObject(response);
                            String message=jsonObject1.getString("message");
                            if(message.equals("Success")) {
                                Toast.makeText(ChangePassword.this, "sucessfully updated", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(ChangePassword.this, DashBoard.class);
                                startActivity(intent);
                            }
                            else
                            {
                                Toast.makeText(ChangePassword.this, "password not matching", Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    };
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError arg0) {
                // TODO Auto-generated method stub
            }
        })

        {
            @Override
            public Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("id",registerid);
                params.put("opassword",oldpassword.getText().toString());
                params.put("npassword",newpassword.getText().toString());
                 return  params;
            }
        };
        rq.add(stringRequest);
    }
}
