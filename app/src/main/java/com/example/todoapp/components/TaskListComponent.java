package com.example.todoapp.components;

import com.example.todoapp.views.fragments.TaskListFragment;

import dagger.Subcomponent;

@Subcomponent
public interface TaskListComponent {

    void inject(TaskListFragment taskListFragment);

}
