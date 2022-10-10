package com.frazmatic.taskmaster.activities;

import androidx.appcompat.app.AppCompatActivity;

import com.amplifyframework.core.Amplify;
import com.frazmatic.taskmaster.R;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Confirmation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);
        setupConfirmationButton();
    }

    public void confirmUser(String email, String confirmationCode){
        Amplify.Auth.confirmSignUp(
                email,
                confirmationCode,
                success -> {
                    Intent intent = new Intent(this, LogIn.class);
                    startActivity(intent);
                    },
                failure ->  runOnUiThread(() -> {
                    Toast.makeText(Confirmation.this, "Confirmation Code or E-Mail Not Recognized", Toast.LENGTH_LONG).show();
                    finish();
                    startActivity(getIntent());
                })
        );
    }

    public void setupConfirmationButton(){
        EditText email = findViewById(R.id.editTextTextEmailConfirmation);
        EditText confirmationCode = findViewById(R.id.editTextTextConfirmationCode);
        Button confirm = findViewById(R.id.buttonConfirm);
        confirm.setOnClickListener(view -> {
            confirmUser(email.getText().toString(), confirmationCode.getText().toString());
        });
    }
}