package com.example.anshumvvmdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.example.anshumvvmdemo.databinding.ActivityMainBinding;
import com.example.anshumvvmdemo.retrofit.DataModel;
import com.example.anshumvvmdemo.retrofit.ResponseClass;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements LifecycleOwner {

   private ActivityMainBinding binding;
    MyAdapter adapter;
    MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        adapter = new MyAdapter();
        // Getting Data From APi

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        binding.recyclerView.setLayoutManager(linearLayoutManager);
        binding.recyclerView.setAdapter(adapter);

        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        Log.e("Step1", "__________________________");
        viewModel.getUserMutableLiveData().observe(MainActivity.this, userListUpdateObserver);


    }

    Observer<ArrayList<DataModel>> userListUpdateObserver =
            new Observer<ArrayList<DataModel>>() {
                @Override
                public void onChanged(ArrayList<DataModel> responseClasses) {
                    Log.e("Step2", "__________________________");
                    adapter.updateUserList(responseClasses);
                }
            };


}