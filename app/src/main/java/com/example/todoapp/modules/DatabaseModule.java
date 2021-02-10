package com.example.todoapp.modules;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Room;

import com.example.todoapp.models.database.Database;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public abstract class DatabaseModule {

    @Provides
    @Singleton
    static Database providesDatabase(@NonNull Context appContext) {
        return Room.databaseBuilder(
                appContext,
                Database.class,
                "task-database"
        ).build();
    }
}
