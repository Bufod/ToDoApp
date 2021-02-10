package com.example.todoapp.abstracts.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.todoapp.R;

public abstract class BaseFragment extends Fragment implements BaseView {

    protected final int FRAGMENT_LAYOUT;

    protected BaseFragment(@LayoutRes int fragment_layout) {
        FRAGMENT_LAYOUT = fragment_layout;
    }

    protected abstract void initView(View rootView, Bundle savedInstanceState);
    protected abstract void init(Bundle savedInstanceState);

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(
                FRAGMENT_LAYOUT,
                container,
                false
        );
        initView(rootView, savedInstanceState);
        return rootView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init(savedInstanceState);
    }
}
