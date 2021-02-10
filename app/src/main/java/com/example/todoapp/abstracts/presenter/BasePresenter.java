package com.example.todoapp.abstracts.presenter;

import android.view.View;

import com.example.todoapp.abstracts.view.BaseView;

public abstract class BasePresenter<V extends BaseView> implements BaseMvpPresenter<V> {

    protected V view;

    @Override
    public boolean isAttached() {
        return view != null;
    }

    @Override
    public void attach(V view) {
        this.view = view;
    }

    @Override
    public void detach() {
        this.view = null;
    }
}
