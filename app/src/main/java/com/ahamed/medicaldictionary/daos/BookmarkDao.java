package com.ahamed.medicaldictionary.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.ahamed.medicaldictionary.model.BookmarkModel;

import java.util.List;

@Dao
public interface BookmarkDao {

    @Insert
    void insertBM(BookmarkModel bookmarkModel);

    @Update
    void updateBM(BookmarkModel bookmarkModel);

    @Delete
    void deleteBM(BookmarkModel bookmarkModel);

    @Query("select * from tbl_bookmark")
    LiveData<List<BookmarkModel>> getAllBM();

    @Query("select * from tbl_bookmark order by id desc")
    LiveData<List<BookmarkModel>> getAllBMByDesc();

}
