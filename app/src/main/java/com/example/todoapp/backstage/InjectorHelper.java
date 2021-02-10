package com.example.todoapp.backstage;

import android.content.Context;

import androidx.fragment.app.FragmentManager;

import com.example.todoapp.components.AppComponent;
import com.example.todoapp.components.CrudTaskRunnableComponent;
import com.example.todoapp.components.DaggerAppComponent;
import com.example.todoapp.components.MainActivityComponent;
import com.example.todoapp.components.TaskComponent;
import com.example.todoapp.components.TaskEditorComponent;
import com.example.todoapp.components.TaskListComponent;
import com.example.todoapp.modules.TaskModule;

import javax.inject.Inject;

import dagger.Module;
import dagger.Provides;


public class InjectorHelper {
    private static final InjectorHelper INJECTOR_HELPER = new InjectorHelper();

    AppComponent appComponent;
    MainActivityComponent mainActivityComponent;
    TaskComponent taskComponent;
    TaskListComponent taskListComponent;
    TaskEditorComponent taskEditorComponent;
    CrudTaskRunnableComponent crudTaskRunnableComponent;

    public AppComponent initAppComponent(Context context){
        appComponent = DaggerAppComponent.builder()
                .context(context)
                .build();
        return appComponent;
    }

    public AppComponent getAppComponent() {
        if (appComponent == null)
            throw new NullPointerException("AppComponent is not initialized");
        return appComponent;
    }

    public void clearAppComponent(){
        appComponent = null;
    }

    public MainActivityComponent initMainActivityComponent(FragmentManager fragmentManager){
        mainActivityComponent =  getAppComponent()
                .linkMainActivityComponent()
                .fragmentManager(fragmentManager)
                .build();
        return mainActivityComponent;
    }

    public MainActivityComponent getMainActivityComponent() {
        if (mainActivityComponent == null)
            throw new NullPointerException("MainActivityComponent is not initialized");
        return mainActivityComponent;
    }

    public void clearMainActivityComponent(){
        mainActivityComponent = null;
    }

    public TaskComponent initTaskComponent(){
        taskComponent =  getMainActivityComponent()
                .linkTaskComponent(new TaskModule());
        return taskComponent;
    }

    public TaskComponent getTaskComponent() {
        if (taskComponent == null)
            throw new NullPointerException("TaskComponent is not initialized");
        return taskComponent;
    }

    public void clearTaskComponent(){
        taskComponent = null;
    }

    public TaskEditorComponent initTaskEditorComponent(Context context){
        taskEditorComponent = getTaskComponent()
                .linkTaskEditorComponent()
                .context(context)
                .build();
        return taskEditorComponent;
    }

    public TaskEditorComponent getTaskEditorComponent() {
        if (taskEditorComponent == null)
            throw new NullPointerException("TaskEditorComponent is not initialized");
        return taskEditorComponent;
    }

    public void clearTaskEditorComponent(){
        taskEditorComponent = null;
    }

    public TaskListComponent initTaskListComponent(){
        taskListComponent = getTaskComponent()
                .linkTaskListComponent();
        return taskListComponent;
    }

    public TaskListComponent getTaskListComponent() {
        if (taskListComponent == null)
            throw new NullPointerException("TaskListComponent is not initialized");
        return taskListComponent;
    }

    public void clearTaskListComponent(){
        taskListComponent = null;
    }

    public static InjectorHelper getInstance() {
        return INJECTOR_HELPER;
    }
}
