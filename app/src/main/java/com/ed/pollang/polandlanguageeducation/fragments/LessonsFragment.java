package com.ed.pollang.polandlanguageeducation.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ed.pollang.polandlanguageeducation.LessonPresenter;
import com.ed.pollang.polandlanguageeducation.R;
import com.ed.pollang.polandlanguageeducation.adapters.LessonsAdapter;
import com.ed.pollang.polandlanguageeducation.entries.LessonEntry;
import com.ed.pollang.polandlanguageeducation.parsers.LessonParser;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class LessonsFragment extends BaseFragment {

    public static final String TAG = LessonsFragment.class.getSimpleName();
    public static final String LESSONS_JSON = "lessons.json";

    @BindView(R.id.recycler_list)
    RecyclerView recyclerView;
    LessonParser lessonParser = new LessonParser();
    private LessonPresenter lessonPresenter;

    public static Fragment newInstance() {
        return new LessonsFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof LessonPresenter){
            this.lessonPresenter = (LessonPresenter)context;
        } else {
            throw new RuntimeException("The fragment parent should implement LessonPresenter interface");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.lessons_fragment_layout, container, false);
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
        supportActionBarHolder.getSupportActionBarInstance().setTitle(R.string.menu);
    }

    private void setUpContentViews() {
        String json = getStringJsonFromAssets(LESSONS_JSON);
        List<LessonEntry> lessons = new Gson().fromJson(json, new TypeToken<ArrayList<LessonEntry>>() {}.getType());
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerView.setAdapter(new LessonsAdapter(getContext(), lessonPresenter,  lessons));
    }
}
