package com.example.todoapp.components;

import com.example.todoapp.backstage.tasks_scope.TasksScope;
import com.example.todoapp.modules.TaskModule;
import com.example.todoapp.views.fragments.TaskSpaceFragment;

import dagger.Subcomponent;

@Subcomponent(
        modules = TaskModule.class
)
@TasksScope
public interface TaskComponent {

    void inject(TaskSpaceFragment taskSpaceFragment);

    TaskListComponent linkTaskListComponent();

    TaskEditorComponent.Builder linkTaskEditorComponent();

    CrudTaskRunnableComponent linkCrudTaskRunnableComponent();
}
