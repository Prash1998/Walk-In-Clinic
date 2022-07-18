package com.example.walkinclinicv01;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Date;

@RequiresApi(api = Build.VERSION_CODES.O)
public class ClinicHours extends AppCompatActivity implements View.OnClickListener{


    private EditText[] startTimesFields, endTimesFields;
    private String[] startTimes = new String[7];
    private String[] endTimes = new String[7];
    WeeklySchedule workHours;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clinic_hours);
        EditText EditTextMondayStart, EditTextTuesdayStart, EditTextWednesdayStart, EditTextThursdayStart, EditTextFridayStart,
                EditTextSaturdayStart, EditTextSundayStart, EditTextMondayEnd, EditTextTuesdayEnd, EditTextWednesdayEnd,
                EditTextThursdayEnd, EditTextFridayEnd, EditTextSaturdayEnd, EditTextSundayEnd;
        EditTextMondayStart = (EditText) findViewById(R.id.startTime1);
        EditTextMondayEnd= ((EditText) findViewById(R.id.endTime1));
        EditTextTuesdayStart= ((EditText) findViewById(R.id.startTime2)) ;
        EditTextTuesdayEnd= ((EditText) findViewById(R.id.endTime2));
        EditTextWednesdayStart= ((EditText) findViewById(R.id.startTime3)) ;
        EditTextWednesdayEnd= ((EditText) findViewById(R.id.endTime3));
        EditTextThursdayStart= ((EditText) findViewById(R.id.startTime4));
        EditTextThursdayEnd= ((EditText) findViewById(R.id.endTime4));
        EditTextFridayStart= ((EditText) findViewById(R.id.startTime5));
        EditTextFridayEnd= ((EditText) findViewById(R.id.endTime5));
        EditTextSaturdayStart= ((EditText) findViewById(R.id.startTime6));
        EditTextSaturdayEnd= ((EditText) findViewById(R.id.endTime6));
        EditTextSundayStart= ((EditText) findViewById(R.id.startTime7));
        EditTextSundayEnd = ((EditText) findViewById(R.id.endTime7));

        startTimesFields = new EditText[]{EditTextMondayStart, EditTextTuesdayStart, EditTextWednesdayStart, EditTextThursdayStart, EditTextFridayStart
                , EditTextSaturdayStart, EditTextSundayStart};
        endTimesFields = new EditText[]{EditTextMondayEnd, EditTextTuesdayEnd, EditTextWednesdayEnd, EditTextThursdayEnd, EditTextFridayEnd, EditTextSaturdayEnd
        , EditTextSundayEnd};

        findViewById(R.id.cancelBtn4).setOnClickListener(this);
        findViewById(R.id.setBtn).setOnClickListener(this);


    }

    public void addHours(){

        for (int i=0; i<7; i++){
           startTimes[i]=startTimesFields[i].getText().toString().trim();
           endTimes[i]=endTimesFields[i].getText().toString().trim();
        }


        workHours = new WeeklySchedule(new Hours(startTimes[0],endTimes[0]), new Hours(startTimes[1], endTimes[1]), new Hours(startTimes[2], endTimes[2])
                , new Hours(startTimes[3], endTimes[3]), new Hours(startTimes[4],endTimes[4]), new Hours(startTimes[5], endTimes[5]),
                new Hours(startTimes[6],endTimes[6]));

        if (inputChecker() && formatCheckAll() && allPairsCheck()) {
            DatabaseReference myRef;
            FirebaseAuth mAuth;
            FirebaseUser mUser;
            mAuth = FirebaseAuth.getInstance();
            mUser = mAuth.getCurrentUser();
            myRef = FirebaseDatabase.getInstance().getReference();
            myRef.child("Clinics").child(mUser.getUid()).child("Hours").setValue(workHours).addOnCompleteListener(this,
                    task -> {
                            if(task.isSuccessful()){
                                Toast.makeText(this, "Hours added", Toast.LENGTH_LONG).show();
                                startActivity(new Intent(ClinicHours.this,ClinicServicesScreen.class));
                            } else{
                                Toast.makeText(this, "Error: Hours not added", Toast.LENGTH_LONG).show();
                            }
                    });

        }
    }

    public boolean inputChecker(){
        for (int i=0; i<7; i++){
            if(startTimes[i].isEmpty()){
                startTimesFields[i].setError("Start time is required.");
                startTimesFields[i].requestFocus();
                return false;
            }
            if(endTimes[i].isEmpty()){
                endTimesFields[i].setError("End time is required.");
                endTimesFields[i].requestFocus();
                return false;
            }
        }
        return true;
    }

    public boolean formatChecker(String time){
        time = time.trim();
        if (time.toUpperCase().equals("CLOSED")){
            return true;
        }
        if(time.length()!=5){
            return false;
        }
        try {
            LocalTime.parse(time);
        }
        catch(DateTimeParseException e){
            return false;
        }
        return true;
    }

    public boolean pairCheck(String start, String end){
        if(start.toUpperCase().equals("CLOSED")){
            return end.toUpperCase().equals("CLOSED");
        }
        if(end.toUpperCase().equals("CLOSED")){
            return false;
        }

        try{
            SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
            Date date_from = formatter.parse(start);
            Date date_to = formatter.parse(end);
            return date_from.before(date_to);
        }
        catch(Exception e){
            return false;
        }
    }

    public boolean allPairsCheck(){
        for(int i=0; i<7; i++){
            if(!pairCheck(startTimes[i], endTimes[i])){
                startTimesFields[i].setError("Start time has to be before end time");
                startTimesFields[i].requestFocus();
                return false;
            }
        }
        return true;
    }

    public boolean formatCheckAll(){
        for (int i=0; i<7; i++){
            if(!formatChecker(startTimes[i])){
                startTimesFields[i].setError("Time has to be either HH:MM or CLOSED.");
                startTimesFields[i].requestFocus();
                return false;
            }
            if (!formatChecker(endTimes[i])){
                endTimesFields[i].setError("Time has to be either HH:mm or CLOSED.");
                endTimesFields[i].requestFocus();
                return false;
            }
        }
        return true;
    }


    @Override
    public void onClick(View v){

        switch(v.getId()){
            case R.id.setBtn:
                addHours();
                //startActivity(new Intent(ClinicHours.this, ClinicServicesScreen.class));
               break;

            case R.id.cancelBtn4:
                startActivity(new Intent(this, ClinicServicesScreen.class));
                break;
        }
    }
}
