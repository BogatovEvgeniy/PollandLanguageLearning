package com.ed.pollang.polandlanguageeducation.adapters;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ed.pollang.polandlanguageeducation.R;
import com.ed.pollang.polandlanguageeducation.entries.LessonWordsEntry;

import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WordsAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<LessonWordsEntry> lessonWords;
    private MediaPlayer player;
    private ViewGroup parent;

    public WordsAdapter(Context context, List<LessonWordsEntry> lessonWords) {
        this.context = context;
        this.lessonWords = lessonWords;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.parent = parent;
        View view = LayoutInflater.from(context).inflate(R.layout.default_lesson_word_list_item, parent, false);
        return new WordsAdapterHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        LessonWordsEntry lessonWordsEntry = lessonWords.get(position);
        WordsAdapterHolder adapterHolder = (WordsAdapterHolder) holder;
        adapterHolder.ruWord.setText(lessonWordsEntry.getRu());
        adapterHolder.plWord.setText(lessonWordsEntry.getPl());
        adapterHolder.spundIconView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (player == null) {
                    try {
                        final AssetFileDescriptor afd = context.getAssets().openFd(
                                lessonWords.get(position).getSound());
                        player = new MediaPlayer();
                        player.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
                        player.prepare();
                        player.start();
                        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mp) {
                                parent.setEnabled(true);
                                stopPlayer();
                            }
                        });
                        parent.setEnabled(false);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    public void onStop() {
        stopPlayer();
    }

    private void stopPlayer() {
        if (player != null) {
            player.stop();
            player.reset();
            player.release();
            player = null;
        }
    }

    @Override
    public int getItemCount() {
        return lessonWords.size();
    }

    class WordsAdapterHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.default_lesson_list_item_ru)
        TextView ruWord;
        @BindView(R.id.default_lesson_list_item_pl)
        TextView plWord;
        @BindView(R.id.default_lesson_list_item_sound)
        ImageView spundIconView;

        public WordsAdapterHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
