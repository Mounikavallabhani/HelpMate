package com.arkainfoteck.helpmate.Activitys;

import android.content.Intent;
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

public class ForgotPassword extends AppCompatActivity {
    Button submit;
    EditText phonenumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        submit=(Button)findViewById(R.id.submitforgot);
        phonenumber=(EditText)findViewById(R.id.phonenumber);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                loginJsondata();



            }
        });
    }


    public  void loginJsondata(){
        RequestQueue rq = Volley.newRequestQueue(ForgotPassword.this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                "http://arkainfoteck.xyz/helpmate/dynamic/index.php/api/forgetPassword",
                /* "http://carshuru.com/tst/grephor/register.php",*/
                new Response.Listener<String>() {

                    public void onResponse(String response) {

                        try {

                            JSONObject jsonObject1=new JSONObject(response);

                            String message=jsonObject1.getString("message");
                            if(message.equals("Valid Mobile required")){

                            }else {
                                Intent intent=new Intent(ForgotPassword.this,Login.class);
                                Toast.makeText(ForgotPassword.this, "Password Send To Your Registered Mobile Number ", Toast.LENGTH_SHORT).show();
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
            }
        })

        {
            @Override
            public Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("phone",phonenumber.getText().toString());



                return params;
            }
        };
        rq.add(stringRequest);
    }

}
