package com.example.todoapp.backstage.tasks_scope.task_editor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.inject.Inject;

public class DateFormatter extends SimpleDateFormat {

    @Inject
    public DateFormatter() {
        super("dd.MM.yyyy", Locale.getDefault());
    }

    public Date getDate(String dateRes) throws ParseException {
        return parse(dateRes);
    }

    public String getDate(Calendar calendar){
        return format(calendar.getTime());
    }


    public String getDate(Date date) {
        return format(date);
    }
}
