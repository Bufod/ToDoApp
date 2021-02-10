package com.example.todoapp.abstracts.contracts;

import com.example.todoapp.abstracts.presenter.BaseMvpPresenter;
import com.example.todoapp.abstracts.view.BaseView;
import com.google.android.material.tabs.TabLayout;

public interface TaskSpaceContract {
    interface Presenter extends BaseMvpPresenter<TaskSpaceContract.View> {
        void initializeViewComponents(TabLayout tabLayout, int containerRes);
    }
    interface View extends BaseView {

    }
}
