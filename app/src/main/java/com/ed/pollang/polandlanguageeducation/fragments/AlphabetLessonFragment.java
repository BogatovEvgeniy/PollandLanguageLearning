package com.ed.pollang.polandlanguageeducation.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.ed.pollang.polandlanguageeducation.R;
import com.ed.pollang.polandlanguageeducation.adapters.LettersAdapter;
import com.ed.pollang.polandlanguageeducation.entries.AlphabetLessonEntry;
import com.ed.pollang.polandlanguageeducation.entries.LessonEntry;
import com.ed.pollang.polandlanguageeducation.entries.LetterEntry;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class AlphabetLessonFragment extends BaseFragment {
    public static final String TAG = AlphabetLessonFragment.class.getSimpleName();
    public static final int SPAN_COUNT = 5;
    @BindView(R.id.letters_list)
    RecyclerView lettersList;

    private static final String EXTRA_LESSON_ENTRY = "extra_lesson_entry";
    private LessonEntry lessonEntry;
    private LettersAdapter lettersAdapter;

    public static AlphabetLessonFragment newInstance(LessonEntry lessonEntry) {
        Bundle args = new Bundle();
        args.putParcelable(EXTRA_LESSON_ENTRY, lessonEntry);
        AlphabetLessonFragment fragment = new AlphabetLessonFragment();
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
        return inflater.inflate(R.layout.alphabet_fragment_layout, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        setUpView();
    }

    private void setUpView() {
        setUpToolbar();
        setUpContentViews();
    }

    private void setUpToolbar() {
        supportActionBarHolder.getSupportActionBarInstance().setTitle(R.string.alphabet_title);
        supportActionBarHolder.getSupportActionBarInstance().setHomeAsUpIndicator(R.drawable.ic_arrow_back);
    }

    private void setUpContentViews() {
        String lettersJsonObj = getStringJsonFromAssets(lessonEntry.getLessonFileName());

        AlphabetLessonEntry alphabetLessonEntry = new Gson().fromJson(lettersJsonObj, AlphabetLessonEntry.class);
        GridLayoutManager layout = new GridLayoutManager(getContext(), SPAN_COUNT);
        lettersList.setLayoutManager(layout);
        List<LetterEntry> letterEntryList = getLettersList(alphabetLessonEntry.getLettersList());
        lettersAdapter = new LettersAdapter(getContext(), letterEntryList);
        lettersList.setAdapter(lettersAdapter);
    }

    private List<LetterEntry> getLettersList(LetterEntry[] lettersList) {
        List<LetterEntry> result = new ArrayList<>();
        for (int i = 0; i < lettersList.length; i++) {
            result.add(lettersList[i]);
        }
        return result;
    }

    @Override
    public void onStop() {
        super.onStop();
        lettersAdapter.onStop();
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
