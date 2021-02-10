package com.example.todoapp.views.fragments;

import android.os.Bundle;
import android.view.View;

import com.example.todoapp.R;
import com.example.todoapp.abstracts.contracts.TaskSpaceContract;
import com.example.todoapp.abstracts.view.BaseFragment;
import com.example.todoapp.backstage.InjectorHelper;
import com.example.todoapp.presenters.TaskSpacePresenter;
import com.google.android.material.tabs.TabLayout;

import javax.inject.Inject;


public class TaskSpaceFragment extends BaseFragment implements TaskSpaceContract.View {

    @Inject
    TaskSpacePresenter presenter;

    InjectorHelper injectorHelper = InjectorHelper.getInstance();

    public TaskSpaceFragment() {
        super(R.layout.fragment_task_space);
    }

    public static TaskSpaceFragment newInstance(Bundle bundle) {
        TaskSpaceFragment fragment = newInstance();
        fragment.setArguments(bundle);
        return fragment;
    }

    public static TaskSpaceFragment newInstance() {
        return new TaskSpaceFragment();
    }

    @Override
    protected void initView(View rootView, Bundle savedInstanceState) {
        TabLayout tabLayout = rootView.findViewById(R.id.tabs);
        presenter.initializeViewComponents(tabLayout, R.id.container);
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        injectorHelper
                .initTaskComponent()
                .inject(this);
        presenter.attach(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        injectorHelper.clearTaskComponent();
    }
}