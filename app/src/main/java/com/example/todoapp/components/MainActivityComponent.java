package com.example.todoapp.components;


//import com.example.todoapp.modules.MainActivityModule;

import androidx.fragment.app.FragmentManager;

import com.example.todoapp.backstage.main_activity.MainActivityScope;
import com.example.todoapp.modules.MainActivityModule;
import com.example.todoapp.modules.TaskModule;
import com.example.todoapp.views.activities.MainActivity;

import dagger.BindsInstance;
import dagger.Subcomponent;

@Subcomponent(
        modules = {
                MainActivityModule.class
        }
)
@MainActivityScope
public interface MainActivityComponent {

    void inject(MainActivity mainActivity);

    TaskComponent linkTaskComponent(TaskModule taskModule);

    @Subcomponent.Builder
    interface Builder {

        MainActivityComponent build();

        @BindsInstance
        Builder fragmentManager(FragmentManager fragmentManager);
    }

}
