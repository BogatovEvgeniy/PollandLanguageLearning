package com.ed.pollang.polandlanguageeducation.entries;

import com.google.gson.annotations.SerializedName;

public class DefaultLessonContent {

    @SerializedName("words")
    LessonWordsEntry [] lessonWords;

    @SerializedName("text")
    String text;

    public DefaultLessonContent(LessonWordsEntry[] lessonWords, String text) {
        this.lessonWords = lessonWords;
        this.text = text;
    }

    public LessonWordsEntry[] getLessonWords() {
        return lessonWords;
    }

    public String getText() {
        return text;
    }
}
