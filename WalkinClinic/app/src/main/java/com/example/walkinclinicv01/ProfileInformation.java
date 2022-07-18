package com.example.walkinclinicv01;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class ProfileInformation extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth;
    private DatabaseReference myRef;

    EditText editTextClinicName,editTextAddress,editTextPhoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_information);

        editTextClinicName = (EditText)findViewById(R.id.clinicName);
        editTextAddress = (EditText)findViewById(R.id.address);
        editTextPhoneNumber = (EditText)findViewById(R.id.phoneNumber);

        findViewById(R.id.createClinicBtn).setOnClickListener(this);
        findViewById(R.id.signOut).setOnClickListener(this);
    }

    private void createClinic() {
        String clinicName = editTextClinicName.getText().toString().trim();
        String address = editTextAddress.getText().toString().trim();
        String phoneNumber = editTextPhoneNumber.getText().toString().trim();

        if(clinicName.isEmpty()){
            editTextClinicName.setError("Clinic Name is Required");
            editTextClinicName.requestFocus();
            return;
        }

        if(address.isEmpty()){
            editTextAddress.setError("Clinic Address is Required");
            editTextAddress.requestFocus();
            return;
        }

        if(phoneNumber.isEmpty()){
            editTextPhoneNumber.setError("Phone Number is Required");
            editTextPhoneNumber.requestFocus();
            return;
        }


        mAuth = FirebaseAuth.getInstance();
        myRef = FirebaseDatabase.getInstance().getReference();
        FirebaseUser user = mAuth.getCurrentUser();

        final Info info = new Info(clinicName,address,phoneNumber,user.getUid());
        //final Clinic clinic = new Clinic(info,user.getUid());

        myRef.child("Clinics").child(user.getUid()).child("Info").setValue(info).addOnCompleteListener(this,
                task -> {
                        if(task.isSuccessful()){
                            startActivity(new Intent(ProfileInformation.this,ClinicServicesScreen.class));

                        }else{
                            Toast.makeText(ProfileInformation.this,"Failed to add info to database!",Toast.LENGTH_LONG).show();
                        }
                });

    }

    @Override
    public void onClick(View v){
        switch(v.getId()){
            case R.id.createClinicBtn:
                createClinic();
                break;

            case R.id.signOut:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(ProfileInformation.this,MainActivity.class));
                break;

        }
    }

}
