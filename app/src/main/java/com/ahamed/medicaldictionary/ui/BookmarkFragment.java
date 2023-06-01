package com.ahamed.medicaldictionary.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.ahamed.medicaldictionary.R;
import com.ahamed.medicaldictionary.adapter.BookmarkAdapter;
import com.ahamed.medicaldictionary.databinding.FragmentBookmarkBinding;
import com.ahamed.medicaldictionary.listener.BookmarkCallback;
import com.ahamed.medicaldictionary.model.BookmarkModel;
import com.ahamed.medicaldictionary.model.MedicineModel;
import com.ahamed.medicaldictionary.utils.MyHelper;
import com.ahamed.medicaldictionary.viewModel.BookmarkViewModel;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.ArrayList;
import java.util.List;

public class BookmarkFragment extends Fragment {

    public BookmarkFragment() {
        // Required empty public constructor
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FragmentBookmarkBinding binding = FragmentBookmarkBinding.inflate(inflater, container, false);
        BookmarkViewModel bookmarkViewModel = new ViewModelProvider(requireActivity()).get(BookmarkViewModel.class);
        binding.rvBookmark.setLayoutManager(new LinearLayoutManager(getActivity()));
        List<BookmarkModel> list = new ArrayList<>();
        BookmarkAdapter adapter = new BookmarkAdapter(list, new BookmarkCallback() {
            @Override
            public void bookmarkClick(BookmarkModel model) {
                MedicineModel medicineModel = new MedicineModel();
                medicineModel.setWord(model.getWord());
                medicineModel.setMeanings(model.getMeanings());
                medicineModel.set_id(model.get_id());
                Bundle bundle = new Bundle();
                bundle.putSerializable("data_key", medicineModel);
                bundle.putString("note_key", model.getNote());
                Navigation.findNavController(container).navigate(R.id.action_bookmarkFragment_to_medicineDetailsFragment, bundle);
            }

            @Override
            public void bookmarkDelete(BookmarkModel model) {
                new MaterialAlertDialogBuilder(requireActivity())
                        .setTitle("Delete History")
                        .setMessage("Are you sure you want to delete " + MyHelper.getToUpperCase(model.getWord()) + " ?")
                        .setPositiveButton("Yes", (dialogInterface, i) -> {
                            bookmarkViewModel.deleteBM(model);
                            dialogInterface.dismiss();
                        }).setNegativeButton("No", /* listener = */ null)
                        .setIcon(android.R.drawable.ic_menu_delete)
                        .show();

            }
        });

        binding.rvBookmark.setAdapter(adapter);
        bookmarkViewModel.getAllBMByDesc().observe(getViewLifecycleOwner(), bookmarkModels -> {
            list.clear();
            list.addAll(bookmarkModels);
            adapter.notifyDataSetChanged();
        });


        binding.btnBack.setOnClickListener(v -> Navigation.findNavController(requireView()).popBackStack());
        return binding.getRoot();
    }
}