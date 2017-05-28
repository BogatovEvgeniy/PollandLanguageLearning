package com.ed.pollang.polandlanguageeducation.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ed.pollang.polandlanguageeducation.R;
import com.ed.pollang.polandlanguageeducation.entries.AlphabetLessonEntry;
import com.ed.pollang.polandlanguageeducation.entries.LessonEntry;
import com.google.gson.Gson;


public class DefaultLessonFragment extends BaseFragment {
    public static final String TAG = DefaultLessonFragment.class.getSimpleName();
    private static final String EXTRA_LESSON_ENTRY = "extra_lesson_entry";
    private LessonEntry lessonEntry;

    public static Fragment newInstance(LessonEntry lessonEntry) {
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
        setUpToolbar();
        setUpContentViews();
    }

    private void setUpToolbar() {
        supportActionBarHolder.getSupportActionBarInstance().setTitle(R.string.alphabet_title);
    }

    private void setUpContentViews() {
        String lettersJsonObj = getStringJsonFromAssets(lessonEntry.getLessonFileName());
        AlphabetLessonEntry alphabetLessonEntry = new Gson().fromJson(lettersJsonObj, AlphabetLessonEntry.class);
    }
}
