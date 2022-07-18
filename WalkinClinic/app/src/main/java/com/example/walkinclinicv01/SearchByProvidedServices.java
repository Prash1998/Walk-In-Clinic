package com.example.walkinclinicv01;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class SearchByProvidedServices extends AppCompatActivity implements View.OnClickListener{


    private DatabaseReference mRef;
    String uid;
    EditText editTextServiceName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_by_provided_services);
        findViewById(R.id.cancelBtn);
        findViewById(R.id.searchBtn);

        editTextServiceName=(EditText)findViewById(R.id.searchProvidedServices);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cancelBtn:
                startActivity(new Intent(SearchByProvidedServices.this, ClinicSearch.class));
                break;

            case R.id.searchBtn:

                String serviceToCheck=editTextServiceName.getText().toString().trim();

                Intent service=new Intent(SearchByProvidedServices.this, ListOfClinics.class);

                service.putExtra("Services Offered", serviceToCheck);

                startActivity(service);

                break;
        }
    }
}
