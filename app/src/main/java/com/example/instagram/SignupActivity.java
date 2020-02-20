package com.example.instagram;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class SignupActivity extends AppCompatActivity {
    private static final String TAG = "SignupActivity";

    EditText etUsername;
    EditText etPassword;
    EditText etEmail;
    EditText etHandle;

    Button btnSave;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        etUsername=findViewById(R.id.etUsername);
        etPassword=findViewById(R.id.etPassword);
        etEmail=findViewById(R.id.etEmail);
        etHandle=findViewById(R.id.etHandle);
        btnSave = findViewById(R.id.btnSave);

        btnSave.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                String email = etEmail.getText().toString();
                String handle = etHandle.getText().toString();

                signup(username, password, email, handle);
            }
        });

    } //end onCreate
    public void signup(String username, String password, String email, String handle){
        ParseUser user = new ParseUser();

        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);

        user.put("handle", handle);

        user.signUpInBackground(new SignUpCallback() {
            public void done(ParseException e) {
                if (e == null) {
                    // Hooray! Let them use the app now.
                    goMainActivity();
                } else {
                    Log.e(TAG, "Issue with SignUp");
                    e.printStackTrace();
                    return;

                }
            }
        });
    }//end SignUp
    private void goMainActivity() {
        Intent i = new Intent (this, MainActivity.class);
        startActivity(i);
        finish();

    }//end goMainActivity

}
