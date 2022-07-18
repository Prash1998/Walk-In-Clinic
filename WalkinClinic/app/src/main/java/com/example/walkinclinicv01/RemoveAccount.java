package com.example.walkinclinicv01;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.lang.Object;

import com.google.firebase.auth.UserInfo;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.EmailAuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RemoveAccount extends AppCompatActivity implements View.OnClickListener  {
    private FirebaseAuth mAuth;
    private FirebaseDatabase mDatabase;
    private DatabaseReference myRef;
    FirebaseUser firebaseUser;


    EditText editTextUserNameToDelete;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_account);

        editTextUserNameToDelete = (EditText) findViewById(R.id.userNameToDelete);

        findViewById(R.id.userRemove).setOnClickListener(this);
        findViewById(R.id.cancelBtn3).setOnClickListener(this);
    }

    private void removeUser(){

        //CHANGING the EditText of editTextUserNameToDelete to a String value

        //String username = editTextUserNameToDelete.getText().toString().trim();


        //  **************we need a way to access the user id  (uid)  ************


        //this is to store the value of the User ID of the user we want deleted
        String userID="ghjjggjhhbjjvj";  //TO BE CHANGEDD



        //creating a person object
        //attributes of this object will repace the attributes of the user to be deletted
        Person temp= new Person("Patient","nullName","nullLastName","AnEmailYouWouldNeverGuess@gmail.com");

        //replacing user with specific userID with Person temp object
        DatabaseReference dR= FirebaseDatabase.getInstance().getReference();
        dR.child("users").child(userID).setValue(temp);



        FirebaseAuth.getInstance();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.userRemove:
                removeUser();
                break;
            case R.id.cancelBtn3:
                startActivity(new Intent(RemoveAccount.this,AdminScreen.class));
                break;
        }

    }
}