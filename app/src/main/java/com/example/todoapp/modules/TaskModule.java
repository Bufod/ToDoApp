package com.example.todoapp.modules;

import com.example.todoapp.abstracts.model.Repository;
import com.example.todoapp.backstage.tasks_scope.RecycleAdapter;
import com.example.todoapp.backstage.tasks_scope.TasksScope;
import com.example.todoapp.models.database.Database;

import dagger.Module;
import dagger.Provides;

@Module
public class TaskModule {
    @Provides
    @TasksScope
    public Repository.TaskDao provideTaskDao(Database database){
        return database.taskDao();
    }

    @Provides
    @TasksScope
    public RecycleAdapter provideRecycleAdapter(){
        return new RecycleAdapter();
    }


//    @Provides
//    @TasksScope
//    public AdapterStateHandler provideAdapterStateHandler(){
//        return new AdapterStateHandler();
//    }
}
