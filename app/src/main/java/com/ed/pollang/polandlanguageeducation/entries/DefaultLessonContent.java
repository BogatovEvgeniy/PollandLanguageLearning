package com.ed.pollang.polandlanguageeducation.entries;

import com.google.gson.annotations.SerializedName;

class DefaultLessonContent {

    @SerializedName("words")
    LessonWordsEntry [] lessonWords;

    @SerializedName("text")
    String text;
}
