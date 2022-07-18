package com.example.walkinclinicv01;

import android.widget.TextView;

import androidx.test.annotation.UiThreadTest;
import androidx.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class AddServiceTest {
    @Rule
    public ActivityTestRule<AddService> myActivityTestRule= new ActivityTestRule<AddService>(AddService.class);
    private AddService addService = null;
    private TextView roleName, serviceName;

    @Before
    public void setUp(){
        addService = myActivityTestRule.getActivity();
    }

    @Test
    @UiThreadTest
    public void addService(){
        roleName = addService.findViewById(R.id.roleName);
        serviceName = addService.findViewById(R.id.serviceName);

        roleName.setText("Employee");
        serviceName.setText("Healing");

        addService.runOnUiThread(new Runnable() {

            @Override
            public void run() {

                // Stuff that updates the UI
                addService.findViewById(R.id.createBtn).performClick();

            }
        });

    }

    @Test
    @UiThreadTest
    public void addServiceFail(){
        roleName = addService.findViewById(R.id.roleName);
        serviceName = addService.findViewById(R.id.serviceName);

        roleName.setText("Employee");
        serviceName.setText("");

        addService.runOnUiThread(new Runnable() {

            @Override
            public void run() {

                // Stuff that updates the UI
                addService.findViewById(R.id.createBtn).performClick();

            }
        });
    }

    @Test
    @UiThreadTest
    public void cancelAddService(){
        addService.runOnUiThread(new Runnable() {

            @Override
            public void run() {

                // Stuff that updates the UI
                addService.findViewById(R.id.cancelBtn).performClick();

            }
        });

    }
}
