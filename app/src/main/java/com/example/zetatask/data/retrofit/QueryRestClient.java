package com.example.zetatask.data.retrofit;

import com.example.zetatask.data.pojos.ItemsWrapper;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface QueryRestClient {

    @GET(".")
    Call<ItemsWrapper> listItems(@Query("q")String search, @Query("cx") String cx, @Query("key")String apiKey);

}
