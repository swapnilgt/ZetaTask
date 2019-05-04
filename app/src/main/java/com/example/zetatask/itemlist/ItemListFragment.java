package com.example.zetatask.itemlist;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.zetatask.R;
import com.example.zetatask.data.QueryRepoImpl;
import com.example.zetatask.data.pojos.Item;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ActivityListener} interface
 * to handle interaction events.
 * Use the {@link ItemListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ItemListFragment extends Fragment implements ItemListContract.View {

    @BindView(R.id.tvInput)
    TextInputEditText input;
    @BindView(R.id.rvItemList)
    RecyclerView recyclerView;
    @BindView(R.id.pbProgress)
    ProgressBar progressBar;

    private View mView;

    private static final String TAG = "ItemListFragment";

    private ActivityListener mActivityListener;

    private ItemListAdapter mAdapter;
    private ItemListPresenter mPresenter;

    private final ItemListAdapter.ItemClickListener mItemListener = new ItemListAdapter.ItemClickListener() {
        @Override
        public void onClickItem(Item item) {
            mActivityListener.onClickItem(item);
        }
    };

    public ItemListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ItemListFragment.
     */
    public static ItemListFragment newInstance() {
        ItemListFragment fragment = new ItemListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }

        mAdapter = new ItemListAdapter(new ArrayList<>(), mItemListener);
        mPresenter = new ItemListPresenter(QueryRepoImpl.getInstance(), this);

        Log.d(TAG, "Inside onCreate()");
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if(mView == null) {
            mView = inflater.inflate(R.layout.fragment_item_list_framgent, container, false);
            ButterKnife.bind(this, mView);

            recyclerView.setAdapter(mAdapter);

            final LinearLayoutManager lm = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
            recyclerView.setLayoutManager(lm);
        }

        Log.d(TAG, "Inside onCreateView()");

        return mView;
    }


    @SuppressWarnings("unused")
    @OnClick(R.id.button)
    void onClickSearch(View v) {
        mPresenter.onSearch(input.getText().toString());
        progressBar.setVisibility(View.VISIBLE);
        if(getActivity() != null) {
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
            if (mView != null) {
                imm.hideSoftInputFromWindow(mView.getWindowToken(), 0);
            }
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ActivityListener) {
            mActivityListener = (ActivityListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement ActivityListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mActivityListener = null;
    }

    @Override
    public void setList(List<Item> itemsList) {
        mAdapter.updateList(itemsList);
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showError(String msg) {
        progressBar.setVisibility(View.INVISIBLE);
        Toast.makeText(getContext(), msg, Toast.LENGTH_LONG).show();
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface ActivityListener {
        void onClickItem(Item item);
    }
}
