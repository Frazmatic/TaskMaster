package com.frazmatic.taskmaster.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.frazmatic.taskmaster.R;

public class Settings extends AppCompatActivity {
    private SharedPreferences settings;
    public static final String USERNAME_KEY = "userName";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        settings = PreferenceManager.getDefaultSharedPreferences(this);
        String username = settings.getString(Settings.USERNAME_KEY, "");
        if (!username.isEmpty()){
            EditText usernameText = ((EditText)findViewById(R.id.editTextUserName));
            usernameText.setText(username);
        }
    }

    public void saveSettings(View view){
       SharedPreferences.Editor editor = settings.edit();
       String username = ((EditText)findViewById(R.id.editTextUserName)).getText().toString();
       editor.putString(USERNAME_KEY, username);
       editor.apply();
       Toast.makeText(Settings.this, "Saving Settings", Toast.LENGTH_SHORT).show();
    }
}