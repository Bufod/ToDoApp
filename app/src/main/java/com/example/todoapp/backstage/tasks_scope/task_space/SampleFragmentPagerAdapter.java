package com.example.todoapp.backstage.tasks_scope.task_space;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.todoapp.views.fragments.TaskListFragment;


public class SampleFragmentPagerAdapter extends FragmentStateAdapter {
    final int PAGE_COUNT = 3;

    public SampleFragmentPagerAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }

    //TODO доделать фрагменты
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return TaskListFragment.newInstance();
        }
        return TaskListFragment.newInstance();
    }

    @Override
    public int getItemCount() {
        return PAGE_COUNT;
    }
}