package com.example.anshumvvmdemo;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.anshumvvmdemo.retrofit.DataModel;
import com.example.anshumvvmdemo.retrofit.ResponseClass;
import com.example.anshumvvmdemo.retrofit.RetrofitInstance;

import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainViewModel extends ViewModel {

    MutableLiveData<ArrayList<DataModel>> userLiveData;
    ArrayList<DataModel> userArrayList = new ArrayList<>();


    public MainViewModel() {

        Log.e("Step1", "__________________________");
        userLiveData = new MutableLiveData<>();
        init();
    }


    public MutableLiveData<ArrayList<DataModel>> getUserMutableLiveData(){
        return userLiveData;
    }

    public void init(){
        getDetails();
    }

    public void getDetails(){
        Log.e("Step2", "__________________________");
        try{
            RetrofitInstance.getInstance().getMyApi().getUserDetails()
                    .enqueue(new Callback<ResponseClass>() {
                        @Override
                        public void onResponse(Call<ResponseClass> call, Response<ResponseClass> response) {
                            Log.e("onResponse______", String.valueOf(response.code()));
                            if (response.isSuccessful() && response.body() != null) {
                                Log.e("OnResponse", response.body().toString());

                                for(int i=0; i< response.body().getData().size(); i++) {
                                    userArrayList.add( response.body().getData().get(i));
                                }
                                userLiveData.setValue(userArrayList);
                                Log.e("userArrayList size", String.valueOf(userArrayList.size()));

                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseClass> call, Throwable t) {
                            Log.e("onFailure", t.getMessage());
                            Log.e("onFailure", t.toString());

                        }
                    });

        }catch (Exception e){
            e.printStackTrace();
            Log.e("getDetails", e.getMessage());
        }
    }


}
