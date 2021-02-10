package com.example.todoapp.abstracts.presenter;

import android.view.View;

import com.example.todoapp.abstracts.view.BaseView;

public interface BaseMvpPresenter<V extends BaseView> {
    boolean isAttached();
    void attach(V view);
    void detach();
}
