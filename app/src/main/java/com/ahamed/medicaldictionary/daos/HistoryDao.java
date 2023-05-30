package com.ahamed.medicaldictionary.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.ahamed.medicaldictionary.model.HistoryModel;

import java.util.List;

@Dao
public interface HistoryDao {

    @Insert
    void insertHistory(HistoryModel historyModel);

    @Update
    void updateHistory(HistoryModel historyModel);

    @Delete
    void deleteHistory(HistoryModel historyModel);

    @Query("select * from tbl_history")
    LiveData<List<HistoryModel>> getAllHistory();

    @Query("select * from tbl_history order by id desc")
    LiveData<List<HistoryModel>> getAllHistoryByDesc();

}
