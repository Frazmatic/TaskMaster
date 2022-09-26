package com.frazmatic.taskmaster.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.frazmatic.taskmaster.R;
import com.frazmatic.taskmaster.activities.TaskDetail;
import com.frazmatic.taskmaster.models.Task;

import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {
    private List<Task> tasks;
    Context callingActivity;

    public TaskAdapter(List<Task> tasks, Context callingActivity) {
        this.tasks = tasks;
        this.callingActivity = callingActivity;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View taskFragment = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_task, parent, false);
        return new TaskViewHolder(taskFragment);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        TextView titleView = holder.itemView.findViewById(R.id.textViewTaskFragTitle);
        String title = tasks.get(position).getTitle();
        titleView.setText((position+1) + ". " + tasks.get(position).getTitle());

        TextView body = holder.itemView.findViewById(R.id.textViewTaskFragBody);
        String taskBody = tasks.get(position).getBody();
        body.setText(taskBody);

        TextView state = holder.itemView.findViewById(R.id.textViewTaskFragState);
        state.setText(tasks.get(position).getState().toString());

        TextView dateCreated = holder.itemView.findViewById(R.id.textViewTaskFragDateCreated);
        dateCreated.setText(tasks.get(position).getCreated().toString());

        holder.itemView.setOnClickListener(view -> {taskDetails(tasks.get(position));});
    }

    private void taskDetails(Task task){
        Intent intent = new Intent(callingActivity, TaskDetail.class);
        intent.putExtra("title", task.getTitle());
        intent.putExtra("body", task.getBody());
        intent.putExtra("date", task.getCreated().toString());
        intent.putExtra("state", task.getState().toString());
        callingActivity.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    public static class TaskViewHolder extends RecyclerView.ViewHolder {
        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
