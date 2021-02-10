package com.example.todoapp.presenters;

import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;

import com.example.todoapp.abstracts.contracts.TaskEditorContract;
import com.example.todoapp.abstracts.contracts.TaskListContract;
import com.example.todoapp.abstracts.model.Repository;
import com.example.todoapp.abstracts.presenter.BasePresenter;
import com.example.todoapp.backstage.main_activity.PresenterEventBus;
import com.example.todoapp.backstage.tasks_scope.CrudTaskRunnable;
import com.example.todoapp.backstage.tasks_scope.RecycleAdapter;
import com.example.todoapp.backstage.tasks_scope.task_list.CardStackListener;
import com.example.todoapp.models.database.entity.Task;
import com.yuyakaido.android.cardstackview.CardStackLayoutManager;
import com.yuyakaido.android.cardstackview.CardStackView;
import com.yuyakaido.android.cardstackview.Direction;
import com.yuyakaido.android.cardstackview.Duration;
import com.yuyakaido.android.cardstackview.SwipeAnimationSetting;

import java.util.Objects;

import javax.inject.Inject;

import static com.example.todoapp.abstracts.contracts.TaskListContract.MSG;

public class TaskListPresenter
        extends BasePresenter<TaskListContract.View>
        implements TaskListContract.PresenterInterface {

    @Inject
    RecycleAdapter recycleAdapter;

    @Inject
    Repository.TaskDao taskDao;

    @Inject
    PresenterEventBus presenterEventBus;

    CardStackView swipeStack;

    @Inject
    public TaskListPresenter() {
    }

    @Inject
    public void stateHandlerToSubscribeAdapter() {
        recycleAdapter.setItemClickListener(this);
    }

    public void initializeSwipeStack(CardStackView swipeStack) {
        this.swipeStack = swipeStack;

        CardStackListener cardStackListener = new CardStackListener() {
            @Override
            public RecycleAdapter getRecycleAdapter() {
                return recycleAdapter;
            }

            @Override
            public CardStackView getCardStackView() {
                return swipeStack;
            }
        };

        CardStackLayoutManager cardStackLayoutManager =
                new CardStackLayoutManager(view.getContext(), cardStackListener);
        cardStackLayoutManager.setDirections(Direction.FREEDOM);
        swipeStack.setLayoutManager(cardStackLayoutManager);
        swipeStack.setAdapter(recycleAdapter);

        CrudTaskRunnable.Action action =
                ((CrudTaskRunnable.Action) view.getArguments().getSerializable(MSG));

        new Thread(
                new CrudTaskRunnable(action == null ?
                        CrudTaskRunnable.Action.SELECT_ALL_IN_WORK :
                        action)
        ).start();
    }

    @Override
    public void onItemClick(Task task) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(TaskEditorContract.MSG, task);
        presenterEventBus.publish(PresenterEventBus.EventType.SELECTED_TASK, bundle);
    }

    public View.OnClickListener btAddOnClickListener() {
        return v ->
                presenterEventBus.publish(PresenterEventBus.EventType.CREATED_TASK, null);
    }

    @Override
    public View.OnClickListener btDeleteOnClickListener() {
        return v -> {
            SwipeAnimationSetting setting = new SwipeAnimationSetting.Builder()
                    .setDirection(Direction.Left)
                    .setDuration(Duration.Normal.duration)
                    .setInterpolator(new AccelerateInterpolator())
                    .build();

            ((CardStackLayoutManager) Objects.requireNonNull(swipeStack.getLayoutManager()))
                    .setSwipeAnimationSetting(setting);
            swipeStack.swipe();
        };
    }

    @Override
    public View.OnClickListener btCompleteOnClickListener() {
        return v -> {
            SwipeAnimationSetting setting = new SwipeAnimationSetting.Builder()
                    .setDirection(Direction.Right)
                    .setDuration(Duration.Normal.duration)
                    .setInterpolator(new AccelerateInterpolator())
                    .build();

            ((CardStackLayoutManager) Objects.requireNonNull(swipeStack.getLayoutManager()))
                    .setSwipeAnimationSetting(setting);
            swipeStack.swipe();
        };
    }
}
