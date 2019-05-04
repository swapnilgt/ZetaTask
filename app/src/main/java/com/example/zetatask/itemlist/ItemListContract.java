package com.example.zetatask.itemlist;

import com.example.zetatask.data.pojos.Item;

import java.util.List;

public interface ItemListContract {

    interface Presenter {

        void onSearch(String query);

        void onClick(Item item);
    }

    interface View {

        void setList(List<Item> itemsList);

        void showError(String msg);
    }
}
