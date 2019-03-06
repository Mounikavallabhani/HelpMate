package com.arkainfoteck.helpmate.Activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.arkainfoteck.helpmate.R;

public class Settings extends AppCompatActivity {
    Button changepassword,editprofile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        changepassword=findViewById(R.id.changepassword);
        editprofile=findViewById(R.id.editprofile);
        changepassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),ChangePassword.class);
                startActivity(intent);
            }
        });
        editprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),EditProfile.class);
                startActivity(intent);

            }
        });
    }
}
