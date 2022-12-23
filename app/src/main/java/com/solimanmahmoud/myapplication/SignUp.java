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
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Pattern;

public class SignUp extends AppCompatActivity {

    EditText fname_txt, lname_txt, email_txt, pass_txt, confirm_pass, phone_txt;
    TextView login;
    Button register_btn;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        mAuth = FirebaseAuth.getInstance();

        fname_txt = findViewById(R.id.fname_txt);
        lname_txt = findViewById(R.id.lname_txt);
        email_txt = findViewById(R.id.email_txt);
        pass_txt = findViewById(R.id.pass_txt);
        confirm_pass = findViewById(R.id.confirm_pass);
        phone_txt = findViewById(R.id.phone_txt);
        register_btn = findViewById(R.id.register_btn);
        login = findViewById(R.id.switch_login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignUp.this,Login.class));
                finish();
            }
        });

        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String firstname = fname_txt.getText().toString().trim();
                String lastname = lname_txt.getText().toString().trim();
                String email = email_txt.getText().toString().trim();
                String pass = pass_txt.getText().toString().trim();
                String Cpass = confirm_pass.getText().toString().trim();
                String phone = phone_txt.getText().toString().trim();

                if (firstname.isEmpty()) {
                    fname_txt.setError("First name is required!");
                    fname_txt.requestFocus();
                    return;
                }

                if (lastname.isEmpty()) {
                    lname_txt.setError("Last name is required!");
                    lname_txt.requestFocus();
                    return;
                }

                if (email.isEmpty()) {
                    email_txt.setError("Email is required!");
                    email_txt.requestFocus();
                    return;
                }

                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    email_txt.setError("Please provide valid email!");
                    email_txt.requestFocus();
                    return;
                }

                if (pass.isEmpty()) {
                    pass_txt.setError("Password name is required!");
                    pass_txt.requestFocus();
                    return;
                }

                if (pass.length() < 8) {
                    pass_txt.setError("Min password length should be 8 characters!");
                    pass_txt.requestFocus();
                    return;
                }

                if (Cpass.isEmpty()) {
                    confirm_pass.setError("Confirm password is required!");
                    confirm_pass.requestFocus();
                    return;
                }

                if (!pass.equals(Cpass)) {
                    Toast.makeText(SignUp.this, "Password confirm", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (phone.isEmpty()) {
                    phone_txt.setError("Phone number is required!");
                    phone_txt.requestFocus();
                    return;
                }

                mAuth.createUserWithEmailAndPassword(email, pass)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Userdp userdp = new Userdp(firstname, lastname, email, phone);
                                    FirebaseDatabase.getInstance().getReference("Users")
                                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                            .setValue(userdp).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                Toast.makeText(SignUp.this, "user has been registerd successfully!", Toast.LENGTH_LONG).show();
                                            } else {
                                                Toast.makeText(SignUp.this, "failed to register! try again!", Toast.LENGTH_LONG).show();
                                            }
                                        }
                                    });
                                } else {
                                    Toast.makeText(SignUp.this, "failed to register! try again!", Toast.LENGTH_LONG).show();
                                }
                            }
                        });

            }
        });

    }
}