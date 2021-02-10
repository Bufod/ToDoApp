package com.example.todoapp.backstage.tasks_scope.task_editor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.inject.Inject;

public class TimeFormatter extends SimpleDateFormat {

    @Inject
    public TimeFormatter(){
        super("HH:mm", Locale.getDefault());
    }


    public Date getTime(String dateRes) throws ParseException {
        return parse(dateRes);
    }

    public String getTime(Calendar calendar){
        return format(calendar.getTime());
    }


    public String getTime(Date date) {
        return format(date);
    }

    public String getTime(int hourOfDay, int minute) {
        return String.format(Locale.getDefault(), "%02d:%02d", hourOfDay, minute);
    }
}
