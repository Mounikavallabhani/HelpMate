package com.arkainfoteck.helpmate.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.arkainfoteck.helpmate.Activitys.DashBoard;
import com.arkainfoteck.helpmate.Adapter.JobHistoryAdapter;
import com.arkainfoteck.helpmate.Model.JobHistoryModel;
import com.arkainfoteck.helpmate.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Myservices extends Fragment {
    RecyclerView recyclerView;
    public JobHistoryAdapter jobHistoryAdapter;
    ArrayList<JobHistoryModel> jobHistoryModels;
    GridLayoutManager gridLayoutManager;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String registerid;
    View view;
    String image;
    String maid_book_id,timeing,orderedtime,no_hour,location,price,status,train,type,no_maids,dates,net_price;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_job_history, container, false);
        recyclerView=(RecyclerView)view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        gridLayoutManager=new GridLayoutManager(getActivity(),1);
        recyclerView.setLayoutManager(gridLayoutManager);

        sharedPreferences=getActivity().getSharedPreferences("logindetails",Context.MODE_PRIVATE);
        registerid=sharedPreferences.getString("registerid",null);
           getDataFromServer();

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




        return view ;
    }
    public void getDataFromServer(){
        RequestQueue rq = Volley.newRequestQueue(getContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                "http://broomsticks.in/index.php/api/complete_orders",

                new Response.Listener<String>() {

                    public void onResponse(String response) {

                        try {
                            //     Toast.makeText(Login.this," "+response,Toast.LENGTH_LONG).show();
                            JSONObject jsonObject1=new JSONObject(response);
                            //   Toast.makeText(Login.this,""+jsonObject1,Toast.LENGTH_LONG).show();
                            jobHistoryModels=new ArrayList<>();
                            String data=jsonObject1.getString("data");
                            JSONArray jsonObject12=new JSONArray(data);
                            for(int i=0;i<jsonObject12.length();i++){
                                JSONObject jsonObject=jsonObject12.getJSONObject(i);
                                type=jsonObject.getString("type");
                               maid_book_id=jsonObject.getString("maid_book_id");
                               timeing=jsonObject.getString("timeing");
                               orderedtime=jsonObject.getString("orderedtime");
                               no_hour=jsonObject.getString("no_hour");
                               location=jsonObject.getString("location");
                                price=jsonObject.getString("price");
                                status="http://arkainfoteck.xyz/helpmate/dynamic/assets/ns/cook.png";
                                train="http://arkainfoteck.xyz/helpmate/dynamic/assets/ns/cook.png";
                                image=jsonObject.getString("image");
                                no_maids=jsonObject.getString("no_maids");
                                dates=jsonObject.getString("dates");
                                net_price=jsonObject.getString("net_price");

                                jobHistoryModels.add(new JobHistoryModel(maid_book_id,type,timeing,orderedtime,no_hour,location,price,image,no_maids,dates,net_price));

                            }

                            jobHistoryAdapter=new JobHistoryAdapter(getActivity(),jobHistoryModels);
                            recyclerView.setAdapter(jobHistoryAdapter);


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


                return params;
            }
        };
        rq.add(stringRequest);
    }


}
