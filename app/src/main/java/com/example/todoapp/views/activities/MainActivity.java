package com.example.todoapp.views.activities;

import android.os.Bundle;

import com.example.todoapp.R;
import com.example.todoapp.abstracts.contracts.MainActivityContract;
import com.example.todoapp.abstracts.presenter.BasePresenter;
import com.example.todoapp.abstracts.view.BaseCompatActivity;
import com.example.todoapp.abstracts.view.BaseView;
import com.example.todoapp.backstage.InjectorHelper;
import com.example.todoapp.presenters.MainActivityPresenter;

import javax.inject.Inject;

public class MainActivity extends BaseCompatActivity implements MainActivityContract.View {

    InjectorHelper injectorHelper = InjectorHelper.getInstance();

    @Inject
    MainActivityPresenter presenter;

    @Override
    protected void init(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);

        injectorHelper
                .initMainActivityComponent(
                        getSupportFragmentManager()
                )
                .inject(this);

        presenter.attach(this);
        presenter.initScreen();
    }


    @Override
    protected BasePresenter<? extends BaseView> getPresenter() {
        return presenter;
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() <= 1) {
            super.onBackPressed();
        } else {
            getSupportFragmentManager().popBackStack();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        injectorHelper.clearMainActivityComponent();
    }
}