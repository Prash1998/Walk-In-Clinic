package com.example.walkinclinicv01;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    //private Button signInButton;
    //private Button registerButton
    EditText UserName, Password;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference myRef;
    private FirebaseUser mUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.register).setOnClickListener(this);
        findViewById(R.id.signIn).setOnClickListener(this);
        UserName = (EditText) findViewById(R.id.username);
        Password = (EditText) findViewById(R.id.password);

        firebaseAuth = firebaseAuth.getInstance();
        firebaseAuth.signOut();                              //sign out last user
        //System.out.println("Email:"+firebaseAuth.getCurrentUser().getEmail()+",UId:"+firebaseAuth.getCurrentUser().getUid());
    }


    public void checkLogin(final View view) {

        String username = UserName.getText().toString().trim();
        String password = Password.getText().toString().trim();
        if (username.isEmpty() || password.isEmpty()){
            if (username.isEmpty()) {
                UserName.setError("Email is required");
                UserName.requestFocus();
                return;
            } else{
                Password.setError("Password is required");
                Password.requestFocus();
                return;
            }
        }else {
            firebaseAuth.signInWithEmailAndPassword(UserName.getText().toString(), Password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        if (UserName.getText().toString().equals("qwe@gmail.com") && Password.getText().toString().equals("123456")) {
                            startActivity(new Intent(MainActivity.this, AdminScreen.class));
                        } else    //Check if User Signed in is an Employee or Patient
                            myRef = FirebaseDatabase.getInstance().getReference();
                            mUser = firebaseAuth.getCurrentUser();

                            myRef.child("users").child(mUser.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    Person person = dataSnapshot.getValue(Person.class);

                                    if(person.getRole().equals("Employee")|| person.getRole().equals("employee")){       //Person is an employee

                                        myRef.child("Clinics").addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                if(dataSnapshot.hasChild(mUser.getUid())){
                                                    startActivity(new Intent(MainActivity.this,ClinicServicesScreen.class));
                                                }else{
                                                    startActivity(new Intent(MainActivity.this,EmployeeScreen.class));
                                                }
                                                //for (DataSnapshot ds: dataSnapshot.getChildren()){
                                                    //Clinic clinic = ds.getValue(Clinic.class);
                                                    //if(ds.getValue(Clinic.class).getUSERID().equals(mUser.getUid())){
                                                        //startActivity(new Intent(MainActivity.this,ClinicServicesScreen.class));
                                                    //}
                                                    //if(ds.hasChild(mUser.getUid())){
                                                        //startActivity(new Intent(MainActivity.this,ClinicServicesScreen.class));
                                                    //}
                                                //}

                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError databaseError) {

                                            }
                                        });
                                        //startActivity(new Intent(MainActivity.this,EmployeeScreen.class));
                                    }
                                    else{
                                        startActivity(new Intent(MainActivity.this, WelcomeWindow.class));
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });
                            //startActivity(new Intent(MainActivity.this, WelcomeWindow.class));
                    } else {
                        Toast.makeText(MainActivity.this, "Invalid Entry", Toast.LENGTH_LONG).show();
                    }

                }
            });
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.register:
                startActivity(new Intent(this,RegistrationWindow.class));
                break;
            case R.id.signIn:
                checkLogin(view);
                //startActivity(new Intent(this,WelcomeWindow.class));
                break;
        }

    }

}