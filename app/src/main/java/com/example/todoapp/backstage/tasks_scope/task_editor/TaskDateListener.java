package com.example.todoapp.backstage.tasks_scope.task_editor;

import android.app.DatePickerDialog;
import android.content.Context;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

import javax.inject.Inject;
import javax.inject.Named;


public class TaskDateListener implements View.OnClickListener, DatePickerDialog.OnDateSetListener {

    @Inject
    @Named("TaskEditorContext")
    Context context;

    private TextView dateTV;

    @Inject
    public DateFormatter dateFormatter;

    @Inject
    public TaskDateListener() {
    }

    @Override
    public void onClick(View v) {
        Calendar calendar = Calendar.getInstance();


        try {
            Date date = dateFormatter.getDate(dateTV.getText().toString());
            calendar.setTime(date);
            DatePickerDialog dialog = new DatePickerDialog(
                    context,
                    android.R.style.Theme_Dialog,
                    this,
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)
            );
            dialog.show();
        } catch (ParseException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, dayOfMonth);
        Objects.requireNonNull(dateTV).setText(dateFormatter.getDate(calendar));
    }

    public void setDateTV(TextView dateTV) {
        this.dateTV = dateTV;
        this.dateTV.setOnClickListener(this);
    }
}
