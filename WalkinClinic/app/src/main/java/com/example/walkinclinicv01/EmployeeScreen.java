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

public class EmployeeScreen extends AppCompatActivity implements View.OnClickListener {

    //Added to display employee name on EmployeeScreen (same as in WelcomeWindow)
    FirebaseAuth mAuth;
    FirebaseUser mUser;
    DatabaseReference mDatabase;
    TextView firstNameTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_screen);
        findViewById(R.id.createClinicBtn).setOnClickListener(this);
        findViewById(R.id.signOut2).setOnClickListener(this);
        firstNameTextView = (TextView) findViewById(R.id.textFirstName);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        mDatabase.child("users").child(mUser.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Person person = dataSnapshot.getValue(Person.class);
                firstNameTextView.setText(person.getFirstName());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onClick(View v){
        switch(v.getId()){
            case R.id.createClinicBtn:
                startActivity(new Intent(EmployeeScreen.this,ProfileInformation.class));
                break;

            case R.id.signOut2:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(EmployeeScreen.this,MainActivity.class));
                break;
        }
    }
}
