package com.arkainfoteck.helpmate.Activitys;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Address;
import android.location.Geocoder;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.arkainfoteck.helpmate.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class Login extends AppCompatActivity {
    Button login;
    TextView register,forgot;
    EditText lemail,lpassword;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    GetCurrentLocation currentLoc;
    public    String latitude, longitude;
    double l1,l2;
    Geocoder geocoder;
    List<Address> addresses;
    public static String Current_Location;
    String id,d;
    String fname,phone,email,password;
    Double ll1,ll2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        currentLoc = new GetCurrentLocation(Login.this);


        lemail=findViewById(R.id.lemail);
        lpassword=findViewById(R.id.lpassword);
        login=(Button)findViewById(R.id.login);
        register=(TextView)findViewById(R.id.register);
        forgot=(TextView)findViewById(R.id.forgot) ;


        sharedPreferences=getSharedPreferences("logindetails",Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();


        sharedPreferences = getSharedPreferences("logindetails", Context.MODE_PRIVATE);

        String uname = sharedPreferences.getString("password",null);
        if (uname != null) {
            Intent intent = new Intent(Login.this, DashBoard.class);
            startActivity(intent);
        }
        latitude = currentLoc.latitude;
        longitude = currentLoc.longitude;

        if (TextUtils.isEmpty(latitude) || TextUtils.isEmpty(longitude)) {
            latitude = "0.00";
            longitude = "0.00";
        }


        ll1 = Double.parseDouble(latitude.toString());
        ll2 = Double.parseDouble(longitude.toString());





        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = lemail.getText().toString().trim();

                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                if (lpassword.getText().toString().isEmpty()) {
                    lpassword.requestFocus();
                    lpassword.setError("provide password");
                }
                else if (email.matches(emailPattern)||email.length()==10) {
                    lemail.clearFocus();
                    lpassword.clearFocus();
//


                    loginJsondata();
                }else {
                    lemail.requestFocus();
                    lemail.setError("Enter Proper details ");

                }
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Login.this,Registration.class);
                startActivity(intent);

            }
        });
        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Login.this,ForgotPassword.class);
                startActivity(intent);

            }
        });
    }

    public  void loginJsondata(){
        RequestQueue rq = Volley.newRequestQueue(Login.this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                "http://broomsticks.in/index.php/api/login",
                new Response.Listener<String>() {

                    public void onResponse(String response) {

                        try {
                            //     Toast.makeText(Login.this," "+response,Toast.LENGTH_LONG).show();
                            JSONObject jsonObject1=new JSONObject(response);
                            //   Toast.makeText(Login.this,""+jsonObject1,Toast.LENGTH_LONG).show();
                            System.out.println("eieeiei"+response);
                            String message=jsonObject1.getString("message");
                            if(message.equals("User Not Valid")){
                                Toast.makeText(Login.this,"User not valid ",Toast.LENGTH_LONG).show();
                            }else {
                                Toast.makeText(Login.this,"Success",Toast.LENGTH_LONG).show();

                                String data=jsonObject1.getString("data");
                                JSONArray jsonObject12=new JSONArray(data);
                                for(int i=0;i<jsonObject12.length();i++) {
                                    JSONObject jsonObject = jsonObject12.getJSONObject(i);
                                    id=jsonObject.getString("id");

                                    System.out.println("qwe"+id);
                                    editor.putString("registerid",id);
                                    //                         editor.putString("password",register_pass);
                                    editor.apply();
                                    editor.commit();

                                    fname=jsonObject.getString("fname");
                                    System.out.println("2345"+fname);
                                    editor.putString("sname",fname);
                                    editor.apply();
                                    editor.commit();

                                    phone=jsonObject.getString("phone");
                                    System.out.println("6789"+phone);
                                    editor.putString("snoumber",phone);
                                    editor.apply();
                                    editor.commit();


                                    email=jsonObject.getString("email");
                                    System.out.println("348"+email);
                                    editor.putString("gmail",email);
                                    editor.apply();
                                    editor.commit();





                                    String street=jsonObject.getString("street");
                                    String pincode=jsonObject.getString("pincode");
                                    String state=jsonObject.getString("state");
                                    String districts=jsonObject.getString("districts");
                                    String area=jsonObject.getString("area");
                                    String location=jsonObject.getString("location");
                                    password=jsonObject.getString("password");
                                    editor.putString("password",password);
                                    editor.apply();
                                    editor.commit();



                                    String register_otp=jsonObject.getString("register_otp");
                                    String status=jsonObject.getString("status");

                                    latitude = currentLoc.latitude;
                    longitude = currentLoc.longitude;

                    if (TextUtils.isEmpty(latitude) || TextUtils.isEmpty(longitude)) {
                        latitude = "0.00";
                        longitude = "0.00";
                    }


                    l1 = Double.parseDouble(latitude.toString());
                    l2 = Double.parseDouble(longitude.toString());

                    geocoder = new Geocoder(Login.this, Locale.getDefault());
                    try {
                        addresses = geocoder.getFromLocation(l1, l2, 1);

                        if (addresses != null) {
                            Address returnedAddress = addresses.get(0);
                            StringBuilder strReturnedAddress = new StringBuilder("");

                            for (int j = 0; j <= returnedAddress.getMaxAddressLineIndex(); j++) {
                                strReturnedAddress.append(returnedAddress.getAddressLine(j)).append("\n");
                            }

                            Current_Location = strReturnedAddress.toString();
                            Intent intent=new Intent(Login.this,DashBoard.class);
                            sharedPreferences = getSharedPreferences("AddressDetails", Context.MODE_PRIVATE);
                            editor = sharedPreferences.edit();
                            editor.putString("location", Current_Location);
                            editor.commit();
                            editor.apply();
                            startActivity(intent);

                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                              /*      Intent intent=new Intent(Login.this,DashBoard.class);
                                    startActivity(intent);
*/

                                }
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
                params.put("email",lemail.getText().toString());
                params.put("password",lpassword.getText().toString());


                return params;
            }
        };
        rq.add(stringRequest);
    }

    @Override
    public void onResume() {
        super.onResume();
        currentLoc.connectGoogleApi();
    }

    @Override
    public void onStop() {
        super.onStop();
        currentLoc.disConnectGoogleApi();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
        System.exit(0);
    }
}
