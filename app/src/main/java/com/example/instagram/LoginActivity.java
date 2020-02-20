package com.example.instagram;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";

    EditText etUsername;
    EditText etPassword;
    Button btnLogin;
    Button btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if(ParseUser.getCurrentUser() != null){
            Toast.makeText(this, "Logged in.", Toast.LENGTH_SHORT).show();
            goMainActivity();
        }

        etUsername = findViewById(R.id.etUsername);
        etPassword =findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnSignUp = findViewById(R.id.btnSignUp);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                login(username, password);

            }
        });
        btnSignUp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                signup();
            }
        });
    }
    private void login(String username, String password){
        ParseUser.logInInBackground(username, password, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if (e != null) {
                    Log.e(TAG, "Issue with Login");
                    e.printStackTrace();
                    return;
                }
                //TODO: navigate to new activity if the user has signed properly
                goMainActivity();
            }
        });
    }
    private void signup(){
        Intent i = new Intent(this, SignupActivity.class);
        startActivity(i);

    }

    private void goMainActivity() {
        Intent i = new Intent (this, MainActivity.class);
        startActivity(i);
        finish();

    }

}
