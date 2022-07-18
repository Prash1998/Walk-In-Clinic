package com.example.walkinclinicv01;

import android.widget.TextView;

import androidx.test.annotation.UiThreadTest;
import androidx.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertEquals;



public class ClinicDirectSearchTest {

    @Rule
    public ActivityTestRule<ClinicSearch> myActivityTestRule= new ActivityTestRule<ClinicSearch>(ClinicSearch.class);
    private ClinicSearch myActivity=null;

    private TextView clinicName;
    @Before
    public void setUp() {
        myActivity = myActivityTestRule.getActivity();
    }

    @Test
    @UiThreadTest
    public void searchdirect(){

        clinicName=myActivity.findViewById(R.id.searchClinic);
        clinicName.setText("Clinic1");

        String check=clinicName.getText().toString();



        assertEquals("Clinic1",check);

    }





}
