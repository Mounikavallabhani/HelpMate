package com.arkainfoteck.helpmate.Activitys;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.arkainfoteck.helpmate.R;



public class CheckOut extends AppCompatActivity {
    LinearLayout next;

    EditText cname,cmail,cnumber,caddress;
    SharedPreferences sharedPreferences,sharedPreferences1,sharedPreferences2;

    String scaddress;

    LinearLayout details;
    EditText house_number,colony_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out);
       // details=(LinearLayout)findViewById(R.id.next);
        next=(LinearLayout)findViewById(R.id.next);
        cname=findViewById(R.id.cname);
        cmail=findViewById(R.id.cmail);
        cnumber=findViewById(R.id.cnumber);
        caddress=findViewById(R.id.caddress);
        house_number=(EditText)findViewById(R.id.house_number);
        colony_name=(EditText)findViewById(R.id.colony_name);





        sharedPreferences=getSharedPreferences("AddressDetails",Context.MODE_PRIVATE);
        scaddress=sharedPreferences.getString("location",null);

        sharedPreferences1=getSharedPreferences("logindetails",Context.MODE_PRIVATE);


        String scname=sharedPreferences1.getString("sname",null);
        String snoumber=sharedPreferences1.getString("snoumber",null);
        String gmail=sharedPreferences1.getString("gmail",null);
        System.out.println("getdata"+gmail);
        cname.setText(scname);
        cmail.setText(gmail);
        cnumber.setText(snoumber);
        caddress.setText(scaddress);


        SharedPreferences sharedPreferences1=getSharedPreferences("AddressData",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences1.edit();
        editor.putString("completeaddress",caddress.getText().toString());
        editor.apply();
        editor.commit();


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = cmail.getText().toString().trim();

                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                if(cname.getText().toString().isEmpty())
                {
                    cname.requestFocus();
                    cname.setError("provide name");
                }
                else if(!email.matches(emailPattern))
                {
                    cmail.requestFocus();
                    cmail.setError("provide proper mail");
                }
                else if(cnumber.length()!=10)
                {
                    cnumber.requestFocus();
                    cnumber.setError("provide number");
                }
                else if(caddress.getText().toString().isEmpty())
                {
                    caddress.requestFocus();
                    caddress.setError("provide address");
                }
                else
                {
                    Intent intent=new Intent(CheckOut.this,PaymentCheckout.class);
                    startActivity(intent);
//
                }
            }
        });

//
//        });
    }
}
