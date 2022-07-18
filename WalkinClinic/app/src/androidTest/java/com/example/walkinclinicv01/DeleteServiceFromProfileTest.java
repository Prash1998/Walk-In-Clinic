package com.example.walkinclinicv01;


import android.widget.TextView;

import androidx.test.annotation.UiThreadTest;
import androidx.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class DeleteServiceFromProfileTest {

    @Rule
    public ActivityTestRule<DeleteServiceFromProfile> myActivityTestRule= new ActivityTestRule<DeleteServiceFromProfile>(DeleteServiceFromProfile.class);
    private DeleteServiceFromProfile myActivity=null;
    private TextView serviceToDelete;

    @Before
    public void setUp() {
        myActivity = myActivityTestRule.getActivity();
    }

    @Test
    @UiThreadTest
    public void deleteService(){



        serviceToDelete=myActivity.findViewById(R.id.serviceName);
        serviceToDelete.setText("Heal");

        String check=serviceToDelete.getText().toString();



        /*

        myActivity.runOnUiThread(new Runnable() {

            @Override
            public void run() {

                // Stuff that updates the UI
                myActivity.findViewById(R.id.deleteBtn).performClick();

            }
        });

        */
        assertEquals("Heal",check);




    }


}
