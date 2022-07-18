package com.example.walkinclinicv01;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DeleteServiceFromProfile extends AppCompatActivity implements OnClickListener {

    private DatabaseReference mRef;
    private FirebaseUser mUser;
    private FirebaseAuth mAuth;
    EditText deleteService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_service_from_profile);
        deleteService = (EditText) findViewById(R.id.serviceName);

        findViewById(R.id.deleteBtn).setOnClickListener(this);
        findViewById(R.id.cancelBtn).setOnClickListener(this);
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

    }

    protected void deleteFromProfile(){

        String clinic = deleteService.getText().toString().trim();
        mRef = FirebaseDatabase.getInstance().getReference();
        String userID = mUser.getUid();

       mRef.addListenerForSingleValueEvent(new ValueEventListener() {

           @Override
           public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if ((dataSnapshot.child("Clinics").child(userID).child("Services Offered").hasChild(clinic))) {
                    mRef.child("Clinics").child(userID).child("Services Offered").child(clinic).setValue(null);
                    Toast.makeText(DeleteServiceFromProfile.this, "Service deleted from clinic", Toast.LENGTH_LONG).show();
                    deleteService.setText("");
                    startActivity(new Intent(DeleteServiceFromProfile.this, ClinicServicesScreen.class));

                } else{

               Toast.makeText(DeleteServiceFromProfile.this, "Invalid Entry, service cannot be deleted", Toast.LENGTH_LONG).show();
               startActivity(new Intent(DeleteServiceFromProfile.this, DeleteServiceFromProfile.class));
           }}

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {}
        }); }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.deleteBtn:
                deleteFromProfile();
                break;
            case R.id.cancelBtn:
                startActivity(new Intent(this,ClinicServicesScreen.class));
                break;

        }
    }

}