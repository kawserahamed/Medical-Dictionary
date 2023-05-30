package com.ahamed.medicaldictionary.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.ahamed.medicaldictionary.daos.HistoryDao;
import com.ahamed.medicaldictionary.model.HistoryModel;

@Database(entities = {HistoryModel.class}, version = 1)
public abstract class HistoryDatabase extends RoomDatabase {
    public abstract HistoryDao getHistoryDao();
    private static HistoryDatabase database;

    public static HistoryDatabase getDatabase(Context context) {
        if (database == null) {
            database = Room.databaseBuilder(context, HistoryDatabase.class, "history_db")
                    .allowMainThreadQueries()
                    .build();
            return database;
        }
        return database;
    }

}
