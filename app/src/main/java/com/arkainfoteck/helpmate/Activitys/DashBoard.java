package com.arkainfoteck.helpmate.Activitys;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Address;
import android.location.Geocoder;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.view.menu.MenuAdapter;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;


import com.arkainfoteck.helpmate.Adapter.MenuAdapters;
import com.arkainfoteck.helpmate.Adapter.MyAdapter;
import com.arkainfoteck.helpmate.Adapter.ProductAdopter;
import com.arkainfoteck.helpmate.Fragment.Home;
import com.arkainfoteck.helpmate.Fragment.Myservices;
import com.arkainfoteck.helpmate.Fragment.SettingsFragment;

import com.arkainfoteck.helpmate.Model.MenuModel;
import com.arkainfoteck.helpmate.Model.ProductModel;
import com.arkainfoteck.helpmate.R;
import com.arkainfoteck.helpmate.helperclass.ApiClient;
import com.arkainfoteck.helpmate.interfacess.ApiInterface;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import android.graphics.Color;

import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

public class DashBoard extends AppCompatActivity

        implements NavigationView.OnNavigationItemSelectedListener {
    CardView cook,maid, handyman;
    ViewPager viewPager;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    public static String name="";
    public  String name1;
    Spinner spinner;
    AlertDialog.Builder alert;
    AlertDialog dialog;

    String[] plants = new String[]{
            "Select Location",
            "Sr Nagar",
            "Kondapur",
            "Miyapur",
            "Kphp",
            "Esi"
    };
    GetCurrentLocation currentLoc;
    public    String latitude, longitude;

    final List<String> plantsList = new ArrayList<>(Arrays.asList(plants));
    private static final Integer[] images = {R.drawable.logo5122, R.drawable.logo5122, R.drawable.logo5122};
    private ArrayList<Integer> imageArray = new ArrayList<Integer>();
    private static ViewPager mPager;
    private static int currentPage = 0;
    private Intent i;
    double l1,l2;
    Geocoder geocoder;
    List<Address> addresses;
    TextView location;

    RecyclerView recyclerView;
    GridLayoutManager gridLayoutManager;
    List<ProductModel>productModels;
    ProductAdopter productAdopter;
    private ApiInterface apiinterface;


    Toolbar toolbar;
    DrawerLayout  drawer;
    ActionBarDrawerToggle actionBarDrawerToggle;
    ImageView hambarg;
    RecyclerView recyclerView1;
    LinearLayoutManager linearLayoutManager;
    ArrayList<MenuModel> menuModels;
    MenuAdapters menuAdapter;
    Context context=this;
    TextView username;
    TextView usernamedash;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            Fragment fragment=null;
            switch (item.getItemId()) {
                case R.id.navigation_store:

                    fragment=new Home();
                    break;

                case R.id.navigation_delivary:

                    fragment=new Myservices();
                    break;

                case R.id.navigation_wallet:

                    fragment=new SettingsFragment();
                    break;

            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,fragment).commit();
            return true;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

            setContentView(R.layout.activity_dash_board);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container,new Home()).commit();


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View header = navigationView.getHeaderView(0);
        usernamedash=header.findViewById(R.id.usernamedash);

        //for nmae
        sharedPreferences=getSharedPreferences("logindetails",Context.MODE_PRIVATE);
        String username=sharedPreferences.getString("sname",null);
        usernamedash.setText(username);


        toolbar = (Toolbar) findViewById(R.id.toolbar);
        //   setSupportActionBar(toolbar);
        location=findViewById(R.id.location);




        //System.out.println("cureent_location_name_name"+name+"nothimg");
        sharedPreferences=getSharedPreferences("AddressDetails",Context.MODE_PRIVATE);
        name1=sharedPreferences.getString("location",null);
        System.out.println("adding_address_dynamically"+name1);
        location.setText(name1);
        // this is for profile
        sharedPreferences=getSharedPreferences("locationdetails",Context.MODE_PRIVATE);
        editor=sharedPreferences.edit();
        editor.putString("locationprofile",location.getText().toString());
        editor.apply();
        editor.commit();
        currentLoc = new GetCurrentLocation(DashBoard.this);
















        sharedPreferences = getSharedPreferences("logindetails", Context.MODE_PRIVATE);
        String uname = sharedPreferences.getString("password", null);


        if(sharedPreferences!=null) {
            if (uname != null || uname == "") {

            } else {
                Intent it = new Intent(DashBoard.this, Login.class);
                startActivity(it);
            }
        }
        hambarg=(ImageView)findViewById(R.id.hambarg);

        hambarg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(drawer.isDrawerOpen(Gravity.RIGHT)){
                    drawer.closeDrawer(Gravity.RIGHT);

                }else {
                    drawer.openDrawer(Gravity.RIGHT);

                }
            }
        });
        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),ChangeArea.class);
                startActivity(intent);

            }
        });

        spinner=(Spinner)findViewById(R.id.locationspiner);

        final ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                this,R.layout.spinner_item,plantsList){
            @Override
            public boolean isEnabled(int position){
                if(position == 0) {
                    // Disable the first item from Spinner
                    // First item will be use for hint
                    return false;
                } else {
                    return true;
                }
            }
            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if(position == 0){
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY);
                }
                else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        };

        imageArray = new ArrayList<>();
        viewPager = (ViewPager)findViewById(R.id.viewPager);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);



    }

    public  void getdata(){


    }




    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if(id==R.id.invitefriends)
        {
            Intent sharingIntent = new Intent(Intent.ACTION_SEND);
            sharingIntent.setType("text/plain");
            String shareBody = "url";
            String shareSub = "this is referalcode";
            sharingIntent.putExtra(Intent.EXTRA_SUBJECT, shareSub);
            sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
            startActivity(Intent.createChooser(sharingIntent, "Share using"));

        }
        else if(id==R.id.help)
        {
            Intent intent=new Intent(getApplicationContext(),HelpAndSupport.class);
            startActivity(intent);
        }
        else if(id==R.id.logout){
            Intent intent=new Intent(getApplicationContext(),Login.class);
            getApplicationContext().getSharedPreferences("logindetails", 0).edit().clear().commit();
            startActivity(intent);

        }



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.END);
        return true;
    }
    @Override
    public void onBackPressed() {

        LayoutInflater inflater = getLayoutInflater();
        View alertLayout = inflater.inflate(R.layout.homelayout, null);
        Button ok = alertLayout.findViewById(R.id.ok);
        Button cancel = alertLayout.findViewById(R.id.cancel);
        alert = new AlertDialog.Builder(DashBoard.this);

        alert.setView(alertLayout);
        // alert.setCancelable(false);
        dialog = alert.create();

        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // super.onBackPressed();
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
                System.exit(0);

            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();

            }
        });

    }



}