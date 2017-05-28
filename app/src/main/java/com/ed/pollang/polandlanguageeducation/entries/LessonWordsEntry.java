package com.ed.pollang.polandlanguageeducation.entries;

import com.google.gson.annotations.SerializedName;

class LessonWordsEntry {
    @SerializedName("ru")
    String header;

    @SerializedName("pl")
    String introduction;

    @SerializedName("word_sound_file")
    DefaultLessonContent content;

}
