package com.example.todoapp.backstage.tasks_scope.task_editor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.todoapp.models.database.entity.Task;

public class TaskPriorityAdapter extends ArrayAdapter<Task.Status> {

    private final Context context;
    private final Task.Status[] data;
    private final int layoutResourceId;

    public TaskPriorityAdapter(@NonNull Context context, int resource, @NonNull Task.Status[] objects) {
        super(context, resource, objects);
        this.context = context;
        this.data = objects;
        this.layoutResourceId = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


        if (convertView == null)
            convertView = LayoutInflater.from(context).inflate(layoutResourceId, parent, false);

        TextView text = convertView.findViewById(android.R.id.text1);
        text.setText(data[position].toString());
        return convertView;
    }
}
