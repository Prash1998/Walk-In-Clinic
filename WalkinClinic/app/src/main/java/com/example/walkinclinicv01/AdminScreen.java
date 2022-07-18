package com.example.walkinclinicv01;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

public class AdminScreen extends AppCompatActivity implements View.OnClickListener {

    FirebaseAuth mAuth;
    FirebaseUser mUser;
    DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_screen);

        findViewById(R.id.addBtn).setOnClickListener(this);
        findViewById(R.id.editBtn).setOnClickListener(this);
        findViewById(R.id.deleteBtn).setOnClickListener(this);
        findViewById(R.id.logout).setOnClickListener(this);
        findViewById(R.id.removeUser).setOnClickListener(this);
    }

    public void deleteAccount(){


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.addBtn:
                startActivity(new Intent(AdminScreen.this, AddService.class));
                break;
            case R.id.editBtn:
                startActivity(new Intent(AdminScreen.this, EditService.class));
                break;
            case R.id.deleteBtn:
                startActivity(new Intent(AdminScreen.this, DeleteService.class));
                break;
            case R.id.logout:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(AdminScreen.this, MainActivity.class));
                break;
            case R.id.removeUser:
                startActivity(new Intent(AdminScreen.this, RemoveAccount.class));
                break;
        }
    }
}