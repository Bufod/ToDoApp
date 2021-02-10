package com.example.todoapp.components;

import android.content.Context;

import com.example.todoapp.backstage.tasks_scope.TasksScope;
import com.example.todoapp.modules.TaskModule;
import com.example.todoapp.views.fragments.TaskEditorFragment;

import javax.inject.Named;

import dagger.BindsInstance;
import dagger.Subcomponent;



@Subcomponent
public interface TaskEditorComponent {
    void inject(TaskEditorFragment taskEditorFragment);

    @Subcomponent.Builder
    interface Builder {

        TaskEditorComponent build();

        @BindsInstance
        Builder context(@Named("TaskEditorContext") Context context);
    }
}
