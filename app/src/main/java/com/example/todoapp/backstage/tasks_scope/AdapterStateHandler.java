package com.example.todoapp.backstage.tasks_scope;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import androidx.annotation.NonNull;

import javax.inject.Inject;

/**
 * Слушает очередь сообщений реагирующих на изменение в БД и требующих обновления адаптера
 */
public class AdapterStateHandler extends Handler {

    RecycleAdapter recycleAdapter;

    @Inject
    public AdapterStateHandler(RecycleAdapter recycleAdapter) {
        super(Looper.getMainLooper());
        this.recycleAdapter = recycleAdapter;
    }

    @Override
    public void handleMessage(@NonNull Message msg) {
        if (recycleAdapter != null && msg.what == 0) {
            recycleAdapter.notifyDataChanged();
        }
    }
}
