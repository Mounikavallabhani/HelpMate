package com.arkainfoteck.helpmate.Activitys;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

import java.util.HashMap;
import java.util.Map;

public class EditProfile extends AppCompatActivity {
    Button update;
    EditText eusername;
    TextView eemail,eph;
    SharedPreferences sharedPreferences;
    String susername,sph,susermail;
    String id,fname,phone,email,registerid;
    SharedPreferences sharedPreferences1;
    SharedPreferences.Editor editor;
    String door_no,street,districts,area,location,pincode,status;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        update=findViewById(R.id.update);
        eusername=findViewById(R.id.eusername);

        eemail=findViewById(R.id.eemail);
        eph=findViewById(R.id.eph);

        sharedPreferences=getSharedPreferences("logindetails",Context.MODE_PRIVATE);
        susername=sharedPreferences.getString("sname",null);
        sph=sharedPreferences.getString("snoumber",null);
        susermail=sharedPreferences.getString("gmail",null);
        registerid=sharedPreferences.getString("registerid",null);


        eusername.setText(susername);
        eemail.setText(susermail);
        eph.setText(sph);

        sharedPreferences1=getSharedPreferences("profiledetails",Context.MODE_PRIVATE);
        String sdoorno=sharedPreferences1.getString("doorno",null);
        String sstreet=sharedPreferences1.getString("street",null);
        String sdistricts=sharedPreferences1.getString("districts",null);
        String sarea=sharedPreferences1.getString("area",null);
        String slocation=sharedPreferences1.getString("location",null);
        String spincode=sharedPreferences1.getString("pincode",null);
        String sstatus=sharedPreferences1.getString("status",null);



            eusername.setText(susername);
            eemail.setText(susermail);
            eph.setText(sph);



        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(eusername.getText().toString().isEmpty())
                {
                    eusername.requestFocus();
                    eusername.setError("Provide name");
                }
                else if(eemail.getText().toString().isEmpty())
                {
                    eemail.requestFocus();
                    eemail.setError("Provide name");
                }
                else if(eph.getText().toString().isEmpty())
                {
                    eph.requestFocus();
                    eph.setError("Provide name");
                }
                else {
                    EditProfileJsondata();


                }
            }
        });

    }

    public  void EditProfileJsondata(){
        RequestQueue rq = Volley.newRequestQueue(EditProfile.this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                "http://broomsticks.in/index.php/api/update_profile",
                /* "http://carshuru.com/tst/grephor/register.php",*/
                new Response.Listener<String>() {

                    public void onResponse(String response) {

                        try {

                            JSONObject jsonObject1=new JSONObject(response);
                            String message=jsonObject1.getString("message");
                            if(message.equals("User Successfully Updated")){
                                Toast.makeText(EditProfile.this,"Success",Toast.LENGTH_LONG).show();

                                Intent intent=new Intent(EditProfile.this,DashBoard.class);


                                startActivity(intent);

                            }else {
                                Toast.makeText(EditProfile.this,"User not valid ",Toast.LENGTH_LONG).show();

                            }

                            String data=jsonObject1.getString("data");
                            JSONArray jsonObject12=new JSONArray(data);
                            for(int i=0;i<jsonObject12.length();i++) {
                                JSONObject jsonObject = jsonObject12.getJSONObject(i);
                                id=jsonObject.getString("id");

                                System.out.println("qwe"+id);




                                fname=jsonObject.getString("fname");
                                System.out.println("2345"+fname);



                                phone=jsonObject.getString("phone");
                                System.out.println("6789"+phone);



                                email=jsonObject.getString("email");
                                System.out.println("348"+email);


                                  door_no=jsonObject.getString("door_no");
                                System.out.println("2345"+door_no);

                               // eflatno.setText(door_no);


                                 street=jsonObject.getString("street");

                                System.out.println("3456"+street);

                                 pincode=jsonObject.getString("pincode");
                                System.out.println("4567"+pincode);

                                String state=jsonObject.getString("state");
                                System.out.println("34567"+state);

                                districts=jsonObject.getString("districts");
                                System.out.println("34567"+districts);

                                area=jsonObject.getString("area");
                                System.out.println("770"+area);

                                location=jsonObject.getString("location");
                                System.out.println("096"+location);

                                String password=jsonObject.getString("password");
                                String register_otp=jsonObject.getString("register_otp");
                                status=jsonObject.getString("status");
                                System.out.println("126"+location);

                                }
                                sharedPreferences1=getSharedPreferences("profiledetails",Context.MODE_PRIVATE);
                                editor=sharedPreferences1.edit();
                                editor.putString("doorno",door_no);
                                editor.putString("area",area);
                                editor.putString("districts",districts);
                                editor.putString("location",location);
                                editor.putString("status",status);
                                editor.putString("pincode",pincode);

                            editor.apply();
                                editor.commit();







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
                params.put("id",registerid);
                params.put("fname",eusername.getText().toString());
                params.put("door_no"," ");
                params.put("area"," ");
                params.put("disstricts"," ");
                params.put("state"," ");
                params.put("pincode"," ");
                return params;
            }
        };
        rq.add(stringRequest);
    }

}
