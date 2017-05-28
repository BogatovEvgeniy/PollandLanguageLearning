package com.ed.pollang.polandlanguageeducation.entries;

import com.google.gson.annotations.SerializedName;

public class LessonWordsEntry {
    @SerializedName("ru")
    String ru;

    @SerializedName("pl")
    String pl;

    @SerializedName("word_sound_file")
    String sound;

    public LessonWordsEntry(String ru, String pl, String sound) {
        this.ru = ru;
        this.pl = pl;
        this.sound = sound;
    }

    public String getRu() {
        return ru;
    }

    public String getPl() {
        return pl;
    }

    public String getSound() {
        return sound;
    }
}
