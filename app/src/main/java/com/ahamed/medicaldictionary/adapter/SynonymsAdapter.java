package com.ahamed.medicaldictionary.adapter;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ahamed.medicaldictionary.R;
import com.ahamed.medicaldictionary.listener.SynonymsWrdListener;

import java.util.List;

public class SynonymsAdapter extends RecyclerView.Adapter<SynonymsAdapter.SynonymsViewHolder> {
    private final List<String> list;
    private SynonymsWrdListener listener;

    public SynonymsAdapter(List<String> list, SynonymsWrdListener listener) {
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public SynonymsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_synonyms_item, parent, false);
        return new SynonymsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SynonymsViewHolder holder, int position) {
        String medicine = list.get(position);
        String aStr = medicine.trim().replace("\"", "");
        String bStr = aStr.replace("}", "");
        String cStr = bStr.trim();
        if (TextUtils.isEmpty(cStr)) {
            holder.layout.setVisibility(View.GONE);
        } else {
            holder.title.setText(cStr);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.synonymsWord(cStr);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class SynonymsViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        LinearLayout layout;

        public SynonymsViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tv_synonymsTitle);
            layout = itemView.findViewById(R.id.layout_synonyms);
        }
    }
}
