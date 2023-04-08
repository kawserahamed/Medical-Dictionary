package com.ahamed.medicaldictionary.viewModel;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.ahamed.medicaldictionary.R;
import com.ahamed.medicaldictionary.database.DatabaseHelper;
import com.ahamed.medicaldictionary.model.MedicineModel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

public class DatabaseViewModel extends AndroidViewModel {
    Application application;
    private static final String TAG = "TAG";
    private final DatabaseHelper databaseHelper;

    public DatabaseViewModel(@NonNull Application application) {
        super(application);
        this.application = application;
        databaseHelper = new DatabaseHelper(application);
        File database = application.getDatabasePath(DatabaseHelper.DB_NAME);
        if (!database.exists()) {
            databaseHelper.getReadableDatabase();
            if (copyDatabase(application)) {
                Log.d(TAG, "DatabaseViewModel: " + "successfully ");
            } else {
                Log.d(TAG, "DatabaseViewModel: some problem ");
            }
        }
    }

    public MutableLiveData<List<MedicineModel>> getAllMedicineWithPage(int currentPage, int limit) {
        MutableLiveData<List<MedicineModel>> listMutableLiveData = new MutableLiveData<>();
        List<MedicineModel> tempList = databaseHelper.getAllMedicine(currentPage, limit);
        listMutableLiveData.postValue(tempList);
        databaseHelper.close();
        return listMutableLiveData;
    }

    public MutableLiveData<List<MedicineModel>> getAutoWordList(String word) {
        MutableLiveData<List<MedicineModel>> listMutableLiveData = new MutableLiveData<>();
        List<MedicineModel> tempList = databaseHelper.getAutoWordList(word);
        listMutableLiveData.postValue(tempList);
        databaseHelper.close();
        return listMutableLiveData;
    }

    public MedicineModel getWordByName(String name) {
        MedicineModel word;
        word = databaseHelper.getWordByName(name);
        return word;
    }

    public MedicineModel getNextWordByID(String id) {
        MedicineModel word;
        word = databaseHelper.getNextWordByID(id);
        return word;
    }

    public MedicineModel getPreviousWordByID(String id) {
        MedicineModel word;
        word = databaseHelper.getPreviousWordByID(id);
        return word;
    }

    private boolean copyDatabase(Context context) {
        try {
            InputStream inputStream = context.getResources().openRawResource(R.raw.word);
            String outFileName = DatabaseHelper.DB_PATH + DatabaseHelper.DB_NAME;
            OutputStream outputStream = new FileOutputStream(outFileName);
            byte[] buff = new byte[1024];
            int length = 0;
            while ((length = inputStream.read(buff)) > 0) {
                outputStream.write(buff, 0, length);
            }
            outputStream.flush();
            outputStream.close();
            return true;

        } catch (Exception exception) {
            exception.printStackTrace();
            return false;
        }
    }


}
