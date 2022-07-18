package com.example.walkinclinicv01;

import android.widget.Button;
import android.widget.TextView;

import androidx.test.annotation.UiThreadTest;
import androidx.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertEquals;




public class FailPatientLoginTest {



    @Rule
    public ActivityTestRule<MainActivity> myActivityTestRule = new ActivityTestRule(MainActivity.class);

    private MainActivity myActivity = null;
    private TextView text;
    private TextView text2;
    private Button login;


    @Before
    public void setUp() {
        myActivity = myActivityTestRule.getActivity();
    }

    @Test
    @UiThreadTest

    public void patientlogin() throws Exception {

        text = myActivity.findViewById(R.id.username);
        text.setText("user000@uottawa.ca");

        text2 = myActivity.findViewById(R.id.password);
        text2.setText("8989898");

        String check = text.getText().toString();
        String check2 = text2.getText().toString();

        myActivity.runOnUiThread(new Runnable() {

            @Override
            public void run() {

                // Stuff that updates the UI
                myActivity.findViewById(R.id.signIn).performClick();

            }
        });




        assertEquals("user000@uottawa.ca", check);
        assertEquals("Kayla123", check2);

    }

}
