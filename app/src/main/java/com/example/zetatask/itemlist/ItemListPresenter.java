package com.example.zetatask.itemlist;

import com.example.zetatask.data.QueryRepo;
import com.example.zetatask.data.pojos.Item;

import java.lang.ref.WeakReference;

public class ItemListPresenter implements ItemListContract.Presenter {


    private final QueryRepo mRepo;
    private final WeakReference<ItemListContract.View> mView;

    public ItemListPresenter(QueryRepo mRepo, ItemListContract.View mView) {
        this.mRepo = mRepo;
        this.mView = new WeakReference<>(mView);
    }

    @Override
    public void onSearch(String query) {
        mRepo.getQueryItems(query, (items, t) -> {
            if(mView.get() != null) {
                if(t == null) {
                    mView.get().setList(items.getItems());
                } else {
                    mView.get().showError(t.getMessage());
                }
            }
        });
    }

    @Override
    public void onClick(Item item) {

    }
}
