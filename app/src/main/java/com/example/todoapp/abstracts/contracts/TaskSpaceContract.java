package com.example.todoapp.abstracts.contracts;

import com.example.todoapp.abstracts.presenter.BasePresenterInterface;
import com.example.todoapp.abstracts.view.BaseView;
import com.google.android.material.tabs.TabLayout;

public interface TaskSpaceContract {
    interface PresenterInterface extends BasePresenterInterface<View> {

        /**
         * Инициализация всех отображаемых компонент
         * @param tabLayout - Экземпляр {@link TabLayout}
         * @param containerRes - Ресурс с контейнером для отображения фрагментов
         */
        void initializeViewComponents(TabLayout tabLayout, int containerRes);
    }
    interface View extends BaseView {

    }
}
