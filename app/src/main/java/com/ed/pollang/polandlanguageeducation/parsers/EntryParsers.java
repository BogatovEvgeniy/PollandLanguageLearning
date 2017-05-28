package com.ed.pollang.polandlanguageeducation.parsers;

import java.util.List;

public interface EntryParsers<Entry, Source> {
    public List<Entry> parse(Source source);
}
