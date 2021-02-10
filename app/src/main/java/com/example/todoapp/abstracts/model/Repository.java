package com.example.todoapp.abstracts.model;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.TypeConverters;
import androidx.room.Update;

import com.example.todoapp.models.database.converter.DateConverter;
import com.example.todoapp.models.database.converter.StatusConverter;
import com.example.todoapp.models.database.entity.Task;

import java.util.Date;
import java.util.List;

public interface Repository {

    @Dao
    interface TaskDao{
        @Query("SELECT * FROM task WHERE status = :status AND expiry_date > :currentDate")
        @TypeConverters({StatusConverter.class, DateConverter.class})
        List<Task> getAllWithStatusAndDateBefore(Task.Status status, Date currentDate);

        @Query("SELECT * FROM task WHERE status = :status AND expiry_date <= :currentDate")
        @TypeConverters({StatusConverter.class, DateConverter.class})
        List<Task> getAllWithStatusAndDateAfter(Task.Status status, Date currentDate);

        @Query("SELECT * FROM task WHERE status = :status")
        @TypeConverters({StatusConverter.class})
        List<Task> getAllWithStatus(Task.Status status);

        @Update
        void update(Task task);

        @Insert
        void insert(Task task);

        @Delete
        void delete(Task task);
    }
}
