package com.ahamed.medicaldictionary.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.ahamed.medicaldictionary.model.BookmarkModel;
import com.ahamed.medicaldictionary.repos.BookmarkRepository;

import java.util.List;

public class BookmarkViewModel extends AndroidViewModel {
    private final BookmarkRepository bookmarkRepository;

    public BookmarkViewModel(@NonNull Application application) {
        super(application);
        bookmarkRepository = new BookmarkRepository(application);
    }

    public void addBM(BookmarkModel bookmarkModel) {
        bookmarkRepository.insertBM(bookmarkModel);
    }


    public void updateBM(BookmarkModel bookmarkModel) {
        bookmarkRepository.updateBM(bookmarkModel);
    }

    public void deleteBM(BookmarkModel bookmarkModel) {
        bookmarkRepository.deleteBM(bookmarkModel);
    }

   public LiveData<List<BookmarkModel>> getAllBM() {
        return bookmarkRepository.getAllBM();

    }

    public LiveData<List<BookmarkModel>> getAllBMByDesc() {
        return bookmarkRepository.getAllBMByDesc();
    }

}
