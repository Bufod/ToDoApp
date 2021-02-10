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

        /**
         * Выбор всех задач с фильтром по статусу и дате позже текущего момента
         * @param status Статус задачи из {@link com.example.todoapp.models.database.entity.Task.Status}
         * @param currentDate Текущее время
         * @return List<Task>: список задач
         */
        @Query("SELECT * FROM task WHERE status = :status AND expiry_date > :currentDate")
        @TypeConverters({StatusConverter.class, DateConverter.class})
        List<Task> getAllWithStatusAndDateBefore(Task.Status status, Date currentDate);

        /**
         * Выбор всех задач с фильтром по статусу и дате раньше текущего момента
         * @param status Статус задачи из {@link com.example.todoapp.models.database.entity.Task.Status}
         * @param currentDate Текущее время
         * @return List<Task>: список задач
         */
        @Query("SELECT * FROM task WHERE status = :status AND expiry_date <= :currentDate")
        @TypeConverters({StatusConverter.class, DateConverter.class})
        List<Task> getAllWithStatusAndDateAfter(Task.Status status, Date currentDate);

        /**
         * Выбор всех задач с фильтром по статусу
         * @param status Статус задачи из {@link com.example.todoapp.models.database.entity.Task.Status}
         * @return List<Task>: список задач
         */
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
