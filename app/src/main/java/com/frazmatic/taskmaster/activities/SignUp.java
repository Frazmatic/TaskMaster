package com.frazmatic.taskmaster.activities;

import androidx.appcompat.app.AppCompatActivity;

import com.amplifyframework.auth.AuthUserAttributeKey;
import com.amplifyframework.auth.options.AuthSignUpOptions;
import com.amplifyframework.core.Amplify;
import com.frazmatic.taskmaster.R;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        setupSignUpButton();
    }

    public void createUser(String email, String password){
        Amplify.Auth.signUp(email,
                password,
                AuthSignUpOptions.builder()
                        .userAttribute(AuthUserAttributeKey.email(), email)
                        .build(),
                success -> {
                    Log.i("AuthSignup", success.toString());
                    Intent intent = new Intent(this, Confirmation.class);
                    startActivity(intent);
                    },
                failure -> {
                    runOnUiThread(() -> {
                        Toast.makeText(SignUp.this, "Sign Up Failed", Toast.LENGTH_LONG).show();
                        finish();
                        startActivity(getIntent());
                    });
                }
        );
    }

    public void setupSignUpButton(){
        EditText email = findViewById(R.id.editTextSignUpEmail);
        EditText password = findViewById(R.id.editTextSignUpPassword);
        Button signup = findViewById(R.id.buttonSignUp);
        signup.setOnClickListener(view -> {
            createUser(email.getText().toString(), password.getText().toString());
        });
    }
}