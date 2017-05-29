package com.ed.pollang.polandlanguageeducation.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ed.pollang.polandlanguageeducation.R;
import com.ed.pollang.polandlanguageeducation.adapters.WordsAdapter;
import com.ed.pollang.polandlanguageeducation.entries.DefaultLessonEntry;
import com.ed.pollang.polandlanguageeducation.entries.LessonEntry;
import com.ed.pollang.polandlanguageeducation.entries.LessonWordsEntry;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


public class DefaultLessonFragment extends BaseFragment {
    public static final String TAG = DefaultLessonFragment.class.getSimpleName();
    private static final String EXTRA_LESSON_ENTRY = "extra_lesson_entry";
    private LessonEntry lessonEntry;
    private WordsAdapter wordsAdapter;
    private DefaultLessonEntry defaultLessonEntry;

    @BindView(R.id.defaultLessonIntroduction)
    TextView lessonIntro;

    @BindView(R.id.defaultLessonWordsList)
    RecyclerView wordsList;

    @BindView(R.id.defaultLessonText)
    TextView lessonText;

    public static Fragment newInstance(LessonEntry lessonEntry) {
        Bundle args = new Bundle();
        args.putParcelable(EXTRA_LESSON_ENTRY, lessonEntry);
        DefaultLessonFragment fragment = new DefaultLessonFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.lessonEntry = getArguments().getParcelable(EXTRA_LESSON_ENTRY);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.default_lesson_fragment_layout, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        setUpView();
    }

    private void setUpView() {
        String lettersJsonObj = getStringJsonFromAssets(lessonEntry.getLessonFileName());
        defaultLessonEntry = new Gson().fromJson(lettersJsonObj, DefaultLessonEntry.class);
        setUpToolbar();
        setUpContentViews();
    }

    private void setUpToolbar() {
        supportActionBarHolder.getSupportActionBarInstance().setTitle(R.string.alphabet_title);
        supportActionBarHolder.getSupportActionBarInstance().setHomeAsUpIndicator(R.drawable.ic_arrow_back);
    }

    private void setUpContentViews() {
        lessonIntro.setText(defaultLessonEntry.getIntroduction());
        lessonText.setText(defaultLessonEntry.getContent().getText());
        wordsList.setLayoutManager(new LinearLayoutManager(getContext()));
        List<LessonWordsEntry> lessonWordsEntries = getWordsList(defaultLessonEntry.getContent().getLessonWords());
        wordsAdapter = new WordsAdapter(getContext(), lessonWordsEntries);
        wordsList.setAdapter(wordsAdapter);

        //Update toolbar title by lesson header
        supportActionBarHolder.getSupportActionBarInstance().setTitle(defaultLessonEntry.getHeader());
    }

    private List<LessonWordsEntry> getWordsList(LessonWordsEntry[] lessonWords) {
        List<LessonWordsEntry> result = new ArrayList<>();
        for (int i = 0; i < lessonWords.length; i++) {
            result.add(lessonWords[i]);
        }
        return result;
    }

    @Override
    public void onStop() {
        super.onStop();
        wordsAdapter.onStop();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                supportActionBarHolder.onBackButtonClicked();
                return true;
            default:
                return false;
        }
    }
}
