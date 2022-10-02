package com.frazmatic.taskmaster.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.core.model.temporal.Temporal;
import com.frazmatic.taskmaster.R;
import com.amplifyframework.datastore.generated.model.Task;
import com.amplifyframework.datastore.generated.model.TaskState;

import java.util.Date;

public class AddTask extends AppCompatActivity {
    public static final String add_tag = "AddTaskActivity";
    private int tasksAdded;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        tasksAdded = 0;
    }

    public void addTask(View view){
        EditText title = findViewById(R.id.textInputTaskTitle);
        EditText description = findViewById(R.id.textInputTaskDescription);
        if(title.getText().length() >= 1){
            String currentDate = com.amazonaws.util.DateUtils.formatISO8601Date(new Date());
            Task newTask = Task.builder()
                    .title(title.getText().toString())
                    .body(description.getText().toString())
                    .state(TaskState.New)
                    .created(new Temporal.DateTime(currentDate))
                    .build();
            Amplify.API.mutate(
                    ModelMutation.create(newTask),
                    success -> {Log.i(add_tag, "Task added via Amplify API"); taskAddSuccess(title, description);},
                    failure -> Log.i(add_tag, "Amplify failed to add new Task:" + failure)
            );
        }
    }

    private void taskAddSuccess(EditText title, EditText description){
        tasksAdded++;
        TextView taskAddedCount = findViewById(R.id.textViewSubmittedCount);
        taskAddedCount.setText("Tasks Submitted: " + tasksAdded);
        Toast.makeText(AddTask.this, "Task Submitted", Toast.LENGTH_SHORT).show();
        title.setText("");
        description.setText("");
    }
}