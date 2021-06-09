package com.example.retrofit_get_update_add.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.retrofit_get_update_add.model.Items;
import com.example.retrofit_get_update_add.model.User;
import com.example.retrofit_get_update_add.retrofit.ApiRequest;
import com.example.retrofit_get_update_add.retrofit.RetrofitRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Repository {
    private ApiRequest apiRequest;

    public Repository() {
        apiRequest = RetrofitRequest.getRetrofitInstance().create(ApiRequest.class);
    }

    public LiveData<Items> getListUser() {
        final MutableLiveData<Items> data = new MutableLiveData<>();
        apiRequest.getListUser().enqueue(new Callback<Items>() {
            @Override
            public void onResponse(Call<Items> call, Response<Items> response) {
                response.body();
                if (response.body() != null) {
                    data.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<Items> call, Throwable t) {
                data.setValue(null);
            }
        });
        return data;
    }

    public LiveData<Items> postListUser(User user) {
        final MutableLiveData<Items> data = new MutableLiveData<>();
        apiRequest.postListUser(user).enqueue(new Callback<Items>() {
            @Override
            public void onResponse(Call<Items> call, Response<Items> response) {
                if (response.body() != null) {
                    data.setValue(response.body());
                }
            }
            @Override
            public void onFailure(Call<Items> call, Throwable t) {
                data.setValue(null);
            }
        });
        return data;
    }

    public LiveData<Items> updateUser(String id, User user) {
        final MutableLiveData<Items> data = new MutableLiveData<>();
        apiRequest.updateUser(id,user).enqueue(new Callback<Items>() {
            @Override
            public void onResponse(Call<Items> call, Response<Items> response) {
                if (response.body() != null) {
                    data.setValue(response.body());
                }
            }
            @Override
            public void onFailure(Call<Items> call, Throwable t) {
                data.setValue(null);
            }
        });
        return data;
    }
}
