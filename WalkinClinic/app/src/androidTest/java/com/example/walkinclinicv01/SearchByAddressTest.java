package com.example.walkinclinicv01;


import android.widget.TextView;

import androidx.test.annotation.UiThreadTest;
import androidx.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SearchByAddressTest {

    @Rule
    public ActivityTestRule<SearchByAddress> myActivityTestRule= new ActivityTestRule<SearchByAddress>(SearchByAddress.class);
    private SearchByAddress myActivity=null;

    private TextView addressName;
    @Before
    public void setUp() {
        myActivity = myActivityTestRule.getActivity();
    }

    @Test
    @UiThreadTest
    public void searchadd(){

        addressName=myActivity.findViewById(R.id.searchAddress);
        addressName.setText("123 Waterloo Av");

        String check=addressName.getText().toString();



        assertEquals("123 Waterloo Av",check);

    }

}
