package com.arkainfoteck.helpmate.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.arkainfoteck.helpmate.Activitys.ChangePassword;
import com.arkainfoteck.helpmate.Activitys.DashBoard;
import com.arkainfoteck.helpmate.Activitys.EditProfile;
import com.arkainfoteck.helpmate.R;

public class SettingsFragment extends Fragment {
    Button changepassword,editprofile;
    View view;
    TextView username,usermail,userph,useraddress;
    SharedPreferences sharedPreferences;
    String susername,susermail,sph,location;
    Fragment fragment ;
    FragmentTransaction transaction;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_settings, container, false);
        changepassword=view.findViewById(R.id.changepassword);
        username=(TextView)view.findViewById(R.id.username);
        usermail=(TextView)view.findViewById(R.id.usermail);
        userph=(TextView)view.findViewById(R.id.userph);
        useraddress=(TextView)view.findViewById(R.id.useraddress);
        editprofile=view.findViewById(R.id.editprofile);


        sharedPreferences=getActivity().getSharedPreferences("logindetails",Context.MODE_PRIVATE);
        susername=sharedPreferences.getString("sname",null);
        sph=sharedPreferences.getString("snoumber",null);
        susermail=sharedPreferences.getString("gmail",null);

        sharedPreferences=getActivity().getSharedPreferences("locationdetails",Context.MODE_PRIVATE);
      location=sharedPreferences.getString("locationprofile",null);




         System.out.print("q34e5"+susername);
        System.out.print("cvbnm"+susermail);
        System.out.print("dfgj"+sph);


         username.setText(susername);
         usermail.setText(susermail);
         userph.setText(sph);
         useraddress.setText(location);

        changepassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),ChangePassword.class);
                startActivity(intent);
            }
        });
        editprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),EditProfile.class);
                startActivity(intent);

            }
        });

        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(event.getAction()==KeyEvent.ACTION_DOWN) {
                    if(keyCode==KeyEvent.KEYCODE_BACK) {

                        Intent intent=new Intent(getActivity(),DashBoard.class);
                        getActivity().startActivity(intent);

                    }
                }
                return false;
            }
        });






        return view;
    }

}
