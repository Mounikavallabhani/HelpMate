package com.arkainfoteck.helpmate.Activitys;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Address;
import android.location.Geocoder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.arkainfoteck.helpmate.Adapter.LocationAdapter;
import com.arkainfoteck.helpmate.Adapter.OrderHistoryAdapter;
import com.arkainfoteck.helpmate.Model.LocationModel;
import com.arkainfoteck.helpmate.Model.OrderHistoryModel;
import com.arkainfoteck.helpmate.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ChangeArea extends AppCompatActivity {

    TextView curraentlocation,addaddress;
    LocationAdapter locationAdapter;
    DatabaseHelper database;
    RecyclerView recyclerView;
    ArrayList<DataModel> datamodel;
    LinearLayoutManager linearLayoutManager;

    GetCurrentLocation currentLoc;
    public    String latitude, longitude;
    double l1,l2;
    Geocoder geocoder;
    List<Address> addresses;
    String Current_Location;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_area);
        currentLoc = new GetCurrentLocation(ChangeArea.this);

        recyclerView=findViewById(R.id.recyclelocation);
        curraentlocation=findViewById(R.id.currentlocation);
        addaddress=findViewById(R.id.addaddres);



        datamodel =new ArrayList<DataModel>();





        database = new DatabaseHelper(ChangeArea.this);
        datamodel=  database.getdata();
        locationAdapter =new LocationAdapter(getApplication(),datamodel);


        Log.i("HIteshdata",""+datamodel);
        LinearLayoutManager reLayoutManager =new LinearLayoutManager(getApplicationContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(reLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(locationAdapter);





        curraentlocation.setOnClickListener(
                new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                latitude = currentLoc.latitude;
                longitude = currentLoc.longitude;

                if (TextUtils.isEmpty(latitude) || TextUtils.isEmpty(longitude)) {
                    latitude = "0.00";
                    longitude = "0.00";
                }


                l1 = Double.parseDouble(latitude.toString());
                l2 = Double.parseDouble(longitude.toString());

                geocoder = new Geocoder(ChangeArea.this, Locale.getDefault());
                try {
                    addresses = geocoder.getFromLocation(l1, l2, 1);

                    if (addresses != null) {
                        Address returnedAddress = addresses.get(0);
                        StringBuilder strReturnedAddress = new StringBuilder("");

                        for (int i = 0; i <= returnedAddress.getMaxAddressLineIndex(); i++) {
                            strReturnedAddress.append(returnedAddress.getAddressLine(i)).append("\n");
                        }

                        Current_Location = strReturnedAddress.toString();
                        sharedPreferences = getSharedPreferences("AddressDetails", Context.MODE_PRIVATE);
                        editor = sharedPreferences.edit();
                        editor.putString("location", Current_Location);
                        editor.commit();
                        editor.apply();

                        Intent intent=new Intent(ChangeArea.this,DashBoard.class);
                        startActivity(intent);

                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }




            }
        });
        addaddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ChangeArea.this,AddAdress.class);
                startActivity(intent);

            }
        });


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
}
