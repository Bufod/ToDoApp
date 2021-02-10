package com.example.todoapp.components;

import com.example.todoapp.backstage.tasks_scope.CrudTaskRunnable;

import dagger.Subcomponent;


@Subcomponent
public interface CrudTaskRunnableComponent {
    void inject(CrudTaskRunnable crudTaskRunnable);
}
