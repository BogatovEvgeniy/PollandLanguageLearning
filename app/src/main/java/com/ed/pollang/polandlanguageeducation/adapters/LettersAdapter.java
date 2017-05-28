package com.ed.pollang.polandlanguageeducation.adapters;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ed.pollang.polandlanguageeducation.R;
import com.ed.pollang.polandlanguageeducation.entries.LetterEntry;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LettersAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<LetterEntry> lettersList;
    private MediaPlayer player;
    private ViewGroup parent;

    public LettersAdapter(Context context, List<LetterEntry> lettersList) {
        this.context = context;
        this.lettersList = lettersList == null ? new ArrayList<LetterEntry>() : lettersList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.parent = parent;
        View view = LayoutInflater.from(context).inflate(R.layout.alphabet_item, parent, false);
        return new LetterHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final LetterEntry letterEntry = lettersList.get(position);
        LetterHolder letterHolder = (LetterHolder) holder;
        letterHolder.letterView.setText(letterEntry.getSign());
        letterHolder.letterView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (player == null) {
                    try {
                        final AssetFileDescriptor afd = context.getAssets().openFd(letterEntry.getSign_sound_file());
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
        return lettersList.size();
    }

    class LetterHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.letter_item_content)
        TextView letterView;

        public LetterHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
