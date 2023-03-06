package com.example.anshumvvmdemo.retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    String baseUrl ="https://reqres.in/";

    @GET("api/users")
    Call<ResponseClass> getUserDetails();

}
