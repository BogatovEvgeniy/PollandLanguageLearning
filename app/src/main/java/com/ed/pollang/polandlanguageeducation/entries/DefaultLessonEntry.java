package com.ed.pollang.polandlanguageeducation.entries;

import com.google.gson.annotations.SerializedName;

public class DefaultLessonEntry {

    @SerializedName("header")
    String header;

    @SerializedName("introduction")
    String introduction;

    @SerializedName("content")
    DefaultLessonContent content;

    public DefaultLessonEntry(String header, String introduction, DefaultLessonContent content) {
        this.header = header;
        this.introduction = introduction;
        this.content = content;
    }

    public String getHeader() {
        return header;
    }

    public String getIntroduction() {
        return introduction;
    }

    public DefaultLessonContent getContent() {
        return content;
    }
}
