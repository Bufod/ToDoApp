package com.example.todoapp.abstracts.contracts;

import android.os.Bundle;

import com.example.todoapp.abstracts.presenter.BaseMvpPresenter;
import com.example.todoapp.abstracts.view.BaseView;
import com.example.todoapp.backstage.main_activity.PresenterEventBus;

public interface MainActivityContract {
    interface Presenter extends BaseMvpPresenter<MainActivityContract.View>, PresenterEventBus.SubscriberSelectedEvent,
            PresenterEventBus.SubscriberCreatedEvent{
        void initScreen();
        void goToTaskEditorFragment(Bundle bundle);
        void goToTaskEditorFragment();
        void goToTaskSpaceFragment();
        void goToTaskSpaceFragment(Bundle bundle);
    }
    interface View extends BaseView{

    }
}
