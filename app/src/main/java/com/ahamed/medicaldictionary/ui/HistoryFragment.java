package com.ahamed.medicaldictionary.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.ahamed.medicaldictionary.R;
import com.ahamed.medicaldictionary.adapter.HistoryAdapter;
import com.ahamed.medicaldictionary.databinding.FragmentHistoryBinding;
import com.ahamed.medicaldictionary.model.HistoryModel;
import com.ahamed.medicaldictionary.model.MedicineModel;
import com.ahamed.medicaldictionary.viewModel.HistoryViewModel;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.ArrayList;
import java.util.List;


public class HistoryFragment extends Fragment {

    public HistoryFragment() {
        // Required empty public constructor
    }


    @SuppressLint("NotifyDataSetChanged")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FragmentHistoryBinding binding = FragmentHistoryBinding.inflate(inflater, container, false);
        HistoryViewModel historyViewModel = new ViewModelProvider(requireActivity()).get(HistoryViewModel.class);
        binding.btnBack.setOnClickListener(v -> Navigation.findNavController(requireView()).popBackStack());

        binding.rvHistory.setLayoutManager(new LinearLayoutManager(getActivity()));
        List<HistoryModel> list = new ArrayList<>();
        HistoryAdapter adapter = new HistoryAdapter(list, model -> {
            MedicineModel medicineModel = new MedicineModel();
            medicineModel.setWord(model.getWord());
            medicineModel.setMeanings(model.getMeanings());
            medicineModel.set_id(model.get_id());
            Bundle bundle = new Bundle();
            bundle.putSerializable("data_key", medicineModel);
            bundle.putString("bookmark_key", "bookmark");
            Navigation.findNavController(container).navigate(R.id.action_historyFragment_to_medicineDetailsFragment, bundle);

        });
        binding.rvHistory.setAdapter(adapter);


        historyViewModel.getAllHistoryByDesc().observe(getViewLifecycleOwner(), historyModels -> {
            list.clear();
            list.addAll(historyModels);
            adapter.notifyDataSetChanged();

        });

        binding.btnClear.setOnClickListener(v -> new MaterialAlertDialogBuilder(requireActivity()).setTitle("Delete History").setMessage("Are you sure you want to delete all history ?").setPositiveButton("Yes", (dialogInterface, i) -> {
            new Handler().post(() -> historyViewModel.getAllHistory().observe(getViewLifecycleOwner(), historyModels -> {
                for (HistoryModel model : historyModels) {
                    historyViewModel.deleteHistory(model);
                }
            }));
            dialogInterface.dismiss();
        }).setNegativeButton("No", /* listener = */ null).setIcon(android.R.drawable.ic_menu_delete).show());

        return binding.getRoot();
    }
}