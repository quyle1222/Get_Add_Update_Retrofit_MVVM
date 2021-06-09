package com.example.retrofit_get_update_add.retrofit;

import com.example.retrofit_get_update_add.model.Items;
import com.example.retrofit_get_update_add.model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiRequest {
    //https://60bd8e89ace4d50017aab30d.mockapi.io/data/list

    @GET("data/users")
    Call<Items> getListUser();

    @POST("data/users")
    Call<Items> postListUser(@Body User User);

    @PUT("data/users/{id}")
    Call<Items> updateUser(@Path("id") String id,
                           @Body User user);

}
