package com.example.todoapp.views.fragments;

import android.os.Bundle;
import android.view.View;

import com.example.todoapp.R;
import com.example.todoapp.abstracts.contracts.TaskListContract;
import com.example.todoapp.abstracts.view.BaseFragment;
import com.example.todoapp.backstage.InjectorHelper;
import com.example.todoapp.backstage.tasks_scope.RecycleAdapter;
import com.example.todoapp.presenters.TaskListPresenter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import javax.inject.Inject;

public class TaskListFragment extends BaseFragment implements TaskListContract.View {

    @Inject
    TaskListPresenter presenter;

    @Inject
    RecycleAdapter recycleAdapter;

    InjectorHelper injectorHelper = InjectorHelper.getInstance();

    public TaskListFragment() {
        super(R.layout.fragment_task_list);
    }

    public static TaskListFragment newInstance(Bundle bundle) {
        TaskListFragment fragment = newInstance();
        fragment.setArguments(bundle);
        return fragment;
    }

    public static TaskListFragment newInstance() {
        return new TaskListFragment();
    }

    @Override
    protected void initView(View rootView, Bundle savedInstanceState) {
        presenter.attach(this);
        presenter.initializeSwipeStack(rootView.findViewById(R.id.recView));

        ((FloatingActionButton) rootView.findViewById(R.id.addBt))
                .setOnClickListener(
                        presenter.btAddOnClickListener()
                );

        ((FloatingActionButton) rootView.findViewById(R.id.deleteBt))
                .setOnClickListener(
                        presenter.btDeleteOnClickListener()
                );

        ((FloatingActionButton) rootView.findViewById(R.id.completeBt))
                .setOnClickListener(
                        presenter.btCompleteOnClickListener()
                );


    }

    @Override
    protected void init(Bundle savedInstanceState) {

        injectorHelper
                .initTaskListComponent()
                .inject(this);
        presenter.attach(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        injectorHelper.clearTaskListComponent();
    }
}