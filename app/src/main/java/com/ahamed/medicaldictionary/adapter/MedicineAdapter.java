package com.ahamed.medicaldictionary.adapter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.ahamed.medicaldictionary.R;
import com.ahamed.medicaldictionary.listener.MedicinePositionListener;
import com.ahamed.medicaldictionary.model.MedicineModel;
import com.ahamed.medicaldictionary.utils.MyHelper;

import java.util.List;

public class MedicineAdapter extends RecyclerView.Adapter<MedicineAdapter.MedicineViewHolder> {
    private List<MedicineModel> list;
    private MedicinePositionListener listener;

    public MedicineAdapter(List<MedicineModel> list, MedicinePositionListener listener) {
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MedicineViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_medicine, parent, false);
        return new MedicineViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MedicineViewHolder holder, int position) {
        MedicineModel model = list.get(position);
        holder.textView.setText(MyHelper.getToUpperCase(model.getWord()));
        holder.itemView.setOnClickListener(v -> {
            listener.medicinePosition(position);
            listener.wordClick(model);
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MedicineViewHolder extends RecyclerView.ViewHolder {

        private TextView textView;

        public MedicineViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tv_title);

        }
    }
}
