package com.example.todoapp.modules;

import com.example.todoapp.backstage.main_activity.PresenterEventBus;
import com.example.todoapp.backstage.main_activity.MainActivityScope;
import com.example.todoapp.views.fragments.TaskEditorFragment;
import com.example.todoapp.views.fragments.TaskListFragment;

import dagger.Module;
import dagger.Provides;

@Module
public class MainActivityModule {

    @Provides
    public TaskListFragment provideTaskListFragment(){
        return TaskListFragment.newInstance();
    }

    @Provides
    public TaskEditorFragment provideTaskEditorFragment(){
        return TaskEditorFragment.newInstance();
    }

    @Provides
    @MainActivityScope
    public PresenterEventBus providePresenterEventBus(){
        return new PresenterEventBus();
    }
}
