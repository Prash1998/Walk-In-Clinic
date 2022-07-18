package com.example.walkinclinicv01;

import android.widget.TextView;

import androidx.test.annotation.UiThreadTest;
import androidx.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.Random;

public class RegisterTest {

    @Rule
    public ActivityTestRule<RegistrationWindow> myActivityTestRule= new ActivityTestRule<RegistrationWindow>(RegistrationWindow.class);

    private RegistrationWindow registerAction = null;
    private TextView email, password, firstname, lastname, role, confirmpassword;

    @Before
    public void setUp() {
        registerAction = myActivityTestRule.getActivity();
    }

    @Test
    @UiThreadTest
    public void registerPatient (){
        email = registerAction.findViewById(R.id.username);
        password = registerAction.findViewById(R.id.password);
        confirmpassword = registerAction.findViewById(R.id.confirmPassword);
        firstname = registerAction.findViewById(R.id.firstName);
        lastname = registerAction.findViewById(R.id.lastName);
        role = registerAction.findViewById(R.id.userRole);

        email.setText(randomString());
        password.setText("123456");
        confirmpassword.setText("123456");
        firstname.setText("Patient");
        lastname.setText("You");
        role.setText("Patient");

        registerAction.runOnUiThread(new Runnable() {

            @Override
            public void run() {

                // Stuff that updates the UI
                registerAction.findViewById(R.id.button).performClick();

            }
        });

    }

    public String randomString(){
        String random = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 18) { // length of the random string.
            int index = (int) (rnd.nextFloat() * random.length());
            salt.append(random.charAt(index));
        }
        salt.append("@gmail.com");
        String saltStr = salt.toString();
        System.out.println(salt);
        return saltStr;
    }

    @Test
    @UiThreadTest
    public void registerEmployee(){
        email = registerAction.findViewById(R.id.username);
        password = registerAction.findViewById(R.id.password);
        confirmpassword = registerAction.findViewById(R.id.confirmPassword);
        firstname = registerAction.findViewById(R.id.firstName);
        lastname = registerAction.findViewById(R.id.lastName);
        role = registerAction.findViewById(R.id.userRole);

        email.setText(randomString());

        password.setText("123456");
        confirmpassword.setText("123456");
        firstname.setText("Employee");
        lastname.setText("You");
        role.setText("Employee");

        registerAction.runOnUiThread(new Runnable() {

            @Override
            public void run() {
                // Stuff that updates the UI
                registerAction.findViewById(R.id.button).performClick();

            }
        });
    }

    @Test
    @UiThreadTest
    public void registerFail(){
        email = registerAction.findViewById(R.id.username);
        password = registerAction.findViewById(R.id.password);
        confirmpassword = registerAction.findViewById(R.id.confirmPassword);
        firstname = registerAction.findViewById(R.id.firstName);
        lastname = registerAction.findViewById(R.id.lastName);
        role = registerAction.findViewById(R.id.userRole);

        email.setText(randomString());
        password.setText("");
        confirmpassword.setText("123456");
        firstname.setText("Employee");
        lastname.setText("You");
        role.setText("Employee");

        registerAction.runOnUiThread(new Runnable() {

            @Override
            public void run() {

                // Stuff that updates the UI
                registerAction.findViewById(R.id.button).performClick();

            }
        });
    }

    @Test
    @UiThreadTest
    public void backToMain(){
        registerAction.runOnUiThread(new Runnable() {

            @Override
            public void run() {
                // Stuff that updates the UI
                registerAction.findViewById(R.id.logIn).performClick();

            }
        });
    }




}
