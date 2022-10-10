package com.frazmatic.taskmaster.activities;

import androidx.appcompat.app.AppCompatActivity;

import com.amplifyframework.core.Amplify;
import com.frazmatic.taskmaster.R;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LogIn extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        setupSignInButton();
        setupSignUpButton();
    }

    public void signInUser(String email, String password){
        Amplify.Auth.signIn(email,
                password,
                success -> {
                    Intent intent = new Intent(this, MainActivity.class);
                    startActivity(intent);
                },
                failure -> {
                    runOnUiThread(() -> {
                        Toast.makeText(LogIn.this, "User Not Recognized", Toast.LENGTH_LONG).show();
                        finish();
                        startActivity(getIntent());
                    });
                }
        );

    }

    public void setupSignInButton(){
        Button b = findViewById(R.id.buttonSignIn);
        b.setOnClickListener(view -> {
            EditText email = findViewById(R.id.editTextTextEmailAddress);
            EditText password = findViewById(R.id.editTextTextPassword);
            signInUser(email.getText().toString(), password.getText().toString());
        });
    }

    public void setupSignUpButton(){
        Button b = findViewById(R.id.buttonSignUpPage);
        b.setOnClickListener(view -> {
            Intent intent = new Intent(this, SignUp.class);
            startActivity(intent);
        });
    }
}