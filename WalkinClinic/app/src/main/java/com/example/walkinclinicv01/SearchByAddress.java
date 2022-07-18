package com.example.walkinclinicv01;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.Query;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;


public class SearchByAddress extends AppCompatActivity implements View.OnClickListener{

    EditText search;
    private DatabaseReference mRef;
    String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_by_address);

        findViewById(R.id.cancelBtn7);
        findViewById(R.id.searchBtn);

        search = (EditText) findViewById(R.id.searchAddress);

    }

    private void searchAddress(){
        mRef = FirebaseDatabase.getInstance().getReference("Clinics");
        String addressSearch = search.getText().toString().trim();
        mRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds: dataSnapshot.getChildren()){
                    Info info = ds.child("Info").getValue(Info.class);
                    System.out.println("Clinic Name =" +info.getName());
                    if(info.getAddress().equals(addressSearch)){
                        uid = info.getUserID();
                        //Log.d("name99", uid);
                        startTheActivity(uid);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });}

    private void startTheActivity(String uid){
        System.out.println("USERID = "+uid);
        if(uid == null){
            uid = "sVE1b39QhjdKjrPtde1WuX2sYKM2";
        }
        System.out.println("USERID = "+uid);
        Intent i = new Intent(SearchByAddress.this,PatientClinic.class);
        i.putExtra("ID",uid);
        startActivity(i);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cancelBtn7:
                startActivity(new Intent(SearchByAddress.this, ClinicSearch.class));
                break;

            case R.id.searchBtn:
                searchAddress();
                break;
        }
    }
}
