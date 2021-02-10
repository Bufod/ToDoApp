package com.example.todoapp.backstage.tasks_scope.task_list;

import android.view.View;
import android.widget.Toast;

import com.example.todoapp.backstage.tasks_scope.CrudTaskRunnable;
import com.example.todoapp.backstage.tasks_scope.RecycleAdapter;
import com.example.todoapp.models.database.entity.Task;
import com.yuyakaido.android.cardstackview.CardStackView;
import com.yuyakaido.android.cardstackview.Direction;

public abstract class CardStackListener implements com.yuyakaido.android.cardstackview.CardStackListener {
    int positionDisappearedCard;
    @Override
    public void onCardDragging(Direction direction, float ratio) {

    }

    public abstract RecycleAdapter getRecycleAdapter();

    @Override
    public void onCardSwiped(Direction direction) {
        if(Direction.HORIZONTAL.contains(direction)){
            Task task = getRecycleAdapter().getData().get(positionDisappearedCard);
            if (direction == Direction.Left) {
                getRecycleAdapter().getData().remove(positionDisappearedCard);
                new Thread(
                        new CrudTaskRunnable(CrudTaskRunnable.Action.DELETE, task)
                ).start();
            }
            else if (task.status != Task.Status.COMPLETED){
                task.status = Task.Status.COMPLETED;
                getRecycleAdapter().getData().remove(positionDisappearedCard);
                new Thread(
                        new CrudTaskRunnable(CrudTaskRunnable.Action.UPDATE, task)
                ).start();
            }
        }
        if (this.positionDisappearedCard == getRecycleAdapter().getItemCount()-1){
            onLastCardDetected();
        }
    }

    @Override
    public void onCardRewound() {

    }

    @Override
    public void onCardCanceled() {

    }

    public abstract CardStackView getCardStackView();

    @Override
    public void onCardAppeared(View view, int position) {

    }

    public abstract void onLastCardDetected();

    @Override
    public void onCardDisappeared(View view, int position) {
        this.positionDisappearedCard = position;
    }
}
