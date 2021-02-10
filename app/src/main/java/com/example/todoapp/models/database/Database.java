package com.example.todoapp.models.database;

import androidx.room.RoomDatabase;

import com.example.todoapp.abstracts.model.Repository;
import com.example.todoapp.models.database.entity.Task;

@androidx.room.Database(entities = {Task.class}, version = 1)
public abstract class Database extends RoomDatabase {
    public abstract Repository.TaskDao taskDao();
}
