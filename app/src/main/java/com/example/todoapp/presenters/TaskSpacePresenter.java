package com.example.todoapp.presenters;

import androidx.fragment.app.FragmentManager;

import com.example.todoapp.R;
import com.example.todoapp.abstracts.contracts.TaskSpaceContract;
import com.example.todoapp.abstracts.presenter.BasePresenter;
import com.example.todoapp.backstage.tasks_scope.task_space.OnTabSelectedListener;
import com.google.android.material.tabs.TabLayout;

import javax.inject.Inject;

public class TaskSpacePresenter
        extends BasePresenter<TaskSpaceContract.View>
        implements TaskSpaceContract.PresenterInterface {

    String[] tabName;

    @Inject
    FragmentManager fragmentManager;

    @Inject
    public TaskSpacePresenter() {
    }

    @Override
    public void attach(TaskSpaceContract.View view) {
        super.attach(view);
        tabName = view.getContext().getResources().getStringArray(R.array.tab_name);
    }

    public void initializeViewComponents(TabLayout tabLayout, int containerRes) {

        tabLayout.addOnTabSelectedListener(
                new OnTabSelectedListener(fragmentManager, containerRes)
        );

        for (String s : tabName) {
            tabLayout.addTab(tabLayout.newTab().setText(s));
        }
    }
}
