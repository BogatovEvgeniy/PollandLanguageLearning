package com.ed.pollang.polandlanguageeducation.entries;

import com.google.gson.annotations.SerializedName;

public class DefaultLessonEntry {

    @SerializedName("header")
    String header;

    @SerializedName("introduction")
    String introduction;

    @SerializedName("content")
    DefaultLessonContent content;

}
