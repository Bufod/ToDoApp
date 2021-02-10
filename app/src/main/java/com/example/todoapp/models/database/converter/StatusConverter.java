package com.example.todoapp.models.database.converter;

import androidx.room.TypeConverter;

import com.example.todoapp.models.database.entity.Task;

public class StatusConverter {

    @TypeConverter
    public static Integer toInt(Task.Status status){
        return status == null ? null: status.ordinal();
    }

    @TypeConverter
    public static Task.Status toPriority(Integer ind){
        try {
            return ind == null ? null: Task.Status.values()[ind];
        } catch (ArrayIndexOutOfBoundsException e){
            e.printStackTrace();
            return null;
        }
    }
}
