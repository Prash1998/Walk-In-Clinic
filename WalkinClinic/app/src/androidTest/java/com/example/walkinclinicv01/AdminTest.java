package com.example.walkinclinicv01;

import androidx.test.annotation.UiThreadTest;
import androidx.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class AdminTest {
    @Rule
    public ActivityTestRule<AdminScreen> mAdminTestRule = new ActivityTestRule<AdminScreen>(AdminScreen.class);
    private AdminScreen mAdmin = null;

    @Before
    public void setUp(){

        mAdmin = mAdminTestRule.getActivity();
    }

    @Test
    @UiThreadTest
    public void adminTestAddService() throws Exception{

        mAdmin.runOnUiThread(new Runnable() {

            @Override
            public void run() {

                // Stuff that updates the UI
                mAdmin.findViewById(R.id.addBtn).performClick();

            }
        });
    }
    @Test
    @UiThreadTest
    public void adminTestDeleteService() throws  Exception{

        mAdmin.runOnUiThread(new Runnable() {

            @Override
            public void run() {

                // Stuff that updates the UI
                mAdmin.findViewById(R.id.deleteBtn).performClick();

            }
        });
    }
    @Test
    @UiThreadTest
    public void adminTestEditService() throws Exception{

        mAdmin.runOnUiThread(new Runnable() {

            @Override
            public void run() {

                // Stuff that updates the UI
                mAdmin.findViewById(R.id.editBtn).performClick();

            }
        });
    }
    @Test
    @UiThreadTest
    public void adminTestRemoveUser() throws Exception{

        mAdmin.runOnUiThread(new Runnable() {

            @Override
            public void run() {

                // Stuff that updates the UI
                mAdmin.findViewById(R.id.removeUser).performClick();

            }
        });
    }

    @Test
    @UiThreadTest
    public void adminTestLogOut() throws  Exception{

        mAdmin.runOnUiThread(new Runnable() {

            @Override
            public void run() {

                // Stuff that updates the UI
                mAdmin.findViewById(R.id.logout).performClick();

            }
        });
    }
}
