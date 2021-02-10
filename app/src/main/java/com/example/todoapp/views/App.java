package com.example.todoapp.views;


import android.app.Application;

import androidx.fragment.app.FragmentManager;

import com.example.todoapp.backstage.InjectorHelper;
import com.example.todoapp.components.AppComponent;

import com.example.todoapp.components.MainActivityComponent;
import com.example.todoapp.modules.MainActivityModule;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        InjectorHelper
                .getInstance()
                .initAppComponent(getApplicationContext());
    }
}
