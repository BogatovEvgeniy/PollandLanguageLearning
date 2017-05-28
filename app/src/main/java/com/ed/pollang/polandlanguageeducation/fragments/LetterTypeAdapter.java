package com.ed.pollang.polandlanguageeducation.fragments;

import com.ed.pollang.polandlanguageeducation.entries.LetterEntry;
import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

class LetterTypeAdapter implements JsonDeserializer<LetterEntry> {
    @Override
    public LetterEntry deserialize(JsonElement json, Type typeOfT,
            JsonDeserializationContext context) throws JsonParseException {
        return new Gson().fromJson(json, LetterEntry.class);
    }
}
