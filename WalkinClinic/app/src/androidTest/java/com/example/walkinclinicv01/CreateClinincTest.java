package com.example.walkinclinicv01;


import android.widget.TextView;

import androidx.test.annotation.UiThreadTest;
import androidx.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class CreateClinincTest {

    @Rule
    public ActivityTestRule<ProfileInformation> myActivityTestRule= new ActivityTestRule<ProfileInformation>(ProfileInformation.class);
    private ProfileInformation myActivity=null;
    private TextView clinicName, Address, phonenumber;

    @Before
    public void setUp() {
        myActivity = myActivityTestRule.getActivity();
    }


    @Test
    @UiThreadTest
    public void CreateClinic(){

        clinicName=myActivity.findViewById(R.id.clinicName);
        Address = myActivity.findViewById(R.id.address);
        phonenumber = myActivity.findViewById(R.id.phoneNumber);

        clinicName.setText("Med Clinic");
        Address.setText("Ottawa");
        phonenumber.setText("6132348");


        String check=clinicName.getText().toString();
        String check2=Address.getText().toString();
        String check3=phonenumber.getText().toString();

        /*


        myActivity.runOnUiThread(new Runnable() {

            @Override
            public void run() {

                // Stuff that updates the UI
                myActivity.findViewById(R.id.createClinicBtn).performClick();

            }
        });

        */

        assertEquals("Med Clinic",check);
        assertEquals("Ottawa",check2);
        assertEquals("6132348",check3);


    }
}
