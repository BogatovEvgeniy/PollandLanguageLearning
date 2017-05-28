package com.ed.pollang.polandlanguageeducation.entries;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class LetterEntry implements Parcelable {
    @SerializedName("sign")
    String sign;
    @SerializedName("sign_sound_file")
    String sign_sound_file;

    public LetterEntry(String sign, String sign_sound_file) {
        this.sign = sign;
        this.sign_sound_file = sign_sound_file;
    }

    public String getSign() {
        return sign;
    }

    public String getSign_sound_file() {
        return sign_sound_file;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.sign);
        dest.writeString(this.sign_sound_file);
    }

    protected LetterEntry(Parcel in) {
        this.sign = in.readString();
        this.sign_sound_file = in.readString();
    }

    public static final Parcelable.Creator<LetterEntry> CREATOR = new Parcelable.Creator<LetterEntry>() {
        @Override
        public LetterEntry createFromParcel(Parcel source) {
            return new LetterEntry(source);
        }

        @Override
        public LetterEntry[] newArray(int size) {
            return new LetterEntry[size];
        }
    };
}
