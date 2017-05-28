package com.ed.pollang.polandlanguageeducation.fragments;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

class TwoDirectionsItemDecorator extends RecyclerView.ItemDecoration {
    private Context context;

    public TwoDirectionsItemDecorator(Context context, int itemDivider) {
        this.context = context;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
    }
}
