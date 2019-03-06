package com.arkainfoteck.helpmate.Activitys;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.ListView;

import com.arkainfoteck.helpmate.R;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    ListView listView;


    GetCurrentLocation currentLoc;
    public    String latitude, longitude;
    double l1,l2;
    Geocoder geocoder;
    List<Address> addresses;
    public static String result;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


}
