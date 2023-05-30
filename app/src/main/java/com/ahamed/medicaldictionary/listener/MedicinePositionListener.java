package com.ahamed.medicaldictionary.listener;

import com.ahamed.medicaldictionary.model.MedicineModel;

public interface MedicinePositionListener {
    void medicinePosition(int i);

    void wordClick(MedicineModel model);
}
