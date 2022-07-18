package com.example.walkinclinicv01;


import android.widget.TextView;

import androidx.test.annotation.UiThreadTest;
import androidx.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AddServiceToProfileTest {
    @Rule
    public ActivityTestRule<AddServiceToProfile> myActivityTestRule= new ActivityTestRule<AddServiceToProfile>(AddServiceToProfile.class);

    private AddServiceToProfile myActivity=null;

    private TextView serviceName;

    @Before
    public void setUp() {
        myActivity = myActivityTestRule.getActivity();
    }

    @Test
    @UiThreadTest
    public void addService(){

        serviceName=myActivity.findViewById(R.id.serviceName);
        serviceName.setText("Heal");

        String check=serviceName.getText().toString();

        /*

        myActivity.runOnUiThread(new Runnable() {

            @Override
            public void run() {

                // Stuff that updates the UI
                myActivity.findViewById(R.id.createBtn).performClick();

            }
        });

         */

        assertEquals("Heal",check);

    }




}
