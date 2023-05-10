package com.ahamed.medicaldictionary.viewModel;


import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ahamed.medicaldictionary.model.MedicineModel;


public class MedicalViewModel extends ViewModel {
    private final MutableLiveData<MedicineModel> liveData = new MutableLiveData<>();


    public MutableLiveData<MedicineModel> getLiveData() {
        return liveData;
    }

    public void setLiveData(MedicineModel model) {
        this.liveData.postValue(model);
    }
}
