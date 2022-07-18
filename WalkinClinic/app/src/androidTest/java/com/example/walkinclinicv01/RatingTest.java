package com.example.walkinclinicv01;


import android.widget.TextView;

import androidx.test.annotation.UiThreadTest;
import androidx.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertEquals;



public class RatingTest {

    @Rule
    public ActivityTestRule<Rating> myActivityTestRule= new ActivityTestRule<Rating>(Rating.class);
    private Rating myActivity=null;

    private TextView RateNUM;
    private TextView review;

    @Before
    public void setUp() {
        myActivity = myActivityTestRule.getActivity();
    }

    @Test
    @UiThreadTest
    public void rateservice(){

        RateNUM=myActivity.findViewById(R.id.rating);
        RateNUM.setText("4.8");


        review=myActivity.findViewById(R.id.comment);
        review.setText("Good");


        String check=RateNUM.getText().toString();
        String check2=review.getText().toString();



        assertEquals("4.8",check);
        assertEquals("Good",check2);


    }

}
