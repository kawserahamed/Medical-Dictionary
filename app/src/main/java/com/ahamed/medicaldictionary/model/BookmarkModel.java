package com.ahamed.medicaldictionary.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "tbl_bookmark")
public class BookmarkModel {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "bookmark_word")
    private String word;

    @ColumnInfo(name = "bookmark_meanings")
    private String meanings;

    @ColumnInfo(name = "bookmark_id")
    private String _id;

    @ColumnInfo(name = "bookmark_note")
    private String note;

    @ColumnInfo(name = "bookmark_date")
    private String date;

    public BookmarkModel(String word, String meanings, String _id, String note, String date) {
        this.word = word;
        this.meanings = meanings;
        this._id = _id;
        this.note = note;
        this.date = date;
    }

    @Ignore
    public BookmarkModel(int id, String word, String meanings, String _id, String note, String date) {
        this.id = id;
        this.word = word;
        this.meanings = meanings;
        this._id = _id;
        this.note = note;
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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "BookmarkModel{" +
                "id=" + id +
                ", word='" + word + '\'' +
                ", meanings='" + meanings + '\'' +
                ", _id='" + _id + '\'' +
                ", note='" + note + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
