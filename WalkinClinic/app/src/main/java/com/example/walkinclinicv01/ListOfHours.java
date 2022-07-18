package com.example.walkinclinicv01;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

public class ListOfHours extends AppCompatActivity {

    FirebaseAuth mAuth;
    FirebaseUser mUser;
    DatabaseReference mDatabase;
    TextView openTime1, openTime2,openTime3,openTime4,openTime5,openTime6,openTime7,
        closeTime1, closeTime2,closeTime3,closeTime4,closeTime5,closeTime6,closeTime7;
    Button userCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_hours);

        String[] Open = new String[7];
        String[] Close = new String[7];

        userCancel = findViewById(R.id.cancelBtn5);

        openTime1 = (TextView) findViewById(R.id.myStartTime1);
        openTime2 = (TextView) findViewById(R.id.myStartTime2);
        openTime3 = (TextView) findViewById(R.id.myStartTime3);
        openTime4 = (TextView) findViewById(R.id.myStartTime4);
        openTime5 = (TextView) findViewById(R.id.myStartTime5);
        openTime6 = (TextView) findViewById(R.id.myStartTime6);
        openTime7 = (TextView) findViewById(R.id.myStartTime7);
        closeTime1 = (TextView) findViewById(R.id.myEndTime1);
        closeTime2 = (TextView) findViewById(R.id.myEndTime2);
        closeTime3 = (TextView) findViewById(R.id.myEndTime3);
        closeTime4 = (TextView) findViewById(R.id.myEndTime4);
        closeTime5 = (TextView) findViewById(R.id.myEndTime5);
        closeTime6 = (TextView) findViewById(R.id.myEndTime6);
        closeTime7 = (TextView) findViewById(R.id.myEndTime7);


        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        mDatabase.child("Clinics").child(mUser.getUid()).child("Hours").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                WeeklySchedule weeklySchedule = dataSnapshot.getValue(WeeklySchedule.class);

                Hours mon = weeklySchedule.getMon();
                Hours tue = weeklySchedule.getTue();
                Hours wed = weeklySchedule.getWed();
                Hours thur = weeklySchedule.getThur();
                Hours fri = weeklySchedule.getFri();
                Hours sat = weeklySchedule.getSat();
                Hours sun = weeklySchedule.getSun();

                Open[0] = mon.getOpen();
                Open[1] = tue.getOpen();
                Open[2]= wed.getOpen();
                Open[3] = thur.getOpen();
                Open[4] = fri.getOpen();
                Open[5] = sat.getOpen();
                Open[6] = sun.getOpen();

                Close[0] = mon.getClosed();
                Close[1] = tue.getClosed();
                Close[2] = wed.getClosed();
                Close[3] = thur.getClosed();
                Close[4] = fri.getClosed();
                Close[5] = sat.getClosed();
                Close[6] = sun.getClosed();


                for (int i = 0; i < 7; i++){
                    if (Open[i].isEmpty() || Close[i].isEmpty()){
                        Open[i] = "close";
                        Close[i] = "close";
                    }
                }

                openTime1.setText(Open[0]);
                openTime2.setText(Open[1]);
                openTime3.setText(Open[2]);
                openTime4.setText(Open[3]);
                openTime5.setText(Open[4]);
                openTime6.setText(Open[5]);
                openTime7.setText(Open[6]);

                closeTime1.setText(Close[0]);
                closeTime2.setText(Close[1]);
                closeTime3.setText(Close[2]);
                closeTime4.setText(Close[3]);
                closeTime5.setText(Close[4]);
                closeTime6.setText(Close[5]);
                closeTime7.setText(Close[6]);

                //roleTextView.setText(person.getRole());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        userCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ListOfHours.this, ClinicServicesScreen.class));
            }
        });

    }
}
