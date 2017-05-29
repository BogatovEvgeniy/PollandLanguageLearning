package com.ed.pollang.polandlanguageeducation;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;

import com.ed.pollang.polandlanguageeducation.entries.LessonEntry;
import com.ed.pollang.polandlanguageeducation.fragments.AlphabetLessonFragment;
import com.ed.pollang.polandlanguageeducation.fragments.DefaultLessonFragment;
import com.ed.pollang.polandlanguageeducation.fragments.LessonsFragment;
import com.ed.pollang.polandlanguageeducation.fragments.SupportActionBarHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements SupportActionBarHolder, LessonPresenter {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.container)
    FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lessons_list);
        ButterKnife.bind(this);
        setupView();
        showLessonsFragment();
    }

    private void setupView() {
        setSupportActionBar(toolbar);
        ActionBar supportActionBar = getSupportActionBar();
        supportActionBar.setDisplayOptions(ActionBar.DISPLAY_HOME_AS_UP | ActionBar.DISPLAY_SHOW_TITLE);
        supportActionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
    }

    @Override
    public ActionBar getSupportActionBarInstance() {
        return getSupportActionBar();
    }

    @Override
    public void onBackButtonClicked() {
        onBackPressed();
    }

    @Override
    public void showLesson(LessonEntry lessonEntry) {
        switch (LessonType.getType(lessonEntry.getLessonType())) {
            case ALPHABET:
                showAlphabetFragment(lessonEntry);
                break;
            case DEFAULT:
                showDefaultLessonFragment(lessonEntry);
                break;
        }
    }

    private void showLessonsFragment() {
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        Fragment fragment = supportFragmentManager.findFragmentByTag(LessonsFragment.TAG);

        if (fragment == null) {
            fragment = LessonsFragment.newInstance();
        }

        supportFragmentManager.beginTransaction().replace(R.id.container, fragment).commit();
    }

    private void showAlphabetFragment(LessonEntry lessonEntry) {
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        Fragment fragment = supportFragmentManager.findFragmentByTag(AlphabetLessonFragment.TAG);

        if (fragment == null) {
            fragment = AlphabetLessonFragment.newInstance(lessonEntry);
        }

        supportFragmentManager.beginTransaction().replace(R.id.container, fragment).addToBackStack(
                AlphabetLessonFragment.TAG).commit();
    }

    private void showDefaultLessonFragment(LessonEntry lessonEntry) {
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        Fragment fragment = supportFragmentManager.findFragmentByTag(DefaultLessonFragment.TAG);

        if (fragment == null) {
            fragment = DefaultLessonFragment.newInstance(lessonEntry);
        }

        supportFragmentManager.beginTransaction().replace(R.id.container, fragment).addToBackStack(
                DefaultLessonFragment.TAG).commit();
    }
}
