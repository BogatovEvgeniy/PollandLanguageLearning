package com.ed.pollang.polandlanguageeducation.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import java.io.IOException;
import java.io.InputStream;

import butterknife.ButterKnife;

public class BaseFragment extends Fragment {

    protected SupportActionBarHolder supportActionBarHolder;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof SupportActionBarHolder) {
            this.supportActionBarHolder = (SupportActionBarHolder) context;
        } else {
            throw new RuntimeException("The fragment parent should implement SupportActionBarHolder interface");
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
    }

    protected String getStringJsonFromAssets(String fileName) {
        String json = null;
        try {
            InputStream is = getContext().getAssets().open(fileName);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return json;
    }
}