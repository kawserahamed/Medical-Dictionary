package com.ahamed.medicaldictionary.listener;

import com.ahamed.medicaldictionary.model.BookmarkModel;

public interface BookmarkCallback {
    void bookmarkClick(BookmarkModel model);

    void bookmarkDelete(BookmarkModel model);
}
