package com.ed.pollang.polandlanguageeducation.entries;

import com.google.gson.annotations.SerializedName;

public class AlphabetLessonEntry {

    @SerializedName("header")
    private String header;
    @SerializedName("introduction")
    private String introduction;
    @SerializedName("letters")
    private LetterEntry[] lettersList;

    public AlphabetLessonEntry(String header, String introduction, LetterEntry[] lettersList) {
        this.header = header;
        this.introduction = introduction;
        this.lettersList = lettersList;
    }

    public String getHeader() {
        return header;
    }

    public String getIntroduction() {
        return introduction;
    }

    public LetterEntry[] getLettersList() {
        return lettersList;
    }
}
