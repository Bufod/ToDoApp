package com.example.todoapp.backstage.tasks_scope.task_space;


import android.os.Bundle;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.todoapp.abstracts.contracts.TaskListContract;
import com.example.todoapp.backstage.tasks_scope.CrudTaskRunnable;
import com.example.todoapp.views.fragments.TaskListFragment;
import com.google.android.material.tabs.TabLayout;

import javax.inject.Inject;

public class OnTabSelectedListener implements TabLayout.OnTabSelectedListener {
    final Bundle bundle = new Bundle();

    private final FragmentManager fragmentManager;

    private final int containerRes;

    public OnTabSelectedListener(FragmentManager fragmentManager, int containerRes) {
        this.fragmentManager = fragmentManager;
        this.containerRes = containerRes;
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction()
                .setReorderingAllowed(true);
        switch (tab.getPosition()) {
            case 0:
                bundle.putSerializable(TaskListContract.MSG, CrudTaskRunnable.Action.SELECT_ALL_IN_WORK);
                break;
            case 1:
                bundle.putSerializable(TaskListContract.MSG, CrudTaskRunnable.Action.SELECT_ALL_EXPIRED);
                break;
            case 2:
                bundle.putSerializable(TaskListContract.MSG, CrudTaskRunnable.Action.SELECT_ALL_COMPLETED);
                break;
        }
        fragmentTransaction.replace(containerRes, TaskListFragment.class, bundle);
        fragmentTransaction.commit();
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}