package com.ahamed.medicaldictionary.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.ahamed.medicaldictionary.model.MedicineModel;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "word.sqlite";
    public static final String DB_PATH = "/data/data/com.ahamed.medicaldictionary/databases/";
    public static final String tbl_name = "tbl_word_medical";
    private Context context;
    private SQLiteDatabase database;

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void openDatabase() {
        String db_path = context.getDatabasePath(DB_NAME).getPath();
        if (database != null && database.isOpen()) {
            return;
        }
        database = SQLiteDatabase.openDatabase(db_path, null, SQLiteDatabase.OPEN_READONLY);
    }

    public void closeDatabase() {
        if (database != null) {
            database.close();
        }
    }


    public List<MedicineModel> getAllMedicine(int currentPage, int limit) {
        MedicineModel product = null;
        List<MedicineModel> productList = new ArrayList<>();
        int offset = (currentPage - 1) * limit;
        openDatabase();
        try {
            String query = "SELECT * FROM " + tbl_name + " LIMIT " + limit + " OFFSET " + offset;
            Cursor cursor = database.rawQuery(query, null);
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                product = new MedicineModel(cursor.getString(0), cursor.getString(1), cursor.getString(2));
                productList.add(product);
                cursor.moveToNext();
            }
            cursor.close();
            closeDatabase();

        } catch (Exception e) {
            e.printStackTrace();
        }


    /*    Cursor cursor = mDatabase.rawQuery("SELECT * FROM tbl_medical", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            product = new MedicineModel(cursor.getString(0), cursor.getString(1), cursor.getString(2));
            productList.add(product);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();*/

        return productList;
    }

    public MedicineModel getWordByName(String name) {
        MedicineModel product = null;
        openDatabase();
        try {
            String query = "SELECT * FROM " + tbl_name + " WHERE word=" + "'" + name + "'";
            Cursor cursor = database.rawQuery(query, null);
            cursor.moveToFirst();
            product = new MedicineModel(cursor.getString(0), cursor.getString(1), cursor.getString(2));
            cursor.close();
            closeDatabase();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return product;
    }


}
