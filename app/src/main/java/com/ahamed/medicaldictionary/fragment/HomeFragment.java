package com.ahamed.medicaldictionary.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ahamed.medicaldictionary.adapter.MedicineAdapter;
import com.ahamed.medicaldictionary.databinding.FragmentHomeBinding;
import com.ahamed.medicaldictionary.model.MedicineModel;
import com.ahamed.medicaldictionary.viewModel.DatabaseViewModel;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {

    private List<MedicineModel> mProductList;
    private MedicineAdapter adapter;
    private int current;
    private int total;
    private int scroll_out;
    private boolean isScrolling = false;
    private DatabaseViewModel viewModel;
    private int limit = 100;
    private FragmentHomeBinding binding;
    private LifecycleOwner lifecycleOwner;
    private int position = 0;

    public HomeFragment() {
        // Required empty public constructor
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        mProductList = new ArrayList<>();
        viewModel = new ViewModelProvider(requireActivity()).get(DatabaseViewModel.class);
        adapter = new MedicineAdapter(mProductList, i -> {
            if (i > 5) {
                position = i - 2;
            } else {
                position = i;
            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        binding.rvMedicine.setLayoutManager(linearLayoutManager);
        binding.rvMedicine.setAdapter(adapter);
        lifecycleOwner = getViewLifecycleOwner();

        binding.rvMedicine.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    isScrolling = true;
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                current = linearLayoutManager.getChildCount();
                total = linearLayoutManager.getItemCount();
                scroll_out = linearLayoutManager.findFirstCompletelyVisibleItemPosition();
                if (isScrolling && (current + scroll_out == total)) {
                    position = current;
                    dataFetch(limit += 100);
                }
            }
        });

        viewModel.getAllMedicine(1, limit).observe(getViewLifecycleOwner(), productModels -> {
            mProductList.clear();
            mProductList.addAll(productModels);
            adapter.notifyDataSetChanged();
            binding.rvMedicine.scrollToPosition(position);
        });
        return binding.getRoot();
    }

    @SuppressLint("NotifyDataSetChanged")
    private void dataFetch(int count) {
        binding.progressBar.setVisibility(View.VISIBLE);
        new Handler().postDelayed(() -> {
            Log.d("TAG", "dataFetch: " + count);
            viewModel.getAllMedicine(1, count).observe(lifecycleOwner, productModels -> {
                mProductList.clear();
                mProductList.addAll(productModels);
                adapter.notifyDataSetChanged();
                binding.progressBar.setVisibility(View.GONE);
            });
        }, 1000);
    }
}