package com.example.zetatask.itemlist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import com.example.zetatask.R;
import com.example.zetatask.data.pojos.Item;
import com.example.zetatask.itemdetail.ItemDetailFragment;

public class ItemListActivity extends AppCompatActivity implements ItemListFragment.ActivityListener {

    private FragmentManager mFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);
        mFragmentManager = getSupportFragmentManager();

        setupFragment();
    }

    private void setupFragment() {

        mFragmentManager.beginTransaction().add(R.id.fragmentHolder, ItemListFragment.newInstance()).commit();

    }

    @Override
    public void onClickItem(Item item) {
        mFragmentManager
                .beginTransaction()
                .addToBackStack(null)
                .replace(R.id.fragmentHolder, ItemDetailFragment.newInstance(item.getPageMap().getCseImage().get(0).getSrc())).commit();
    }
}
