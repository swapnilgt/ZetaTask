package com.example.zetatask.data;

import com.example.zetatask.data.pojos.ItemsWrapper;

public interface QueryRepo {

    interface QueryCallback {
        void onResult(ItemsWrapper items, Throwable t);
    }

    void getQueryItems(String search, QueryCallback callback);
}
