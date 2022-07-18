package com.example.walkinclinicv01;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class ClinicSearch extends AppCompatActivity implements View.OnClickListener{

    EditText editTextClinicName;
    DatabaseReference myRef;
    String uid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clinic_search);
        findViewById(R.id.searchAddressBtn).setOnClickListener(this);
        findViewById(R.id.searchHoursBtn).setOnClickListener(this);
        findViewById(R.id.searchServiceBtn).setOnClickListener(this);
        findViewById(R.id.searchDirectlyBtn).setOnClickListener(this);
        findViewById(R.id.logOut1).setOnClickListener(this);

        editTextClinicName = (EditText) findViewById(R.id.searchClinic);
    }

    private void searchforClinic(){

        myRef = FirebaseDatabase.getInstance().getReference("Clinics");

        String searchClinic = editTextClinicName.getText().toString().trim();
        //String uid;
        System.out.println("Trying to search for: "+searchClinic);
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds: dataSnapshot.getChildren()){
                    Info info = ds.child("Info").getValue(Info.class);
                    Log.d("ClinicName",info.getName());
                    if(info.getName().equals(searchClinic)){
                        uid = info.getUserID();
                        startTheActivity(uid);
                        //break;
                    }
                }
                //startTheActivity(uid);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        //uid = "sVE1b39QhjdKjrPtde1WuX2sYKM2";
        /*System.out.println("USERID = "+uid);
        if(uid == null){
            uid = "sVE1b39QhjdKjrPtde1WuX2sYKM2";
        }
        System.out.println("USERID = "+uid);
        Intent i = new Intent(ClinicSearch.this,PatientClinic.class);
        i.putExtra("ID",uid);
        startActivity(i);*/
        //startActivity(new Intent(ClinicSearch.this,PatientClinic.class));
    }

    private void startTheActivity(String uid){
        Log.d("USERID", uid);
        if(uid == null){
            uid = "sVE1b39QhjdKjrPtde1WuX2sYKM2";
        }
        System.out.println("USERID = "+uid);
        Intent i = new Intent(ClinicSearch.this,PatientClinic.class);
        i.putExtra("ID",uid);
        startActivity(i);
        //startActivity(n
    }
    //Adapted to allow for patient functionality
    @Override
    public void onClick(View v){
        switch(v.getId()){
            case R.id.searchAddressBtn:
                startActivity(new Intent(ClinicSearch.this,SearchByAddress.class));
                break;

            case R.id.searchHoursBtn:
                startActivity(new Intent(ClinicSearch.this,SearchByHours.class));
                break;

            case R.id.searchServiceBtn:
                startActivity(new Intent(ClinicSearch.this,SearchByProvidedServices.class));
                break;
            case R.id.searchDirectlyBtn:
                searchforClinic();
                break;

            case R.id.logOut1:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(ClinicSearch.this,MainActivity.class));
        }
    }
}
