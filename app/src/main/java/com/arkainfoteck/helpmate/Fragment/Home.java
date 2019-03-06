package com.arkainfoteck.helpmate.Fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.arkainfoteck.helpmate.Adapter.MyAdapter;
import com.arkainfoteck.helpmate.Adapter.ProductAdopter;
import com.arkainfoteck.helpmate.Adapter.ViewPagerAdapter;
import com.arkainfoteck.helpmate.Model.ProductModel;
import com.arkainfoteck.helpmate.Model.SliderUtils;
import com.arkainfoteck.helpmate.R;
import com.arkainfoteck.helpmate.helperclass.ApiClient;
import com.arkainfoteck.helpmate.interfacess.ApiInterface;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Home extends Fragment {
    private static final Integer[] images = {R.drawable.logo5122, R.drawable.logo5122, R.drawable.logo5122};
    private ArrayList<Integer> imageArray = new ArrayList<Integer>();
    private static ViewPager mPager;
    private static int currentPage = 0;
    RecyclerView recyclerView;
    GridLayoutManager gridLayoutManager;
    List<ProductModel> productModels;
    ProductAdopter productAdopter;
    private ApiInterface apiinterface;
    View view;
    String request_url="http://broomsticks.in/index.php/api/app_slides";
    List<SliderUtils> sliderImg;
    ViewPagerAdapter viewPagerAdapter;
    ViewPager viewPager;
    private int dotscount;

    private ImageView[] dots;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.homefragment, container, false);
        //init();
        getjsondata();



        sliderImg = new ArrayList<>();

        viewPager = (ViewPager)view.findViewById(R.id.viewPager);
        getOfferdata();
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                for(int i = 0; i< dotscount; i++){

                }



            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        return view ;

    }

    public  void getjsondata() {
        // grid layout
        recyclerView = (RecyclerView)view.findViewById(R.id.products);
        gridLayoutManager = new GridLayoutManager(getActivity(), 1);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setHasFixedSize(true);
        productModels = new ArrayList<>();
        apiinterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<List<ProductModel>> call = apiinterface.getContacts();
        call.enqueue(new Callback<List<ProductModel>>() {
            @Override
            public void onResponse(Call<List<ProductModel>> call, Response<List<ProductModel>> response) {
                productModels = response.body();

                productAdopter = new ProductAdopter(getActivity(), productModels);
                recyclerView.setAdapter(productAdopter);
            }

            @Override
            public void onFailure(Call<List<ProductModel>> call, Throwable t) {

            }
        });
    }


    public void  getOfferdata(){

        RequestQueue rq = Volley.newRequestQueue(getActivity());
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                request_url,
                new com.android.volley.Response.Listener<String>() {

                    public void onResponse(String response) {

                        //       System.out.println("Narasimha"+response);
                        try {
                            JSONArray jsonObject=new JSONArray(response);

                            for(int i=0;i<jsonObject.length();i++){
                                JSONObject jsonObject1=jsonObject.getJSONObject(i);
                                SliderUtils sliderUtils = new SliderUtils();
                                sliderUtils.setSliderImageUrl(jsonObject1.getString("image"));
                                sliderImg.add(sliderUtils);
                                viewPagerAdapter = new ViewPagerAdapter(sliderImg, getActivity());

                                viewPager.setAdapter(viewPagerAdapter);

                            }

                            dotscount = viewPagerAdapter.getCount();
                            dots = new ImageView[dotscount];

                            for(int i = 0; i < dotscount; i++){

                                dots[i] = new ImageView(getActivity());


                                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);

                                params.setMargins(8, 0, 8, 0);



                            }



                            final Handler handler = new Handler();
                            final Runnable Update = new Runnable() {
                                public void run() {
                                    if (currentPage == sliderImg.size()) {
                                        currentPage = 0;
                                    }
                                    viewPager.setCurrentItem(currentPage++, true);
                                }
                            };
                            Timer swipeTimer = new Timer();
                            swipeTimer.schedule(new TimerTask() {
                                @Override
                                public void run() {
                                    handler.post(Update);
                                }
                            }, 2500, 2500);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    };
                }, new com.android.volley.Response.ErrorListener() {

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



                return params;
            }
        };
        rq.add(stringRequest);
    }

}
