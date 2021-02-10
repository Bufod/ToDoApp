package com.example.todoapp.abstracts.contracts;

import android.os.Bundle;

import com.example.todoapp.abstracts.presenter.BasePresenterInterface;
import com.example.todoapp.abstracts.view.BaseView;
import com.example.todoapp.backstage.main_activity.PresenterEventBus;

public interface MainActivityContract {
    interface Presenter extends BasePresenterInterface<View>, PresenterEventBus.SubscriberSelectedEvent,
            PresenterEventBus.SubscriberCreatedEvent{

        /**
         * Инициализация TaskSpace фрагмента на стартовой активности
         */
        void initScreen();

        /**
         * Переход к фргменту редактирующему выбранную задачу. Задача сериализуется и передаётся
         * в качестве параметра bundle
         * @param bundle Сериализованная задача передаваемая для редактирования
         */
        void goToTaskEditorFragment(Bundle bundle);

        /**
         * Переход к фргменту редактору для создания новой задачи
         */
        void goToTaskEditorFragment();

        /**
         * Переход к фрагменту взаимодействия с созданными задачами (Отображение списков:
         * активных, просроченных и завершённых задач)
         */
        void goToTaskSpaceFragment();
    }

    interface View extends BaseView{

    }
}
