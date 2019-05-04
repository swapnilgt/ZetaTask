package com.example.zetatask.data;

import com.example.zetatask.AppExecutors;
import com.example.zetatask.data.pojos.ItemsWrapper;
import com.example.zetatask.data.retrofit.QueryRestClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class QueryRepoImpl implements QueryRepo {


    private static QueryRepoImpl INSTANCE;
    private static final Object LOCK = new Object();
    private QueryRestClient mRestClient;
    private static final String API_KEY = "AIzaSyC54p1HKS0HZ16sTe6LNEty4qXfEDqhmnc";
    private static final String CX = "011476162607576381860:ra4vmliv9ti";


    // Private constructor to make this single-ton ....
    private QueryRepoImpl() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.googleapis.com/customsearch/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mRestClient = retrofit.create(QueryRestClient.class);
    }

    public static QueryRepoImpl getInstance() {

        if(INSTANCE == null) {
            synchronized (LOCK) {
                if(INSTANCE == null) {
                    INSTANCE = new QueryRepoImpl();
                }
            }
        }

        return INSTANCE;
    }

    @Override
    public void getQueryItems(String search, QueryCallback callback) {
        AppExecutors.getInstance().networkIO().execute(() -> {
            Call<ItemsWrapper> call = mRestClient.listItems(search, CX, API_KEY);
            call.enqueue(new Callback<ItemsWrapper>() {
                @Override
                public void onResponse(Call<ItemsWrapper> call, Response<ItemsWrapper> response) {
                    AppExecutors.getInstance().mainThread().execute(() -> {
                        if(response.isSuccessful()) {
                            callback.onResult(response.body(), null);
                        } else {
                            callback.onResult(null, new Exception(response.message()));
                        }
                    });
                }

                @Override
                public void onFailure(Call<ItemsWrapper> call, Throwable t) {
                    AppExecutors.getInstance().mainThread().execute(() -> {
                        callback.onResult(null, t);
                    });
                }
            });
        });
    }
}
