package com.example.anshumvvmdemo;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.anshumvvmdemo.retrofit.DataModel;
import com.example.anshumvvmdemo.retrofit.ResponseClass;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{

    ArrayList<DataModel> arrayList ;

    public MyAdapter() {
        this.arrayList = new ArrayList<>();
    }

    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater =  LayoutInflater.from(parent.getContext());
        View view =  inflater.inflate(R.layout.user_details_row , parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {

        Log.e("Step5", "__________________________");
        DataModel dataModel = arrayList.get(position);

        holder.username.setText(dataModel.getFirstName());
        holder.userEmail.setText(dataModel.getEmail());

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public void updateUserList(final ArrayList<DataModel> userArrayList) {
        Log.e("Step4", "__________________________");
        this.arrayList.clear();
        this.arrayList = userArrayList;
        notifyDataSetChanged();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView username, userEmail;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            username = itemView.findViewById(R.id.username);
            userEmail = itemView.findViewById(R.id.userEmail);
        }
    }

}
