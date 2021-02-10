package com.example.todoapp.presenters;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.todoapp.abstracts.contracts.TaskEditorContract;
import com.example.todoapp.abstracts.model.Repository;
import com.example.todoapp.abstracts.presenter.BasePresenter;
import com.example.todoapp.backstage.tasks_scope.CrudTaskRunnable;
import com.example.todoapp.backstage.tasks_scope.task_editor.DateFormatter;
import com.example.todoapp.backstage.tasks_scope.task_editor.TaskDateListener;
import com.example.todoapp.backstage.tasks_scope.task_editor.TaskPriorityAdapter;
import com.example.todoapp.backstage.tasks_scope.task_editor.TaskTimeListener;
import com.example.todoapp.backstage.tasks_scope.task_editor.TimeFormatter;
import com.example.todoapp.models.database.entity.Task;

import org.jetbrains.annotations.NotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

import javax.inject.Inject;

import static com.example.todoapp.abstracts.contracts.TaskEditorContract.MSG;

public class TaskEditorPresenter
        extends BasePresenter<TaskEditorContract.View>
        implements TaskEditorContract.Presenter {

    Task currentTask;

    EditText taskName, taskDescription;
    Spinner taskStatus;
    TextView taskDate;
    TextView taskTime;

    @Inject
    TaskDateListener taskDateListener;

    @Inject
    TaskTimeListener taskTimeListener;

    @Inject
    Repository.TaskDao taskDao;

    @Inject
    public TaskEditorPresenter() {

    }

    public void initializePresenter() {
        try {
            this.currentTask = (Task) (Objects.requireNonNull(view.getArguments()).getSerializable(MSG));
        } catch (NullPointerException e) {
            this.currentTask = null;
        }

    }

    public void initializeViewComponents(EditText taskName, EditText description, Spinner taskStatus,
                                         TextView taskDate, TextView taskTime) {
        this.taskName = taskName;
        this.taskDescription = description;
        this.taskStatus = taskStatus;
        this.taskDate = taskDate;
        this.taskTime = taskTime;

        ArrayAdapter<Task.Status> adapter = new TaskPriorityAdapter(
                view.getContext(),
                android.R.layout.simple_spinner_item,
                Task.Status.values()
        );

        taskStatus.setAdapter(adapter);

        if (currentTask != null) {
            taskName.setText(currentTask.name);

            taskDescription.setText(currentTask.description);

            taskDate.setText(new DateFormatter().getDate(currentTask.expiryDate));

            taskTime.setText(new TimeFormatter().getTime(currentTask.expiryDate));

            taskStatus.setSelection(adapter.getPosition(currentTask.status));


        } else {
            Date date = Calendar.getInstance().getTime();
            taskDate.setText(new DateFormatter().getDate(date));
            taskTime.setText(new TimeFormatter().getTime(date));
        }

        taskDateListener.setDateTV(taskDate);
        taskTimeListener.setTimeTV(taskTime);

    }

    private boolean checkRequiredFields(EditText @NotNull ... editTexts) {
        boolean ok = true;
        for (EditText editText : editTexts) {
            if (editText.getText().toString().trim().equalsIgnoreCase("")) {
                editText.setError("Обязательное поле");
                ok = false;
            }
        }
        return ok;
    }

    public View.OnClickListener saveBtOnClickListener() {
        return v -> {
            if (!checkRequiredFields(taskName))
                return;

            String taskNameStr = taskName.getText().toString(),
                    taskDescriptionStr = taskDescription.getText().toString();
            Task.Status taskStatusVar = (Task.Status) taskStatus.getSelectedItem();
            Date dateTask = null;
            try {
                dateTask = new SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault()).parse(
                        String.format(
                                "%s %s",
                                taskDate.getText().toString(),
                                taskTime.getText().toString()
                        )
                );
            } catch (ParseException e) {
                e.printStackTrace();
            }

            CrudTaskRunnable.Action action = CrudTaskRunnable.Action.UPDATE;
            if (currentTask == null) {
                action = CrudTaskRunnable.Action.INSERT;

                currentTask = new Task(
                        taskNameStr,
                        taskStatusVar,
                        dateTask,
                        taskDescriptionStr
                );
            } else {
                currentTask.name = taskNameStr;
                currentTask.description = taskDescriptionStr;
                currentTask.status = taskStatusVar;
                currentTask.expiryDate = dateTask;
            }

            new Thread(
                    new CrudTaskRunnable(action, currentTask)
            ).start();

            view.getActivity().onBackPressed();
        };
    }

    @Override
    public View.OnClickListener deleteBtOnClickListener() {
        return v -> {
            if (currentTask != null)
                new Thread(
                        new CrudTaskRunnable(CrudTaskRunnable.Action.DELETE, currentTask)
                ).start();
            TaskEditorPresenter.this.view.getActivity().onBackPressed();
        };
    }

}
