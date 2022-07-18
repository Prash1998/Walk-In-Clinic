package com.example.walkinclinicv01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class SearchByHours extends AppCompatActivity implements View.OnClickListener {

    String day, open, close;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_by_hours);
        findViewById(R.id.cancelBtn6).setOnClickListener(this);
        findViewById(R.id.searchBtn).setOnClickListener(this);

        day = ((EditText) findViewById(R.id.searchDay)).toString().trim();
        open = ((EditText) findViewById(R.id.searchStartHours)).toString().trim();
        close = ((EditText) findViewById(R.id.searchStartHours2)).toString().trim();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cancelBtn6:
                startActivity(new Intent(SearchByHours.this, ClinicSearch.class));
                break;

            case R.id.searchBtn:
                Intent service = new Intent(SearchByHours.this, ListOfClinics.class);
                service.putExtra("Day", day);
                service.putExtra("Open", open);
                service.putExtra("Close",close);
                startActivity(service);
                break;
        }
    }
}