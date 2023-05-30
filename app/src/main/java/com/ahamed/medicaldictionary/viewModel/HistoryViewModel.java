package com.ahamed.medicaldictionary.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.ahamed.medicaldictionary.model.HistoryModel;
import com.ahamed.medicaldictionary.repos.HistoryRepository;

import java.util.List;

public class HistoryViewModel extends AndroidViewModel {
    private final HistoryRepository historyRepository;

    public HistoryViewModel(@NonNull Application application) {
        super(application);
        historyRepository = new HistoryRepository(application);
    }

    public void addHistory(HistoryModel historyModel) {
        historyRepository.addHistory(historyModel);
    }

    public void updateHistory(HistoryModel historyModel) {
        historyRepository.updateHistory(historyModel);
    }

    public void deleteHistory(HistoryModel historyModel) {
        historyRepository.deleteHistory(historyModel);
    }

    public LiveData<List<HistoryModel>> getAllHistory() {
        return historyRepository.getAllHistory();
    }

    public LiveData<List<HistoryModel>> getAllHistoryByDesc() {
        return historyRepository.getAllHistoryByDesc();
    }

}
