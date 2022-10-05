package com.frazmatic.taskmaster.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.core.model.temporal.Temporal;
import com.amplifyframework.datastore.generated.model.Team;
import com.frazmatic.taskmaster.R;
import com.amplifyframework.datastore.generated.model.Task;
import com.amplifyframework.datastore.generated.model.TaskState;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class AddTask extends AppCompatActivity {
    private int tasksAdded;
    private CompletableFuture<List<Team>> teamsFuture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        tasksAdded = 0;
        teamsFuture = new CompletableFuture<>();
        completeTeamsFuture();
        setUpTeamSpinner();
        addTask();
    }

    public void addTask(){
        Spinner teamSpinner = findViewById(R.id.spinnerAddTaskTeam);
        findViewById(R.id.buttonAddTask).setOnClickListener(view -> {

            String selectedTeamName = teamSpinner.getSelectedItem().toString();
            ArrayList<Team> teams = getTeamsList();
            Team selectedTrainer = teams.stream().filter(t -> t.getName().equals(selectedTeamName)).findAny().orElseThrow(RuntimeException::new);

            EditText title = findViewById(R.id.textInputTaskTitle);
            EditText description = findViewById(R.id.textInputTaskDescription);
            if(title.getText().length() >= 1){
                String currentDate = com.amazonaws.util.DateUtils.formatISO8601Date(new Date());
                Task newTask = Task.builder()
                        .title(title.getText().toString())
                        .body(description.getText().toString())
                        .state(TaskState.New)
                        .created(new Temporal.DateTime(currentDate))
                        .team(selectedTrainer)
                        .build();
                Amplify.API.mutate(
                        ModelMutation.create(newTask),
                        success -> taskAddSuccess(title, description),
                        failure -> {}
                );
            }
        });
    }

    private void taskAddSuccess(EditText title, EditText description){
        tasksAdded++;
        TextView taskAddedCount = findViewById(R.id.textViewSubmittedCount);
        String taskCountMessage = "Tasks Submitted: " + tasksAdded;
        taskAddedCount.setText(taskCountMessage);
        Toast.makeText(AddTask.this, "Task Submitted", Toast.LENGTH_SHORT).show();
        title.setText("");
        description.setText("");
    }

    private void completeTeamsFuture(){
        Amplify.API.query(
            ModelQuery.list(Team.class),
            success -> {
                ArrayList<Team> teams = new ArrayList<>();
                for (Team t : success.getData()){
                    teams.add(t);
                }
                teamsFuture.complete(teams);
            },
            failure -> teamsFuture.complete(null)
        );
    }

    private ArrayList<Team> getTeamsList(){
        ArrayList<Team> teams = new ArrayList<>();
        try {
            teams = (ArrayList<Team>)teamsFuture.get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        return teams;
    }

    private void setUpTeamSpinner(){
        ArrayList<String> names = new ArrayList<>();
        for(Team t : getTeamsList()){
            names.add(t.getName());
        }

        Spinner teamSpinner = findViewById(R.id.spinnerAddTaskTeam);
        runOnUiThread(() -> teamSpinner.setAdapter(new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                names)));
    }
}