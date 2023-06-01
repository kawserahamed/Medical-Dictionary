package com.ahamed.medicaldictionary.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ahamed.medicaldictionary.R;
import com.ahamed.medicaldictionary.listener.BookmarkCallback;
import com.ahamed.medicaldictionary.model.BookmarkModel;

import java.util.List;

public class BookmarkAdapter extends RecyclerView.Adapter<BookmarkAdapter.BookmarkViewHolder> {

    private final List<BookmarkModel> list;
    private final BookmarkCallback callback;

    public BookmarkAdapter(List<BookmarkModel> list, BookmarkCallback callback) {
        this.list = list;
        this.callback = callback;
    }

    @NonNull
    @Override
    public BookmarkViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_bookmark, parent, false);
        return new BookmarkViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookmarkViewHolder holder, int position) {
        BookmarkModel bookmarkModel = list.get(position);
        holder.bind(bookmarkModel);
        holder.itemView.setOnClickListener(v -> callback.bookmarkClick(bookmarkModel));
        holder.delete.setOnClickListener(v -> callback.bookmarkDelete(bookmarkModel));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class BookmarkViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView note;
        TextView date;
        ImageView delete;

        public BookmarkViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tv_bk_title);
            note = itemView.findViewById(R.id.tv_bk_note);
            date = itemView.findViewById(R.id.tv_bk_date);
            delete = itemView.findViewById(R.id.btn_bk_delete);
        }

        void bind(BookmarkModel model) {
            title.setText(model.getWord());
            date.setText(model.getDate());
            if (model.getNote().equals("")) {
                note.setVisibility(View.GONE);
            } else {
                note.setText(model.getNote());
                note.setVisibility(View.VISIBLE);
            }
        }
    }
}
