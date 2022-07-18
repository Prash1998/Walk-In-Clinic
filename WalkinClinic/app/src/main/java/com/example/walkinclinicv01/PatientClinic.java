package com.example.walkinclinicv01;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PatientClinic extends AppCompatActivity implements View.OnClickListener{

    FirebaseAuth mAuth;
    FirebaseUser mUser;
    DatabaseReference myInitialRef;
    DatabaseReference myRef;
    DatabaseReference myRef1;
    DatabaseReference myRef2;
    TextView clinicName;
    TextView waitTimeHoursTextView;
    TextView waitTimeMinsTextView;
    TextView rating;
    String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_clinic);
        clinicName = (TextView)findViewById(R.id.clinicName);
        waitTimeHoursTextView = (TextView)findViewById(R.id.waitTimeInHours);
        waitTimeMinsTextView = (TextView)findViewById(R.id.waitTimeInMins);
        rating = (TextView) findViewById(R.id.rating);
        findViewById(R.id.checkInBtn).setOnClickListener(this);
        findViewById(R.id.bookBtn).setOnClickListener(this);
        findViewById(R.id.rateBtn).setOnClickListener(this);
        findViewById(R.id.goBackBtn).setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        myRef = FirebaseDatabase.getInstance().getReference();



        Bundle extras = getIntent().getExtras();
        if(extras!=null){
            uid = extras.getString("ID");
        }else{
            uid = "";
        }
        /*
        myInitialRef = FirebaseDatabase.getInstance().getReference();
        myInitialRef.child("Clinics").child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });*/

        System.out.println(uid+"PC");
        myRef.child("Clinics").child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Info info = dataSnapshot.child("Info").getValue(Info.class);
                System.out.println("Attempt");
                System.out.println(info.getName());
                System.out.println("Passed");
                clinicName.setText(info.getName());

                if (dataSnapshot.hasChild("Feedback")) {
                    Feedback feedback = dataSnapshot.child("Feedback").getValue(Feedback.class);
                    String ratingString = Double.toString(feedback.getRating());
                    String num = Integer.toString(feedback.getNumberOfReviewers());
                    String display = ratingString + "(" + num + ")";
                    rating.setText(display);
                }
                else{
                    rating.setText("5(0)");
                }

                if(dataSnapshot.hasChild("WaitTimes")){
                    WaitTimes waitTime = dataSnapshot.child("WaitTimes").getValue(WaitTimes.class);
                    updateWaitingTimes(waitTime);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
    private void startTheActivity(){
        Toast.makeText(PatientClinic.this, "Checked in!", Toast.LENGTH_LONG).show();
        startActivity(new Intent(PatientClinic.this,ClinicSearch.class));
    }

    private void checkingIn(){
        myRef1 = FirebaseDatabase.getInstance().getReference();
        myRef1.child("Clinics").child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(!dataSnapshot.hasChild("WaitTimes")){      //Create new instance of WaitTimes
                    WaitTimes waitTime = new WaitTimes(1,15,0,15);
                    //waitTime.getHoursandMins();
                    myRef1.child("Clinics").child(uid).child("WaitTimes").setValue(waitTime).addOnCompleteListener(PatientClinic.this,
                            task -> {
                                    if(task.isSuccessful()){
                                        //System.out.println(waitTime.getWaitTimeTotal());
                                        updateWaitingTimes(waitTime);
                                        startTheActivity();
                                        //startActivity(new Intent(PatientClinic.this,ClinicSearch.class));
                                        //waitTimeTextView.setText(waitTime.getWaitTimeTotal());
                                    } else{
                                        System.out.println("Not successful! ");
                                    }
                            });
                } else{
                    myRef2 = FirebaseDatabase.getInstance().getReference();
                    WaitTimes waitTime = dataSnapshot.child("WaitTimes").getValue(WaitTimes.class);
                    waitTime.increaseNumPeople();

                    WaitTimes updatedWaitTimes = new WaitTimes(waitTime.getNumPeopleWaiting(),waitTime.getWaitTimeTotal(),waitTime.getWaitTimeHours(),waitTime.getWaitTimeMins());
                    myRef2.child("Clinics").child(uid).child("WaitTimes").setValue(updatedWaitTimes).addOnCompleteListener(PatientClinic.this,
                            task1 -> {
                                if(task1.isSuccessful()){
                                    updateWaitingTimes(updatedWaitTimes);
                                    startTheActivity();
                                    //System.out.println(updatedWaitTimes.getWaitTimeTotal());
                                    //startActivity(new Intent(PatientClinic.this,ClinicSearch.class));
                                }
                            });
                    //System.out.println(waitTime.getWaitTimeTotal());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void updateWaitingTimes(WaitTimes waitTimes){
        waitTimeHoursTextView.setText(String.valueOf(waitTimes.getWaitTimeHours()));
        waitTimeMinsTextView.setText(String.valueOf(waitTimes.getWaitTimeMins()));
    }

    @Override
    public void onClick(View v){
        switch(v.getId()){
            case R.id.checkInBtn:
                checkingIn();
                break;
            case R.id.bookBtn:
                break;
            case R.id.rateBtn:
                Intent i = new Intent(PatientClinic.this, Rating.class);
                i.putExtra("ID",uid);
                startActivity(i);
                break;
            case R.id.goBackBtn:
                startActivity(new Intent(PatientClinic.this,ClinicSearch.class));
                break;
        }
    }
}
