package com.frazmatic.taskmaster.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Team;
import com.frazmatic.taskmaster.R;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class Settings extends AppCompatActivity {
    private SharedPreferences settings;
    public static final String USERNAME_KEY = "userName";
    public static final String TEAMNAME_KEY = "teamName";
    private CompletableFuture<List<Team>> teamsFuture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        settings = PreferenceManager.getDefaultSharedPreferences(this);
        String username = settings.getString(Settings.USERNAME_KEY, "");
        if (!username.isEmpty()){
            EditText usernameText = findViewById(R.id.editTextUserName);
            usernameText.setText(username);
        }
        saveSettings();
        teamsFuture = new CompletableFuture<>();
        completeTeamsFuture();
        setUpTeamSpinner();
    }

    public void saveSettings(){
        findViewById(R.id.buttonSaveSettings).setOnClickListener(view -> {
            SharedPreferences.Editor editor = settings.edit();
            String username = ((EditText)findViewById(R.id.editTextUserName)).getText().toString();
            editor.putString(USERNAME_KEY, username);

            Spinner teamSpinner = findViewById(R.id.spinnerSettingsTeam);
            String selectedTeamName = teamSpinner.getSelectedItem().toString();
            editor.putString(TEAMNAME_KEY, selectedTeamName);
            editor.apply();
            Toast.makeText(Settings.this, "Saving Settings", Toast.LENGTH_SHORT).show();
        });
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

        Spinner teamSpinner = findViewById(R.id.spinnerSettingsTeam);
        runOnUiThread(() -> teamSpinner.setAdapter(new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                names)));
    }
}