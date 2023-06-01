package com.ahamed.medicaldictionary.repos;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.ahamed.medicaldictionary.daos.BookmarkDao;
import com.ahamed.medicaldictionary.database.LocalRoomDatabase;
import com.ahamed.medicaldictionary.model.BookmarkModel;

import java.util.List;

public class BookmarkRepository {

    private final BookmarkDao bookmarkDao;

    public BookmarkRepository(Context context) {
        bookmarkDao = LocalRoomDatabase.getDatabase(context).getBookmarkDao();
    }

    public void insertBM(BookmarkModel bookmarkModel) {
        bookmarkDao.insertBM(bookmarkModel);
    }

    public void updateBM(BookmarkModel bookmarkModel) {
        bookmarkDao.updateBM(bookmarkModel);
    }

    public void deleteBM(BookmarkModel bookmarkModel) {
        bookmarkDao.deleteBM(bookmarkModel);
    }

    public LiveData<List<BookmarkModel>> getAllBM() {
        return bookmarkDao.getAllBM();
    }

    public LiveData<List<BookmarkModel>> getAllBMByDesc() {
        return bookmarkDao.getAllBMByDesc();
    }

}
