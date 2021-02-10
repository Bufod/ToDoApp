package com.example.todoapp.abstracts.contracts;

import android.os.Bundle;
import android.view.View;

import com.example.todoapp.abstracts.presenter.BaseMvpPresenter;
import com.example.todoapp.abstracts.view.BaseView;
import com.example.todoapp.backstage.tasks_scope.task_list.RecycleViewItemClickListener;
import com.yuyakaido.android.cardstackview.CardStackView;

public interface TaskListContract {
    String MSG = "SelectFilter";
    interface Presenter extends BaseMvpPresenter<TaskListContract.View>,
            RecycleViewItemClickListener {
        void initializeSwipeStack(CardStackView swipeStack);
        android.view.View.OnClickListener btAddOnClickListener();
        android.view.View.OnClickListener btDeleteOnClickListener();
        android.view.View.OnClickListener btCompleteOnClickListener();
    }
    interface View extends BaseView{
        Bundle getArguments();
    }
}
