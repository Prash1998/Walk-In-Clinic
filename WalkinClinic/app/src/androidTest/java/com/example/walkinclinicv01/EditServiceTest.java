package com.example.walkinclinicv01;

import android.widget.TextView;

import androidx.test.annotation.UiThreadTest;
import androidx.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class EditServiceTest {
    @Rule
    public ActivityTestRule<EditService> myActivityTestRule= new ActivityTestRule<EditService>(EditService.class);
    private EditService editService = null;
    private TextView oldServiceName, newServiceName, newRoleName, updateRating;

    @Before
    public void setUp(){
        editService = myActivityTestRule.getActivity();
    }

    @Test
    @UiThreadTest
    public void editService(){
        oldServiceName = editService.findViewById(R.id.oldServiceName);
        newServiceName = editService.findViewById(R.id.newServiceName);
        newRoleName = editService.findViewById(R.id.newRoleName);
        updateRating = editService.findViewById(R.id.updateRating);

        oldServiceName.setText("Healing");
        newServiceName.setText("Heal");
        newRoleName.setText("Doctor");
        updateRating.setText("");

        editService.runOnUiThread(new Runnable() {

            @Override
            public void run() {

                // Stuff that updates the UI
                editService.findViewById(R.id.confirmBtn2).performClick();

            }
        });

    }

    @Test
    @UiThreadTest
    public void editServiceFail(){
        oldServiceName = editService.findViewById(R.id.oldServiceName);
        newServiceName = editService.findViewById(R.id.newServiceName);
        newRoleName = editService.findViewById(R.id.newRoleName);
        updateRating = editService.findViewById(R.id.updateRating);

        oldServiceName.setText("Healing");
        newServiceName.setText("");
        newRoleName.setText("Doctor");
        updateRating.setText("");

        editService.runOnUiThread(new Runnable() {

            @Override
            public void run() {

                // Stuff that updates the UI
                editService.findViewById(R.id.confirmBtn2).performClick();

            }
        });
    }

    @Test
    @UiThreadTest
    public void cancelAddService(){
        editService.runOnUiThread(new Runnable() {

            @Override
            public void run() {

                // Stuff that updates the UI
                editService.findViewById(R.id.cancelBtn2).performClick();

            }
        });

    }
}
