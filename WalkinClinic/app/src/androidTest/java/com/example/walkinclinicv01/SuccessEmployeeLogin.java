package com.example.walkinclinicv01;


import android.widget.Button;
import android.widget.TextView;

import androidx.test.annotation.UiThreadTest;
import androidx.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertEquals;



public class SuccessEmployeeLogin {


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

    public void employeelogin() throws Exception {

        text = myActivity.findViewById(R.id.username);
        text.setText("ymemployeetest@gmail.com");

        text2 = myActivity.findViewById(R.id.password);
        text2.setText("test123");

        String check = text.getText().toString();
        String check2 = text2.getText().toString();

        myActivity.runOnUiThread(new Runnable() {

            @Override
            public void run() {

                // Stuff that updates the UI
                myActivity.findViewById(R.id.signIn).performClick();

            }
        });



        assertEquals("ymemployeetest@gmail.com", check);
        assertEquals("test123", check2);

    }



}
