package com.example.todoapp.abstracts.view;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.todoapp.abstracts.presenter.BasePresenter;

public abstract class BaseCompatActivity extends AppCompatActivity implements BaseView {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getPresenter().detach();
    }

    protected abstract BasePresenter<? extends BaseView> getPresenter();

    protected abstract void init(Bundle savedInstanceState);

    @Override
    public Context getContext() {
        return this;
    }
}
