package com.ahamed.medicaldictionary.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ahamed.medicaldictionary.R;
import com.ahamed.medicaldictionary.listener.HistoryCallback;
import com.ahamed.medicaldictionary.model.HistoryModel;
import com.ahamed.medicaldictionary.utils.MyHelper;

import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder> {
    private final List<HistoryModel> list;
    private final HistoryCallback callback;

    public HistoryAdapter(List<HistoryModel> list, HistoryCallback callback) {
        this.list = list;
        this.callback = callback;
    }

    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_history, parent, false);
        return new HistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryViewHolder holder, int position) {
        HistoryModel model = list.get(position);
        holder.bind(model);
        holder.itemView.setOnClickListener(v -> callback.historyClick(model));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class HistoryViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView date;

        public HistoryViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tv_history_name);
            date = itemView.findViewById(R.id.tv_history_date);
        }

        public void bind(HistoryModel model) {
            title.setText(MyHelper.getToUpperCase(model.getWord()));
            date.setText(model.getDate());
        }
    }
}
