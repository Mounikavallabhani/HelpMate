package com.arkainfoteck.helpmate.Activitys;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.arkainfoteck.helpmate.Adapter.OrderHistoryAdapter;
import com.arkainfoteck.helpmate.Model.JobHistoryModel;
import com.arkainfoteck.helpmate.Model.OrderHistoryModel;
import com.arkainfoteck.helpmate.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class OrderHistory extends AppCompatActivity {
    RecyclerView orderrecycle;
    OrderHistoryAdapter orderHistoryAdapter;
    ArrayList<OrderHistoryModel> orderHistoryModels;
    GridLayoutManager gridLayoutManager;
    String cook_id,id,phone,cook_image,active_status,cook_name;
    SharedPreferences sharedPreferences;
    String bookingid,typeof,registerid;
    LinearLayout ll_1;
    TextView messagetext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history);
        orderrecycle = findViewById(R.id.orderrecycle);
        ll_1=findViewById(R.id.ll_1);
        messagetext=findViewById(R.id.messagetext);
        sharedPreferences=getSharedPreferences("bookingid",Context.MODE_PRIVATE);
         bookingid=sharedPreferences.getString("booking",null);
         typeof=sharedPreferences.getString("type",null);
        sharedPreferences=getSharedPreferences("logindetails",Context.MODE_PRIVATE);
        registerid=sharedPreferences.getString("registerid",null);

//
        System.out.println("sadfgh"+bookingid);
        System.out.println("dfghj"+typeof);
        System.out.println("xcvb"+registerid);

        getDataFromServer();




    }


    public void getDataFromServer(){
        RequestQueue rq = Volley.newRequestQueue(OrderHistory.this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                "http://broomsticks.in/index.php/api/cookdetails",

                new Response.Listener<String>() {

                    public void onResponse(String response) {

                        try {
                            // Toast.makeText(OrderHistory.this," "+response,Toast.LENGTH_LONG).show();
                            System.out.print("asdfs" + response);
                            JSONObject jsonObject1 = new JSONObject(response);
                            String message = jsonObject1.getString("message");
                            System.out.println("werty"+message);
                            if (message.equals("Cook are not assigned.."))
                            {
                                ll_1.setVisibility(View.VISIBLE);
                                messagetext.setText(message);

                                //Toast.makeText(OrderHistory.this,""+jsonObject1,Toast.LENGTH_LONG).show();

                        }
                        else if (message.equals("Maid are not assigned.."))
                            {
                                ll_1.setVisibility(View.VISIBLE);
                                messagetext.setText(message);

                                //Toast.makeText(OrderHistory.this,""+jsonObject1,Toast.LENGTH_LONG).show();

                            }
                            else if (message.equals("Handyman are not assigned.."))
                            {
                                ll_1.setVisibility(View.VISIBLE);
                                messagetext.setText(message);

                                //Toast.makeText(OrderHistory.this,""+jsonObject1,Toast.LENGTH_LONG).show();

                            }
                            else
                            {
                                gridLayoutManager = new GridLayoutManager(getApplicationContext(), 1);
                                orderrecycle.setLayoutManager(gridLayoutManager);
                                orderHistoryModels = new ArrayList<>();


                                String data = jsonObject1.getString("data");


                                JSONArray jsonObject12 = new JSONArray(data);
                                for (int i = 0; i < jsonObject12.length(); i++) {
                                    JSONObject jsonObject = jsonObject12.getJSONObject(i);
                                    cook_id = jsonObject.getString("cook_id");
                                    id = jsonObject.getString("id");
                                    phone = jsonObject.getString("phone");
                                    cook_name = jsonObject.getString("cook_name");
                                    cook_image = jsonObject.getString("cook_image");
                                    active_status = jsonObject.getString("active_status");

                                    orderHistoryModels.add(new OrderHistoryModel(cook_image, cook_name, phone, active_status, id, cook_id));

                                }

                                orderHistoryAdapter = new OrderHistoryAdapter(OrderHistory.this, orderHistoryModels);
                                orderrecycle.setAdapter(orderHistoryAdapter);


                            }

                            // JsonArray jsonElements=jsonObject1.getJSONArray("data");
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
                System.out.println("bookid"+bookingid);
                params.put("booking_id",bookingid.toString());

                params.put("type",typeof.toString());
                params.put("user_id",registerid.toString());
                return params;
            }
        };
        rq.add(stringRequest);
    }
}
