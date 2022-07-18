package com.example.walkinclinicv01;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.firebase.client.ChildEventListener;
import com.firebase.client.Firebase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ListOfClinics extends AppCompatActivity {

    ListView list;
    FirebaseDatabase database;
    DatabaseReference ref;
    ArrayList<String> listOfClinics;
    ArrayAdapter<String> adapter;
    //Clinic clinic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_clinics);

        //clinic = new Clinic();
        listOfClinics = new ArrayList<>();

        list = (ListView) findViewById(R.id.list);
        database = FirebaseDatabase.getInstance();
        ref = database.getReference().child("Clinics");

        adapter = new ArrayAdapter<String>(this, R.layout.activity_clinics_adapter, R.id.clinicName, listOfClinics);

        ref.addValueEventListener(new ValueEventListener() {
            @NonNull
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                //clinic = new Clinic();

                for (DataSnapshot ds : dataSnapshot.getChildren()){
                    Info info = ds.child("Info").getValue(Info.class);
                    listOfClinics.add(info.getName());

                }
                list.setAdapter(adapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {

                Intent intent = new Intent(getApplicationContext(), PatientClinic.class);
                //intent.putExtra("position",position);
                //intent.putExtra("name",listOfClinics.get(position));
                startActivity(intent);
            }
        });



    }
}


/*Please note: the below commented out code was our attempt at searching by Hours and Services. It has been commented out so that the list of clinics can still be shown. */

/*  DatabaseReference mRef;
    ArrayList<Clinic> listOfClinics;
    ArrayList<String> clinicsName;
    String service, day, open, close;
    String uid;
    //Clinic clinic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_clinics);

        listOfClinics = new ArrayList<Clinic>();
        clinicsName = new ArrayList<String>();

        list = (ListView) findViewById(R.id.list);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            try {
                service = extras.getString("Services Offered").trim();
                searchService(service);

                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>( this, android.R.layout.simple_list_item_1, clinicsName);

                list.setAdapter(arrayAdapter);
                list.setOnItemClickListener(this);

            } catch (Exception e) {
                day = extras.getString("Day").trim();
                open = extras.getString("Open");
                close = extras.getString("Close");
                searchHour();
            }
        } else {
            service = "";
            day = "";
            open = "";
            close = "";
        }
    }

    public void searchService(String service){
        mRef = FirebaseDatabase.getInstance().getReference("Clinics");
        mRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                int count = 1;
                for (DataSnapshot ds : dataSnapshot.getChildren()){
                    Log.d("name99", String.valueOf(count));
                    count++;

                        Info info = ds.child("Info").getValue(Info.class);
                        uid  = info.getUserID();

                        boolean valid = ds.child("Clinics").child(uid).child("Services Offered").hasChild(service);

                        Log.d("name99", service);
                        Log.d("name99", String.valueOf(valid));

                        // check if the name of the service entered is the same as the one offered in the servies offered.
                        if (valid) {
                            Log.d("name99", "done2");
                            listOfClinics.add(dataSnapshot.getValue(Clinic.class));
                            clinicsName.add(info.getName());
                        }else{

                        }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void searchHour(){

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent i = new Intent(ListOfClinics.this,PatientClinic.class);
        Clinic select = listOfClinics.get(position);
        uid = select.getInfo().getUserID();
        i.putExtra("ID", uid );
        startActivity(i);
    }
}*/
