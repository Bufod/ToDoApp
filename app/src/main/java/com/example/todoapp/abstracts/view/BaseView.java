package com.example.todoapp.abstracts.view;

import android.content.Context;
import android.view.View;

public interface BaseView {
    default void showView(View view, boolean isShown){
        view.setVisibility(isShown ? View.VISIBLE : View.GONE);
    }

    Context getContext();
}
