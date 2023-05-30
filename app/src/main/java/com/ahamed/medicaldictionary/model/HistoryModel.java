package com.ahamed.medicaldictionary.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "tbl_history")
public class HistoryModel {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "history_word")
    private String word;

    @ColumnInfo(name = "history_meanings")
    private String meanings;

    @ColumnInfo(name = "history_id")
    private String _id;

    @ColumnInfo(name = "history_date")
    private String date;

    public HistoryModel(String word, String meanings, String _id, String date) {
        this.word = word;
        this.meanings = meanings;
        this._id = _id;
        this.date = date;
    }

    @Ignore
    public HistoryModel(int id, String word, String meanings, String _id, String date) {
        this.id = id;
        this.word = word;
        this.meanings = meanings;
        this._id = _id;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getMeanings() {
        return meanings;
    }

    public void setMeanings(String meanings) {
        this.meanings = meanings;
    }


    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


    @Override
    public String toString() {
        return "HistoryModel{" +
                "id=" + id +
                ", word='" + word + '\'' +
                ", meanings='" + meanings + '\'' +
                ", _id='" + _id + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
