package com.ahamed.medicaldictionary.fragment;

import static android.content.Context.CLIPBOARD_SERVICE;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.graphics.Color;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
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
import com.ahamed.medicaldictionary.model.MedicineModel;
import com.ahamed.medicaldictionary.utils.MyHelper;
import com.ahamed.medicaldictionary.viewModel.DatabaseViewModel;
import com.ahamed.medicaldictionary.viewModel.MedicalViewModel;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;


public class MedicineDetailsFragment extends Fragment {


    private FragmentMedicineDetailsBinding binding;
    private DatabaseViewModel viewModel;
    private MedicalViewModel medicalViewModel;
    private static final String TAG = "TAG";
    private ClipData myClip;
    private SynonymsAdapter adapter;

    private TextToSpeech textToSpeech;
    private ClipboardManager myClipboard;

    public MedicineDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this
        binding = FragmentMedicineDetailsBinding.inflate(inflater);
        textToSpeech = new TextToSpeech(getActivity(), status -> {
            if (status != TextToSpeech.ERROR) {
                textToSpeech.setLanguage(Locale.ENGLISH);
            }
        });
        myClipboard = (ClipboardManager) requireActivity().getSystemService(CLIPBOARD_SERVICE);

        viewModel = new ViewModelProvider(requireActivity()).get(DatabaseViewModel.class);
        medicalViewModel = new ViewModelProvider(requireActivity()).get(MedicalViewModel.class);
        Bundle bundle = getArguments();
        assert bundle != null;
        MedicineModel model = (MedicineModel) bundle.getSerializable("data_key");
        medicalViewModel.setLiveData(model);

        medicalViewModel.getLiveData().observe(getViewLifecycleOwner(), rawWord -> {
            loadUI(rawWord);
            Log.d(TAG, "onChanged: " + rawWord);
        });


        binding.btnBack.setOnClickListener(v -> Navigation.findNavController(requireView()).popBackStack());

        return binding.getRoot();
    }

    @SuppressLint("NotifyDataSetChanged")
    private void loadUI(MedicineModel model) {
        String title = model.getWord();
        String fTitle = title.substring(0, 1).toUpperCase() + title.substring(1);
        binding.tvTitle.setText(MyHelper.getToUpperCase(model.getWord()));

        binding.btnCopy.setOnClickListener(v -> {
            myClip = ClipData.newPlainText("text", fTitle);
            myClipboard.setPrimaryClip(myClip);
            Toast.makeText(getActivity(), "Copied", Toast.LENGTH_SHORT).show();
        });
        binding.btnSound.setOnClickListener(v -> textToSpeech.speak(fTitle, TextToSpeech.QUEUE_ADD, null));


        List<String> word_list = Arrays.asList(model.getMeanings().split(":"));
        String rawDefinition = word_list.get(2);


        String definition = MyHelper.cleanText(rawDefinition);
        binding.tvDefinition.setText(MyHelper.cleanText(definition));


        if (MyHelper.cleanText(definition).isEmpty()) {
            String sorry = "SORRY, Definition not found";
            binding.tvDefinition.setText(sorry);
            binding.tvDefinition.setTextColor(Color.parseColor("#FFE53935"));
        } else {
            binding.tvDefinition.setTextColor(Color.parseColor("#000000"));
            binding.tvDefinition.setText(MyHelper.getToUpperCase(definition));
        }

        String synonyms = word_list.get(3);
        List<String> synonymsList = Arrays.asList(synonyms.split(","));


        if (synonymsList.size() > 0) {
            Log.d(TAG, "loadUI: " + synonymsList);
            adapter = new SynonymsAdapter(synonymsList, word -> {
                MedicineModel medicineModel = viewModel.getWordByName(word);
                if (medicineModel != null) {
                    medicalViewModel.setLiveData(medicineModel);
                    adapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(getActivity(), "Word not found", Toast.LENGTH_SHORT).show();
                }
            });
            binding.rvSynonyms.setLayoutManager(new LinearLayoutManager(getActivity()));
            binding.rvSynonyms.setAdapter(adapter);
        }


        int currentId = Integer.parseInt(model.get_id());
        int nextID = currentId + 1;
        int previousID = currentId - 1;
        if (currentId <= 1) {
            binding.btnPrevious.setVisibility(View.GONE);
        } else {
            binding.btnPrevious.setVisibility(View.VISIBLE);

        }
        if (currentId >= 99054) {
            binding.btnNext.setVisibility(View.GONE);
        } else {
            binding.btnNext.setVisibility(View.VISIBLE);

        }
        binding.btnNext.setOnClickListener(v -> {
            MedicineModel medicineModel = viewModel.getNextWordByID("" + nextID);
            if (medicineModel != null) {
                medicalViewModel.setLiveData(medicineModel);
                adapter.notifyDataSetChanged();
            } else {
                Toast.makeText(getActivity(), "Word not found", Toast.LENGTH_SHORT).show();
            }
        });

        binding.btnPrevious.setOnClickListener(v -> {
            MedicineModel medicineModel = viewModel.getPreviousWordByID("" + previousID);
            if (medicineModel != null) {
                medicalViewModel.setLiveData(medicineModel);
                adapter.notifyDataSetChanged();
            } else {
                Toast.makeText(getActivity(), "Word not found", Toast.LENGTH_SHORT).show();
            }
        });

    }

}