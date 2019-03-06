package com.arkainfoteck.helpmate.Activitys;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.arkainfoteck.helpmate.R;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;


public class Splash extends AppCompatActivity {
      public static boolean text=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ImageView imageView = findViewById(R.id.imageView);
        ActivityCompat.requestPermissions(Splash.this, new String[]{ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION}, 100);
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade);
        imageView.startAnimation(animation);


        final Thread timer = new Thread(){

            @Override
            public void run() {

                try {
                    sleep(3000);
                    if(text) {
                        Intent intent = new Intent(getApplicationContext(), Login.class);
                        startActivity(intent);
                        finish();
                    }else {
                        Intent intent = new Intent(getApplicationContext(), DashBoard.class);
                        startActivity(intent);
                        finish();
                    }

                    super.run();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }
        };

        timer.start();
    }


}