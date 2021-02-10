package com.example.todoapp.components;

import android.content.Context;

import androidx.fragment.app.FragmentActivity;

import com.example.todoapp.backstage.InjectorHelper;
import com.example.todoapp.models.database.Database;
import com.example.todoapp.modules.DatabaseModule;
import com.example.todoapp.modules.MainActivityModule;
import com.example.todoapp.views.activities.MainActivity;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;


@Component(
        modules = {
                DatabaseModule.class
        }
)
@Singleton
public interface AppComponent {

    Database getDatabase();

    MainActivityComponent.Builder linkMainActivityComponent();

    @Component.Builder
    interface Builder {

        AppComponent build();

        @BindsInstance
        Builder context(Context appContext);
    }
}
