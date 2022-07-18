package com.example.walkinclinicv01;

import android.widget.TextView;

import androidx.test.annotation.UiThreadTest;
import androidx.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class AssignHoursTest {

    @Rule
    public ActivityTestRule<ClinicHours> myActivityTestRule= new ActivityTestRule<ClinicHours>(ClinicHours.class);
    private ClinicHours myActivity=null;
    private TextView MondayFrom, MondayUntil, TuesdayFrom,TuesdayUntil, WednesdayFrom, WednesdayUntil, ThursdayFrom, ThursdayUntil, FridayFrom, FridayUntil, SaturdayFrom, SaturdayUntil, SundayFrom, SundayUntil;

    @Before
    public void setUp() {
        myActivity = myActivityTestRule.getActivity();
    }

    @Test
    @UiThreadTest
    public void AssignHours(){

        MondayFrom=myActivity.findViewById(R.id.startTime1);
        MondayUntil=myActivity.findViewById(R.id.endTime1);

        TuesdayFrom=myActivity.findViewById(R.id.startTime2);
        TuesdayUntil=myActivity.findViewById(R.id.endTime2);

        WednesdayFrom=myActivity.findViewById(R.id.startTime3);
        WednesdayUntil=myActivity.findViewById(R.id.endTime3);

        ThursdayFrom=myActivity.findViewById(R.id.startTime4);
        ThursdayUntil=myActivity.findViewById(R.id.endTime4);

        FridayFrom=myActivity.findViewById(R.id.startTime5);
        FridayUntil=myActivity.findViewById(R.id.endTime5);

        SaturdayFrom=myActivity.findViewById(R.id.startTime6);
        SaturdayUntil=myActivity.findViewById(R.id.endTime6);

        SundayFrom=myActivity.findViewById(R.id.startTime7);
        SundayUntil=myActivity.findViewById(R.id.endTime7);

        MondayFrom.setText("09:00");
        MondayUntil.setText("17:00");

        TuesdayFrom.setText("09:00");
        TuesdayUntil.setText("17:00");

        WednesdayFrom.setText("09:00");
        WednesdayUntil.setText("17:00");

        ThursdayFrom.setText("09:00");
        ThursdayUntil.setText("17:00");

        FridayFrom.setText("09:00");
        FridayUntil.setText("17:00");

        SaturdayFrom.setText("09:00");
        SaturdayUntil.setText("17:00");

        SundayFrom.setText("09:00");
        SundayUntil.setText("17:00");


        String check=MondayFrom.getText().toString();
        String check2=MondayUntil.getText().toString();

        String check3=TuesdayFrom.getText().toString();
        String check4=TuesdayUntil.getText().toString();

        String check5=WednesdayFrom.getText().toString();
        String check6=WednesdayUntil.getText().toString();

        String check7=ThursdayFrom.getText().toString();
        String check8=ThursdayUntil.getText().toString();

        String check9=FridayFrom.getText().toString();
        String check10=FridayUntil.getText().toString();

        String check11=SaturdayFrom.getText().toString();
        String check12=SaturdayUntil.getText().toString();

        String check13=SundayFrom.getText().toString();
        String check14=SundayUntil.getText().toString();




        /*



        myActivity.runOnUiThread(new Runnable() {

            @Override
            public void run() {

                // Stuff that updates the UI
                myActivity.findViewById(R.id.setBtn).performClick();

            }
        });
        */

        assertEquals("09:00",check);
        assertEquals("17:00",check2);

        assertEquals("09:00",check3);
        assertEquals("17:00",check4);

        assertEquals("09:00",check5);
        assertEquals("17:00",check6);

        assertEquals("09:00",check7);
        assertEquals("17:00",check8);

        assertEquals("09:00",check9);
        assertEquals("17:00",check10);

        assertEquals("09:00",check11);
        assertEquals("17:00",check12);

        assertEquals("09:00",check13);
        assertEquals("17:00",check14);




    }


}
