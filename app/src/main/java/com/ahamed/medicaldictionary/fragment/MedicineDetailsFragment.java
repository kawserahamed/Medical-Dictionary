package com.ahamed.medicaldictionary.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.ahamed.medicaldictionary.adapter.SynonymsAdapter;
import com.ahamed.medicaldictionary.databinding.FragmentMedicineDetailsBinding;
import com.ahamed.medicaldictionary.listener.SynonymsWrdListener;
import com.ahamed.medicaldictionary.model.MedicineModel;
import com.ahamed.medicaldictionary.utils.MyHelper;
import com.ahamed.medicaldictionary.viewModel.DatabaseViewModel;

import java.util.Arrays;
import java.util.List;


public class MedicineDetailsFragment extends Fragment implements SynonymsWrdListener {


    private static MedicineModel model;
    private FragmentMedicineDetailsBinding binding;
    private DatabaseViewModel viewModel;
    private static final String TAG = "TAG";

    public MedicineDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this
        binding = FragmentMedicineDetailsBinding.inflate(inflater);
        Bundle bundle = getArguments();
        assert bundle != null;
        model = (MedicineModel) bundle.getSerializable("data_key");
        loadUI();
        viewModel = new ViewModelProvider(requireActivity()).get(DatabaseViewModel.class);
        binding.btnBack.setOnClickListener(v -> Navigation.findNavController(requireView()).popBackStack());

        return binding.getRoot();
    }

    private void loadUI() {
        String title = model.getWord();
        String fTitle = title.substring(0, 1).toUpperCase() + title.substring(1);
        binding.toolbarName.setText(fTitle);
        binding.tvTitle.setText(fTitle);

        List<String> word_list = Arrays.asList(model.getMeanings().split(":"));

        String definition = word_list.get(2);


       /* String nDefinition = definition.trim().replace("[", "");
        String mDefinition = nDefinition.replace("\"s\"", "");
        String oDefinition = mDefinition.replace("\"", "");
        String fDefinition = oDefinition.replace("],", "");*/

        //  String str = fDefinition.substring(0, 1).toUpperCase() + fDefinition.substring(1);
        binding.tvDefinition.setText(MyHelper.cleanText(definition));

    /*    if (TextUtils.isEmpty(fDefinition)) {
            binding.tvDefinitionTitle.setVisibility(View.GONE);
            binding.tvDefinition.setVisibility(View.GONE);
        } else {
            String str = fDefinition.substring(0, 1).toUpperCase() + fDefinition.substring(1);
            binding.tvDefinition.setText(str);
        }*/

        String synonyms = word_list.get(3);
        List<String> synonymsList = Arrays.asList(synonyms.split(","));

        if (synonymsList.size() == 1) {
            String aStr = synonymsList.get(0);
            String bStr = aStr.replace("\"", "");
            String cStr = bStr.replace("}", "");
            String dStr = cStr.trim();
            if (TextUtils.isEmpty(dStr)) {
                binding.tvSynonyms.setVisibility(View.GONE);
                binding.rvSynonyms.setVisibility(View.GONE);
            }

        } else {
            SynonymsAdapter adapter = new SynonymsAdapter(synonymsList, MedicineDetailsFragment.this);
            binding.rvSynonyms.setLayoutManager(new LinearLayoutManager(getActivity()));
            binding.rvSynonyms.setAdapter(adapter);
        }
    }

    @Override
    public void synonymsWord(String word) {
        MedicineModel medicineModel = viewModel.getWordByName(word);
        if (medicineModel != null) {
            model = medicineModel;
            loadUI();
        } else {
            Toast.makeText(getActivity(), "Word not found", Toast.LENGTH_SHORT).show();
        }
    }
}