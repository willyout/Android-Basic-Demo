package com.willyout.BasicDemo.data;

import android.net.Uri;
import android.provider.BaseColumns;

public final class Words {
    public static final String AUTHORITY = "org.willyout.BasicDemo.data";

    public static final class Word implements BaseColumns{
        public static final String _ID = "_id";
        public static final String WORD = "word";
        public static final String DETAIL = "detail";

        public static final Uri DICT_CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/word");
        public static final Uri WORD_CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/word/");

    }

}
