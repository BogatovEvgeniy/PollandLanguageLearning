package com.ed.pollang.polandlanguageeducation;

enum LessonType {
    ALPHABET, DEFAULT;

    public static LessonType getType(String lessonType) {
        LessonType result = DEFAULT;
        switch (lessonType) {
            case "alphabet":
                result = ALPHABET;
                break;
            case "default":
                result = DEFAULT;
                break;
        }
        return result;
    }
}
