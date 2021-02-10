package com.example.todoapp.abstracts.contracts;

import android.os.Bundle;

import com.example.todoapp.abstracts.presenter.BasePresenterInterface;
import com.example.todoapp.abstracts.view.BaseView;
import com.example.todoapp.backstage.tasks_scope.task_list.RecycleViewItemClickListener;
import com.yuyakaido.android.cardstackview.CardStackView;

public interface TaskListContract {
    String MSG = "SelectFilter";
    interface PresenterInterface extends BasePresenterInterface<View>,
            RecycleViewItemClickListener {

        /**
         * Настройка экземпляра класса {@link CardStackView}
         * @param swipeStack Отображаемый список представленный стеком
         * @see <a href="https://github.com/yuyakaido/CardStackView">CardStackView</a>
         */
        void initializeSwipeStack(CardStackView swipeStack);

        android.view.View.OnClickListener btAddOnClickListener();
        android.view.View.OnClickListener btDeleteOnClickListener();
        android.view.View.OnClickListener btCompleteOnClickListener();
    }
    interface View extends BaseView{
        Bundle getArguments();
    }
}
