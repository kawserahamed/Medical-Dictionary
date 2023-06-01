package com.ahamed.medicaldictionary.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.ahamed.medicaldictionary.daos.BookmarkDao;
import com.ahamed.medicaldictionary.daos.HistoryDao;
import com.ahamed.medicaldictionary.model.BookmarkModel;
import com.ahamed.medicaldictionary.model.HistoryModel;

@Database(entities = {HistoryModel.class, BookmarkModel.class}, version = 1)
public abstract class LocalRoomDatabase extends RoomDatabase {
    public abstract HistoryDao getHistoryDao();
    public abstract BookmarkDao getBookmarkDao();
    private static LocalRoomDatabase database;

    public static LocalRoomDatabase getDatabase(Context context) {
        if (database == null) {
            database = Room.databaseBuilder(context, LocalRoomDatabase.class, "history_db")
                    .allowMainThreadQueries()
                    .build();
            return database;
        }
        return database;
    }

}
