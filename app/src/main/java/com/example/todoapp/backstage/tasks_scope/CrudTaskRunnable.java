package com.example.todoapp.backstage.tasks_scope;

import com.example.todoapp.abstracts.model.Repository;
import com.example.todoapp.backstage.InjectorHelper;
import com.example.todoapp.models.database.entity.Task;

import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

/**
 * Реализация CRUD спецификации работы с БД в виде запускаемой в потоке задачи
 */
public class CrudTaskRunnable implements Runnable {

    InjectorHelper injectorHelper = InjectorHelper.getInstance();

    @Inject
    AdapterStateHandler adapterStateHandler;

    @Inject
    RecycleAdapter recycleAdapter;

    @Inject
    Repository.TaskDao taskDao;

    private Action action;

    private Task task;

    private CrudTaskRunnable(){
        injectorHelper
                .getTaskComponent()
                .linkCrudTaskRunnableComponent()
                .inject(this);
    }

    public CrudTaskRunnable(Action action) {
        this();
        this.action = action;
    }

    public CrudTaskRunnable(Action action, Task task) {
        this();
        this.action = action;
        this.task = task;
    }

    @Override
    public void run() {
        switch (action) {
            case SELECT_ALL_IN_WORK:
                runSelectAllInWork();
                break;
            case SELECT_ALL_COMPLETED:
                runSelectAllCompleted();
                break;
            case SELECT_ALL_EXPIRED:
                runSelectAllExpired();
                break;
            case INSERT:
                runInsert();
                break;
            case UPDATE:
                runUpdate();
                break;
            case DELETE:
                runDelete();
                break;
        }
    }

    private void runSelectAllExpired() {
        List<Task> tasks = taskDao.getAllWithStatusAndDateAfter(Task.Status.IN_WORK,
                Calendar.getInstance().getTime());
        if (tasks != null) {
            recycleAdapter.setData(tasks);
            adapterStateHandler.sendEmptyMessage(0);
        }
    }

    private void runSelectAllCompleted() {
        List<Task> tasks = taskDao.getAllWithStatus(Task.Status.COMPLETED);
        if (tasks != null) {
            recycleAdapter.setData(tasks);
            adapterStateHandler.sendEmptyMessage(0);
        }
    }

    private void runInsert() {
        if (task != null) {
            taskDao.insert(task);
            adapterStateHandler.sendEmptyMessage(0);
        }
    }

    private void runUpdate() {
        if (task != null) {
            taskDao.update(task);
            adapterStateHandler.sendEmptyMessage(0);
        }
    }

    private void runSelectAllInWork() {
        List<Task> tasks = taskDao.getAllWithStatusAndDateBefore(Task.Status.IN_WORK,
                Calendar.getInstance().getTime());
        if (tasks != null) {
            recycleAdapter.setData(tasks);
            adapterStateHandler.sendEmptyMessage(0);
        }
    }

    private void runDelete(){
        taskDao.delete(task);
        adapterStateHandler.sendEmptyMessage(0);
    }

    public enum Action {
        UPDATE,
        INSERT,
        SELECT_ALL_IN_WORK,
        SELECT_ALL_COMPLETED,
        SELECT_ALL_EXPIRED,
        DELETE
    }
}
