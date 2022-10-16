package com.frazmatic.taskmaster.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
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
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.Priority;
import com.google.android.gms.tasks.CancellationToken;
import com.google.android.gms.tasks.OnTokenCanceledListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class AddTask extends AppCompatActivity {
    private int tasksAdded;
    private CompletableFuture<List<Team>> teamsFuture;
    private FusedLocationProviderClient fusedLocationProviderClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        tasksAdded = 0;
        teamsFuture = new CompletableFuture<>();
        completeTeamsFuture();
        setUpTeamSpinner();
        addTask();
        requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 55555);
    }

    public void addTask(){
        Spinner teamSpinner = findViewById(R.id.spinnerAddTaskTeam);
        EditText title = findViewById(R.id.textInputTaskTitle);
        EditText body = findViewById(R.id.textInputTaskDescription);

        findViewById(R.id.buttonAddTask).setOnClickListener(view -> {

            String selectedTeamName = teamSpinner.getSelectedItem().toString();
            ArrayList<Team> teams = getTeamsList();
            Team selectedTeam = teams.stream().filter(t -> t.getName().equals(selectedTeamName)).findAny().orElseThrow(RuntimeException::new);
            getLocationAndAddToDatabase(title.getText().toString(), body.getText().toString(), selectedTeam);
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });
    }

    private void addToDatabase(String title, String body, Team t, Double lat, Double lon){
        if(title.length() >= 1){
            String currentDate = com.amazonaws.util.DateUtils.formatISO8601Date(new Date());
            Task newTask = Task.builder()
                    .title(title)
                    .body(body)
                    .state(TaskState.New)
                    .created(new Temporal.DateTime(currentDate))
                    .team(t)
                    .lat(lat)
                    .lon(lon)
                    .build();
            Amplify.API.mutate(
                    ModelMutation.create(newTask),
                    success -> {},
                    failure -> {}
            );
        }
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

    private void getLocationAndAddToDatabase(String title, String body, Team t){
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getApplicationContext());
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        fusedLocationProviderClient.getCurrentLocation(Priority.PRIORITY_HIGH_ACCURACY, new CancellationToken() {
            @NonNull
            @Override
            public CancellationToken onCanceledRequested(@NonNull OnTokenCanceledListener onTokenCanceledListener) {
                return null;
            }

            @Override
            public boolean isCancellationRequested() {
                return false;
            }
        }).addOnSuccessListener(location -> addToDatabase(title, body, t, location.getLatitude(), location.getLongitude()));
    }


}