package com.example.todoapp.abstracts.presenter;

import com.example.todoapp.abstracts.view.BaseView;

//Абстрактная реализация интерфейсной логики для прмитивных методов
public abstract class BasePresenter<V extends BaseView> implements BasePresenterInterface<V> {

    protected V view;

    @Override
    public boolean isAttached() {
        return view != null;
    }

    @Override
    public void attach(V view) {
        this.view = view;
    }

    @Override
    public void detach() {
        this.view = null;
    }
}
