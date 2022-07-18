package com.example.walkinclinicv01;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
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

public class ClinicServicesScreen extends AppCompatActivity implements View.OnClickListener{

    FirebaseAuth mAuth;
    FirebaseUser mUser;
    DatabaseReference myRef;
    TextView ClinicNameTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clinic_services_screen);

        ClinicNameTextView = (TextView)findViewById(R.id.clinicName);
        findViewById(R.id.addBtn).setOnClickListener(this);
        findViewById(R.id.deleteBtn).setOnClickListener(this);
        //findViewById(R.id.removeUser).setOnClickListener(this);
        findViewById(R.id.clinicHoursBtn).setOnClickListener(this);
        findViewById(R.id.employeeHours).setOnClickListener(this);
        findViewById(R.id.logout).setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        myRef = FirebaseDatabase.getInstance().getReference();

        myRef.child("Clinics").child(mUser.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Info info = dataSnapshot.child("Info").getValue(Info.class);

                System.out.println(info.getName());
                ClinicNameTextView.setText(info.getName());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    @Override
    public void onClick(View v){
        switch(v.getId()){
            case R.id.logout:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(ClinicServicesScreen.this,MainActivity.class));
                break;
            case R.id.addBtn:
                startActivity(new Intent(ClinicServicesScreen.this,AddServiceToProfile.class));
                break;
            case R.id.deleteBtn:
                startActivity(new Intent(ClinicServicesScreen.this,DeleteServiceFromProfile.class));
                break;
            case R.id.clinicHoursBtn:
                startActivity(new Intent(ClinicServicesScreen.this,ClinicHours.class));
                break;
            case R.id.employeeHours:
                startActivity(new Intent(ClinicServicesScreen.this,ListOfHours.class));
                break;
        }

    }
}
