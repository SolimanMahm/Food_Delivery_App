package com.solimanmahmoud.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Profile extends AppCompatActivity {

    Button logut;
    TextView firstname, lastname, email, phone;
    private FirebaseUser user;
    private DatabaseReference reference;

    private String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        logut = findViewById(R.id.logout_btn);
        firstname = findViewById(R.id.fname_edt);
        lastname = findViewById(R.id.lname_edt);
        email = findViewById(R.id.email_edt);
        phone = findViewById(R.id.phone_edt);

        logut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(Profile.this, Login.class));
            }
        });

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        userID = user.getUid();

        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Userdp userProfile = snapshot.getValue(Userdp.class);
                if (userProfile != null) {
                    String firstName = userProfile.firstname;
                    String lasttName = userProfile.lastname;
                    String Email = userProfile.email;
                    String Phone = userProfile.phoneNum;

                    firstname.setText(firstName);
                    lastname.setText(lasttName);
                    email.setText(Email);
                    phone.setText(Phone);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Profile.this, "Something wrong happened!", Toast.LENGTH_LONG).show();
            }
        });
    }
}