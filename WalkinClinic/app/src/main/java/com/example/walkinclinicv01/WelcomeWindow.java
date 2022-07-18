package com.example.walkinclinicv01;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class WelcomeWindow extends AppCompatActivity implements View.OnClickListener{
    FirebaseAuth mAuth;
    FirebaseUser mUser;
    DatabaseReference mDatabase;

    TextView firstNameTextView;
    //TextView roleTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_window);
        findViewById(R.id.SignOut).setOnClickListener(this);

        firstNameTextView = (TextView) findViewById(R.id.textFirstName);
        //roleTextView = (TextView) findViewById(R.id.textRole);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        mDatabase.child("users").child(mUser.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Person person = dataSnapshot.getValue(Person.class);

                firstNameTextView.setText(person.getFirstName());
                //roleTextView.setText(person.getRole());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


    //Adapted to allow for patient functionality
    @Override
    public void onClick(View v){
        switch(v.getId()){
            case R.id.searchBtn:
                startActivity(new Intent(WelcomeWindow.this,ClinicSearch.class));
                break;

            case R.id.SignOut:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(WelcomeWindow.this,MainActivity.class));
                break;
        }
    }

}