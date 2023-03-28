package com.ahamed.medicaldictionary.model;

import java.io.Serializable;

public class MedicineModel implements Serializable {
    private String word;
    private String meanings;
    private String _id;

    public MedicineModel(String word, String meanings, String _id) {
        this.word = word;
        this.meanings = meanings;
        this._id = _id;
    }

    public MedicineModel() {
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
}
