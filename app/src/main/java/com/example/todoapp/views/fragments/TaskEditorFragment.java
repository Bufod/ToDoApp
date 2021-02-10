package com.example.todoapp.views.fragments;

import android.os.Bundle;

import android.view.View;
import android.widget.Button;

import com.example.todoapp.R;
import com.example.todoapp.abstracts.contracts.TaskEditorContract;
import com.example.todoapp.abstracts.view.BaseFragment;
import com.example.todoapp.backstage.InjectorHelper;
import com.example.todoapp.presenters.TaskEditorPresenter;

import javax.inject.Inject;


public class TaskEditorFragment extends BaseFragment implements TaskEditorContract.View {

    @Inject
    TaskEditorPresenter presenter;

    InjectorHelper injectorHelper = InjectorHelper.getInstance();

    public TaskEditorFragment() {
        super(R.layout.fragment_task_editor);
    }

    public static TaskEditorFragment newInstance(Bundle bundle) {
        TaskEditorFragment fragment = newInstance();
        fragment.setArguments(bundle);
        return fragment;
    }

    public static TaskEditorFragment newInstance() {
        return new TaskEditorFragment();
    }

    @Override
    protected void initView(View rootView, Bundle savedInstanceState) {
        presenter.initializeViewComponents(
                rootView.findViewById(R.id.taskName),
                rootView.findViewById(R.id.descriptionEt),
                rootView.findViewById(R.id.taskStatus),
                rootView.findViewById(R.id.taskDate),
                rootView.findViewById(R.id.taskTime)
        );

        ((Button)rootView.findViewById(R.id.saveBt))
                .setOnClickListener(presenter.saveBtOnClickListener());

        ((Button)rootView.findViewById(R.id.deleteBt))
                .setOnClickListener(presenter.deleteBtOnClickListener());
    }

    @Override
    protected void init(Bundle savedInstanceState) {

        injectorHelper
                .initTaskEditorComponent(
                        getContext()
                )
                .inject(this);


        presenter.attach(this);
        presenter.initializePresenter();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        injectorHelper.clearTaskEditorComponent();
    }
}