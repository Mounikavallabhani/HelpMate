package com.arkainfoteck.helpmate.Activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.arkainfoteck.helpmate.R;

public class Successpayment extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_successpayment);
    }

    @Override
    public void onBackPressed() {
        Intent intent=new Intent(getApplicationContext(),DashBoard.class);
        startActivity(intent);
    }
}
