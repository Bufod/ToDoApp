package com.example.todoapp.models.database.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.todoapp.models.database.converter.DateConverter;
import com.example.todoapp.models.database.converter.StatusConverter;

import java.io.Serializable;
import java.util.Date;

@Entity
@TypeConverters({DateConverter.class, StatusConverter.class})
public class Task implements Serializable {
    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "status")
    @NonNull
    public Status status;

    @ColumnInfo(name = "expiry_date")
    public Date expiryDate;

    @ColumnInfo(name = "description")
    public String description;

    public Task() {
        status = Status.IN_WORK;
    }

    public Task(String name, @NonNull Status status, Date expiryDate) {
        this.name = name;
        this.status = status;
        this.expiryDate = expiryDate;
    }

    public Task(String name, @NonNull Status status, Date expiryDate, String description) {
        this.name = name;
        this.status = status;
        this.expiryDate = expiryDate;
        this.description = description;
    }


    public enum Status {
        IN_WORK("В работе"),
        COMPLETED("Завершено");

        String name;

        Status(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
    }

}
