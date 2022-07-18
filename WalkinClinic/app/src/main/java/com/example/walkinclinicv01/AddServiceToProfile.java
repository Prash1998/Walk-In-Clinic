package com.example.walkinclinicv01;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;



public class AddServiceToProfile extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    private DatabaseReference myRef;
    EditText AddService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_service_to_profile);

        findViewById(R.id.createBtn).setOnClickListener(this);
        findViewById(R.id.cancelBtn).setOnClickListener(this);
        AddService = (EditText) findViewById(R.id.serviceName);


        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        myRef = FirebaseDatabase.getInstance().getReference();
    }

    protected void addToProfile(){

        String clinicService = AddService.getText().toString().trim();
        String userID = mUser.getUid();

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.child("Services").hasChild(clinicService)){
                    String role = snapshot.child("Services").child(clinicService).child("roleName").getValue(String.class);
                    myRef.child("Clinics").child(userID).child("Services Offered").child(clinicService).setValue(role);
                    Toast.makeText(AddServiceToProfile.this, "Service added to Clinic", Toast.LENGTH_LONG).show();
                    AddService.setText("");
                    startActivity(new Intent(AddServiceToProfile.this,ClinicServicesScreen.class));
                }

                else{

                    //if service does not exist
                    Toast.makeText(AddServiceToProfile.this, "Service cannot be added as it does not exist", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(AddServiceToProfile.this,AddServiceToProfile.class));
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


    public void onClick(View v){
        switch(v.getId()){
            case R.id.createBtn:
                addToProfile();
                break;
            case R.id.cancelBtn:
                startActivity(new Intent(this,ClinicServicesScreen.class));
                break;

        }
    }


}
