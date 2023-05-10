package com.ahamed.medicaldictionary.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuProvider;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ahamed.medicaldictionary.R;
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
        NavHostFragment navHostFragment = (NavHostFragment) requireActivity().getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView);
        assert navHostFragment != null;
        NavController navController = navHostFragment.getNavController();
        ((AppCompatActivity) requireActivity()).setSupportActionBar(binding.toolbar);

        requireActivity().addMenuProvider(new MenuProvider() {
            @Override
            public void onCreateMenu(@NonNull Menu menu, @NonNull MenuInflater menuInflater) {
                menuInflater.inflate(R.menu.top_menu, menu);
            }

            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onMenuItemSelected(@NonNull MenuItem menuItem) {

                NavigationUI.onNavDestinationSelected(menuItem, navController);

                switch (menuItem.getItemId()) {
                    case R.id.menu_history:
                        Toast.makeText(getActivity(), "history", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.menu_bookmark:
                        Toast.makeText(getActivity(), "bookmark", Toast.LENGTH_SHORT).show();
                        break;
                }

                return false;
            }
        }, getViewLifecycleOwner(), Lifecycle.State.RESUMED);



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

        viewModel.getAllMedicineWithPage(1, limit).observe(getViewLifecycleOwner(), productModels -> {
            mProductList.clear();
            mProductList.addAll(productModels);
            adapter.notifyDataSetChanged();
            binding.rvMedicine.scrollToPosition(position);

        });

        binding.searchWord.setOnItemClickListener((parent, view, position, id) -> {
            String word = binding.searchWord.getText().toString();
            MedicineModel medicineModel = viewModel.getWordByName(word);
            if (medicineModel != null) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("data_key", medicineModel);
                Navigation.findNavController(requireView()).navigate(R.id.action_homeFragment_to_medicineDetailsFragment, bundle);
            } else {
                Toast.makeText(getActivity(), "Word not found", Toast.LENGTH_SHORT).show();
            }

        });

        binding.searchWord.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                viewModel.getAutoWordList(s.toString()).observe(getViewLifecycleOwner(), medicineModels -> {
                    List<String> searchList = new ArrayList<>();
                    for (MedicineModel model : medicineModels) {
                        searchList.add(model.getWord());
                    }
                    ArrayAdapter<String> autoTextAdapter = new ArrayAdapter<>(getActivity(),
                            android.R.layout.simple_spinner_dropdown_item, searchList);
                    binding.searchWord.setAdapter(autoTextAdapter);

                });

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        return binding.getRoot();
    }

    @SuppressLint("NotifyDataSetChanged")
    private void dataFetch(int count) {
        binding.progressBar.setVisibility(View.VISIBLE);
        new Handler().postDelayed(() -> {
            Log.d("TAG", "dataFetch: " + count);
            viewModel.getAllMedicineWithPage(1, count).observe(lifecycleOwner, productModels -> {
                mProductList.clear();
                mProductList.addAll(productModels);
                adapter.notifyDataSetChanged();
                binding.progressBar.setVisibility(View.GONE);
            });
        }, 1000);
    }


}