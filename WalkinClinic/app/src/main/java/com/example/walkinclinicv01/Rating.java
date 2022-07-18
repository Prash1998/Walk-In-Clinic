package com.example.walkinclinicv01;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Rating extends AppCompatActivity implements View.OnClickListener{
    FirebaseAuth mAuth;
    FirebaseUser mUser;
    DatabaseReference myRef;

    TextView clinicName;
    EditText ratingEditText;
    String rating;
    String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_rating);
            findViewById(R.id.rateBtn).setOnClickListener(this);
            findViewById(R.id.cancelBtn).setOnClickListener(this);

            clinicName = (TextView)findViewById(R.id.clinicName);
            ratingEditText = (EditText)findViewById(R.id.rating);

            mAuth = FirebaseAuth.getInstance();
            mUser = mAuth.getCurrentUser();
            myRef = FirebaseDatabase.getInstance().getReference();


        Bundle extras = getIntent().getExtras();
        if(extras!=null){
            uid = extras.getString("ID");
            System.out.println(uid);
        }else{
            uid = "";
            System.out.println("null");
        }

        myRef.child("Clinics").child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Info info = dataSnapshot.child("Info").getValue(Info.class);
                System.out.println(info.getName());
                clinicName.setText(info.getName());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




    }


        public void rate(){

            rating = ratingEditText.getText().toString().trim();
            if (rating.isEmpty()){
                ratingEditText.setError("Rating required");
                ratingEditText.requestFocus();
                return;
            }
            double ratingDouble;
            try {
                ratingDouble = Double.parseDouble(rating);
            }
            catch(NumberFormatException e){
                ratingEditText.setError("Rating has to be a number between 0 and 5");
                ratingEditText.requestFocus();
                return;
            }
            if (ratingDouble<0 || ratingDouble>5){
                ratingEditText.setError("Rating has to be between 0 and 5");
                ratingEditText.requestFocus();
                return;
            }
            System.out.println(uid);

            myRef.child("Clinics").child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.hasChild("Feedback")){
                        Feedback feedback = dataSnapshot.child("Feedback").getValue(Feedback.class);
                        double avgRating = feedback.getRating();
                        int numberOfRevs = feedback.getNumberOfReviewers();
                        double newRating = ((avgRating*numberOfRevs) + ratingDouble)/(numberOfRevs + 1);
                        newRating = (double)Math.round(newRating*10d)/10d;
                        Feedback newFeedback =  new Feedback(newRating, numberOfRevs + 1);
                        myRef.child("Clinics").child(uid).child("Feedback").setValue(newFeedback);
                        Intent i = new Intent(Rating.this, PatientClinic.class);
                        i.putExtra("ID",uid);
                        startActivity(i);
                        Toast.makeText(Rating.this, "Submitted! Thank you for your feedback.", Toast.LENGTH_LONG).show();
                    }
                    else{
                        Feedback feedback = new Feedback(ratingDouble,1);
                        myRef.child("Clinics").child(uid).child("Feedback").setValue(feedback);
                        Intent i = new Intent(Rating.this, PatientClinic.class);
                        i.putExtra("ID",uid);
                        startActivity(i);
                        Toast.makeText(Rating.this, "Submitted! Thank you for your feedback.", Toast.LENGTH_LONG).show();
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

        }


        @Override
        public void onClick(View v){
            switch(v.getId()){
                case R.id.rateBtn:
                    rate();
                    break;

                case R.id.cancelBtn:
                    startActivity(new Intent(Rating.this,PatientClinic.class));
                    break;
            }
        }
}
