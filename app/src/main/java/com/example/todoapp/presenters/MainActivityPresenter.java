package com.example.todoapp.presenters;

import android.os.Bundle;

import androidx.fragment.app.FragmentManager;

import com.example.todoapp.R;
import com.example.todoapp.abstracts.presenter.BasePresenter;
import com.example.todoapp.abstracts.contracts.MainActivityContract;
import com.example.todoapp.backstage.main_activity.PresenterEventBus;
import com.example.todoapp.views.fragments.TaskEditorFragment;
import com.example.todoapp.views.fragments.TaskListFragment;
import com.example.todoapp.views.fragments.TaskSpaceFragment;

import javax.inject.Inject;

public class MainActivityPresenter
        extends BasePresenter<MainActivityContract.View>
        implements MainActivityContract.Presenter{

    @Inject
    FragmentManager fragmentManager;

    @Inject
    PresenterEventBus presenterEventBus;

    @Inject
    public MainActivityPresenter() {
    }

    @Inject
    protected void subscribe(){
        presenterEventBus.subscribe(
                this,
                PresenterEventBus.EventType.SELECTED_TASK,
                PresenterEventBus.EventType.CREATED_TASK
        );
    }

    public void initScreen() {
        goToTaskSpaceFragment();
    }

    public void goToTaskEditorFragment(Bundle bundle) {
        fragmentManager.beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.workSpace, TaskEditorFragment.class, bundle)
                .addToBackStack("EditorFragment")
                .commit();
    }

    public void goToTaskEditorFragment() {
        goToTaskEditorFragment(null);
    }

    public void goToTaskSpaceFragment() {
        goToTaskSpaceFragment(null);
    }

    public void goToTaskSpaceFragment(Bundle bundle) {
        fragmentManager.beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.workSpace, TaskSpaceFragment.class, bundle)
                .commit();
    }

    @Override
    public void onSelectedEvent(Bundle bundle) {
        goToTaskEditorFragment(bundle);
    }

    @Override
    public void onCreatedEvent(Bundle bundle) {
        goToTaskEditorFragment();
    }


}
