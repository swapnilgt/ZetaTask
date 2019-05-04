package com.example.zetatask.itemlist;

import android.content.ClipData;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.zetatask.R;
import com.example.zetatask.data.pojos.Item;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ItemListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Item> mItems;
    private final ItemClickListener mListener;

    public ItemListAdapter(List<Item> items, ItemClickListener listener) {
        mItems = items;
        mListener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_holder, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if(mItems.get(position).getPageMap() != null && mItems.get(position).getPageMap().getCseThumbnail() != null && mItems.get(position).getPageMap().getCseThumbnail().size() > 0) {
            final String url = mItems.get(position).getPageMap().getCseThumbnail().get(0).getSrc();
            Glide.with(holder.itemView.getContext()).load(url).into(((ItemViewHolder) holder).itemImage);
            holder.itemView.setOnClickListener(view -> {
                mListener.onClickItem(mItems.get(position));
            });
        } else {
            holder.itemView.setOnClickListener(null);
        }


    }

    void updateList(List<Item> newList) {
        mItems = newList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.ivItemImage)
        ImageView itemImage;

        ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    interface ItemClickListener {
        void onClickItem(Item item);
    }
}
