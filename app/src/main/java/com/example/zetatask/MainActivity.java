package com.example.zetatask;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import com.example.zetatask.data.pojos.Item;
import com.example.zetatask.itemdetail.ItemDetailFragment;
import com.example.zetatask.itemlist.ItemListFragment;

public class MainActivity extends AppCompatActivity implements ItemListFragment.ActivityListener {


    private FragmentManager mFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFragmentManager = getSupportFragmentManager();

        setupFragment();
    }

    private void setupFragment() {

        mFragmentManager.beginTransaction().add(R.id.fragmentHolder, ItemListFragment.newInstance()).commit();

    }

    @Override
    public void onClickItem(Item item) {
        mFragmentManager.beginTransaction().addToBackStack(null).replace(R.id.fragmentHolder, ItemDetailFragment.newInstance(item.getPageMap().getCseImage().get(0).getSrc())).commit();
    }
}
