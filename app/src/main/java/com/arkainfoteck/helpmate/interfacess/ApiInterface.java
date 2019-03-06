package com.arkainfoteck.helpmate.interfacess;

import com.arkainfoteck.helpmate.Model.ProductModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.POST;

public interface ApiInterface {

    @POST("products")
    Call<List<ProductModel>> getContacts();


}
