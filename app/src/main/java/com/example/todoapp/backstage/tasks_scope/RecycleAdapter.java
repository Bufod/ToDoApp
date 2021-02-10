package com.example.todoapp.backstage.tasks_scope;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.todoapp.R;
import com.example.todoapp.backstage.tasks_scope.task_list.RecycleViewItemClickListener;
import com.example.todoapp.models.database.entity.Task;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.ViewHolder> {

    private List<Task> localDataSet;
    private RecycleViewItemClickListener itemClickListener;

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textTv, dataTimeTv, descriptionTv;

        public ViewHolder(View view) {
            super(view);

            textTv = (TextView) view.findViewById(R.id.textTv);
            dataTimeTv = (TextView) view.findViewById(R.id.data_timeTv);
            descriptionTv = (TextView) view.findViewById(R.id.descriptionTv);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (itemClickListener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            itemClickListener.onItemClick(localDataSet.get(position));
                        }
                    }
                }
            });

        }

        public TextView getTextTv() {
            return textTv;
        }

        public TextView getDataTimeTv() {
            return dataTimeTv;
        }

        public TextView getDescriptionTv() {
            return descriptionTv;
        }
    }


    public void setItemClickListener(RecycleViewItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public void setData(List<Task> dataSet){
        localDataSet = dataSet;
    }

    public List<Task> getData() {
        return localDataSet;
    }

    public RecycleAdapter(List<Task> dataSet) {
        localDataSet = dataSet;
    }

    public RecycleAdapter() {
        localDataSet = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        Task task = localDataSet.get(position);
        viewHolder.getTextTv().setText(task.name);
        viewHolder.getDataTimeTv().setText(
                new SimpleDateFormat(
                        "dd.MM.yyyy HH:mm", Locale.getDefault()
                ).format(
                        task.expiryDate
                )
        );
        viewHolder.getDescriptionTv().setText(task.description);
    }

    @Override
    public int getItemCount() {
        return localDataSet.size();
    }

    public void notifyDataChanged(){
        notifyDataSetChanged();
        Collections.sort(
                localDataSet,
                (o1, o2) -> o1.expiryDate.compareTo(o2.expiryDate)
        );
    }
}