package com.example.todoapp.views;


import android.app.Application;

import com.example.todoapp.backstage.InjectorHelper;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        InjectorHelper
                .getInstance()
                .initAppComponent(getApplicationContext());
    }
}
