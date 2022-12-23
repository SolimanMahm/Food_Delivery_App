package com.solimanmahmoud.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {

    EditText Email, Password;
    Button logIn;
    TextView signUp;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        Email = findViewById(R.id.email_log);
        Password = findViewById(R.id.password_log);
        logIn = findViewById(R.id.login_btn);
        signUp = findViewById(R.id.showSignup);


        mAuth = FirebaseAuth.getInstance();
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this, SignUp.class));
                finish();
            }
        });

        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = Email.getText().toString().trim();
                String password = Password.getText().toString().trim();
                if (email.isEmpty()) {
                    Email.setError("Email is requires!");
                    Email.requestFocus();
                    return;
                }
                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    Email.setError("Please enter a valid email!");
                    Email.requestFocus();
                    return;
                }
                if (password.isEmpty()) {
                    Password.setError("Password is required!");
                    Password.requestFocus();
                    return;
                }
                if (password.length() < 8) {
                    Password.setError("Min password length is 8 characters!");
                    Password.requestFocus();
                    return;
                }
                mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                            startActivity(new Intent(Login.this, SecondActivity.class));
                        } else {
                            Toast.makeText(Login.this, "Failed to login! Please check your credentials", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });

    }
}