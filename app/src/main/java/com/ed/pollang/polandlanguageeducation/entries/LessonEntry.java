package com.ed.pollang.polandlanguageeducation.entries;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class LessonEntry implements Parcelable {
    @SerializedName("lesson_type")
    String lessonType;
    @SerializedName("lesson_id")
    int lessonId;
    @SerializedName("lesson_file_name")
    String lessonFileName;
    String metadata;
    String data;
    String desc;

    public LessonEntry(String lessonType, int lessonId, String lessonFileName, String metadata, String data,
            String desc) {
        this.lessonType = lessonType;
        this.lessonId = lessonId;
        this.lessonFileName = lessonFileName;
        this.metadata = metadata;
        this.data = data;
        this.desc = desc;
    }

    public String getLessonType() {
        return lessonType;
    }

    public int getLessonId() {
        return lessonId;
    }

    public String getLessonFileName() {
        return lessonFileName;
    }

    public String getMetadata() {
        return metadata;
    }

    public String getData() {
        return data;
    }

    public String getDesc() {
        return desc;
    }

    @Override
    public String toString() {
        return "LessonEntry{" + "lessonType='" + lessonType + '\'' + ", lessonId=" + lessonId + ", lessonFileName='" + lessonFileName + '\'' + ", metadata='" + metadata + '\'' + ", data='" + data + '\'' + ", desc='" + desc + '\'' + '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.lessonType);
        dest.writeInt(this.lessonId);
        dest.writeString(this.lessonFileName);
        dest.writeString(this.metadata);
        dest.writeString(this.data);
        dest.writeString(this.desc);
    }

    protected LessonEntry(Parcel in) {
        this.lessonType = in.readString();
        this.lessonId = in.readInt();
        this.lessonFileName = in.readString();
        this.metadata = in.readString();
        this.data = in.readString();
        this.desc = in.readString();
    }

    public static final Parcelable.Creator<LessonEntry> CREATOR = new Parcelable.Creator<LessonEntry>() {
        @Override
        public LessonEntry createFromParcel(Parcel source) {
            return new LessonEntry(source);
        }

        @Override
        public LessonEntry[] newArray(int size) {
            return new LessonEntry[size];
        }
    };
}
