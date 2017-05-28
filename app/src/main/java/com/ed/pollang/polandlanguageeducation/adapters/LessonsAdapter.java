package com.ed.pollang.polandlanguageeducation.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ed.pollang.polandlanguageeducation.LessonPresenter;
import com.ed.pollang.polandlanguageeducation.R;
import com.ed.pollang.polandlanguageeducation.entries.LessonEntry;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LessonsAdapter extends RecyclerView.Adapter {
    private List<LessonEntry> lessonEntryList;
    private Context context;
    private LessonPresenter lessonPresenter;

    public LessonsAdapter(Context context, @NonNull LessonPresenter lessonPresenter) {
        this.context = context;
        this.lessonPresenter = lessonPresenter;
        lessonEntryList = new ArrayList<>();
    }

    public LessonsAdapter(Context context, @NonNull LessonPresenter lessonPresenter,
            List<LessonEntry> lessonEntryList) {
        this(context, lessonPresenter);
        this.lessonEntryList = lessonEntryList == null ? new ArrayList<LessonEntry>() : lessonEntryList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.lesson_item, viewGroup, false);
        return new LessonsItemHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int position) {
        final LessonEntry lessonEntry = lessonEntryList.get(position);
        LessonsItemHolder holder = (LessonsItemHolder) viewHolder;
        holder.metadata.setText(lessonEntry.getMetadata());
        holder.data.setText(lessonEntry.getData());
        holder.description.setText(lessonEntry.getDesc());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lessonPresenter.showLesson(lessonEntry);
            }
        });
    }

    @Override
    public int getItemCount() {
        return lessonEntryList.size();
    }

    public void setData(List<LessonEntry> lessonEntryList) {
        this.lessonEntryList = lessonEntryList;
    }

    class LessonsItemHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.main_screen_lesson_metadata)
        TextView metadata;

        @BindView(R.id.main_screen_lesson_data)
        TextView data;

        @BindView(R.id.main_screen_lesson_desc)
        TextView description;

        public LessonsItemHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
