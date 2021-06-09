package com.example.retrofit_get_update_add;

import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retrofit_get_update_add.adapter.RecyclerViewAdapter;
import com.example.retrofit_get_update_add.model.User;
import com.example.retrofit_get_update_add.viewmodel.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ViewModel viewModel;
    ArrayList<User> userModelArrayList = new ArrayList<User>();
    RecyclerViewAdapter adapter;
    private RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        getListUser();
    }

    private void initView() {

        layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(layoutManager);
        linearLayout = findViewById(R.id.layoutContainer);
        recyclerView.setHasFixedSize(true);
        adapter = new RecyclerViewAdapter(MainActivity.this, userModelArrayList, linearLayout);
        recyclerView.setAdapter(adapter);
        viewModel = ViewModelProviders.of(this).get(ViewModel.class);
    }

    private void getListUser() {
        viewModel.getUserListLiveData().observe(this, userModel -> {
            if (userModel != null) {
                List<User> users = userModel.getItem();
                userModelArrayList.addAll(users);
                adapter.notifyDataSetChanged();
            }
        });
    }
}