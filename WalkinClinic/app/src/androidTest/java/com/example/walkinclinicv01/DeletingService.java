package com.example.walkinclinicv01;

import android.widget.TextView;

import androidx.test.annotation.UiThreadTest;
import androidx.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class DeletingService {
    @Rule
    public ActivityTestRule<DeleteService> myActivityTestRule= new ActivityTestRule<DeleteService>(DeleteService.class);
    private DeleteService deleteService = null;
    private TextView serviceNameToDelete;

    @Before
    public void setUp(){
        deleteService = myActivityTestRule.getActivity();
    }

    @Test
    @UiThreadTest
    public void editService(){
        serviceNameToDelete = deleteService.findViewById(R.id.serviceNameToDelete);
        serviceNameToDelete.setText("Healing");


        deleteService.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                // Stuff that updates the UI
                deleteService.findViewById(R.id.confirmBtn).performClick();
            }
        });

    }

    @Test
    @UiThreadTest
    public void editServiceFail(){
        serviceNameToDelete = deleteService.findViewById(R.id.serviceNameToDelete);
        serviceNameToDelete.setText("");


        deleteService.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                // Stuff that updates the UI
                deleteService.findViewById(R.id.confirmBtn).performClick();
            }
        });
    }

    @Test
    @UiThreadTest
    public void cancelAddService(){
        deleteService.runOnUiThread(new Runnable() {

            @Override
            public void run() {
                // Stuff that updates the UI
                deleteService.findViewById(R.id.cancelBtn).performClick();

            }
        });

    }
}
