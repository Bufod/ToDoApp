package com.example.todoapp.abstracts.contracts;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.todoapp.abstracts.presenter.BasePresenterInterface;
import com.example.todoapp.abstracts.view.BaseView;

public interface TaskEditorContract {
    String MSG = "SelectedTask";
    interface Presenter extends BasePresenterInterface<View> {

        /**
         * Инициализация презентера, проверка приходящих для редактирования задач
         */
        void initializePresenter();

        /**
         * Инициализация всех отображаемых компонент
         */
        void initializeViewComponents(EditText taskName, EditText description, Spinner taskStatus,
                                      TextView taskDate, TextView taskTime);

        android.view.View.OnClickListener saveBtOnClickListener();
        android.view.View.OnClickListener deleteBtOnClickListener();
    }
    interface View extends BaseView {
        Bundle getArguments();
        Activity getActivity();
    }
}
