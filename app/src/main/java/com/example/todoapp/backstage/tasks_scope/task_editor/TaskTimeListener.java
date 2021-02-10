package com.example.todoapp.backstage.tasks_scope.task_editor;

import android.app.TimePickerDialog;
import android.content.Context;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

import javax.inject.Inject;
import javax.inject.Named;

public class TaskTimeListener implements View.OnClickListener, TimePickerDialog.OnTimeSetListener{

    @Inject
    @Named("TaskEditorContext")
    Context context;

    @Inject
    TimeFormatter timeFormatter;

    private TextView timeTV;

    @Inject
    public TaskTimeListener() {
    }


    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        Objects.requireNonNull(timeTV).setText(timeFormatter.getTime(hourOfDay, minute));
    }

    @Override
    public void onClick(View v) {

        Calendar calendar = Calendar.getInstance();


        try {
            Date date = timeFormatter.getTime(timeTV.getText().toString());
            calendar.setTime(date);
            TimePickerDialog dialog = new TimePickerDialog(
                    context,
                    android.R.style.Theme_Dialog,
                    this,
                    calendar.get(Calendar.HOUR_OF_DAY),
                    calendar.get(Calendar.MINUTE),
                    true
            );
            dialog.show();
        } catch (ParseException e) {
            e.printStackTrace();
        }


    }

    public void setTimeTV(TextView timeTV) {
        this.timeTV = timeTV;
        this.timeTV.setOnClickListener(this);
    }
}
