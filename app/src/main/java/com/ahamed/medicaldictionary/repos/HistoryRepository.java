package com.ahamed.medicaldictionary.repos;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.ahamed.medicaldictionary.daos.HistoryDao;
import com.ahamed.medicaldictionary.database.LocalRoomDatabase;
import com.ahamed.medicaldictionary.model.HistoryModel;

import java.util.List;

public class HistoryRepository {
    private final HistoryDao historyDao;
    public HistoryRepository(Context context) {

        historyDao = LocalRoomDatabase.getDatabase(context).getHistoryDao();
    }
    public void addHistory(HistoryModel historyModel) {
        historyDao.insertHistory(historyModel);
    }

    public void updateHistory(HistoryModel historyModel) {
        historyDao.updateHistory(historyModel);
    }

    public void deleteHistory(HistoryModel historyModel) {
        historyDao.deleteHistory(historyModel);
    }

    public LiveData<List<HistoryModel>> getAllHistory() {
        return historyDao.getAllHistory();
    }

    public LiveData<List<HistoryModel>> getAllHistoryByDesc() {
        return historyDao.getAllHistoryByDesc();
    }

}
