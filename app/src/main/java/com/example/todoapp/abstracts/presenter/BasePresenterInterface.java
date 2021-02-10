package com.example.todoapp.abstracts.presenter;

import com.example.todoapp.abstracts.view.BaseView;

/**
 * Интерфейс базового презентера c прекрепляемой View. Позволяет ограничить релизацию презентера
 * только для View обозначенных в соответсвующем контракте
 * @param <V> View компонента MVP архитектуры
 */
public interface BasePresenterInterface<V extends BaseView> {
    boolean isAttached();
    void attach(V view);
    void detach();
}
