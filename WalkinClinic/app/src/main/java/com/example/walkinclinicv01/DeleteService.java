package com.example.walkinclinicv01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

public class DeleteService extends AppCompatActivity implements View.OnClickListener {

    private FirebaseDatabase mDataBase;
    private DatabaseReference myRef;
    EditText EditTextDeleteServiceName;

    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_service);

        EditTextDeleteServiceName = (EditText) findViewById(R.id.serviceNameToDelete);
        //createBtn, cancelBtn
        findViewById(R.id.confirmBtn).setOnClickListener(this);
        findViewById(R.id.cancelBtn).setOnClickListener(this);

    }

    // Check if the service to be deleted is in the database
    // Then delete it
    private void deleteService() {
        String deleteServiceName = EditTextDeleteServiceName.getText().toString().trim();


        if (deleteServiceName.isEmpty()) {
            EditTextDeleteServiceName.setError("Name of service to be deleted is required");
            EditTextDeleteServiceName.requestFocus();
            return ;
        }

        myRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference servicesNode = myRef.child("Services");
        servicesNode.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if (snapshot.hasChild(deleteServiceName)) {
                    Log.d("PassedCheck", servicesNode.child(deleteServiceName).toString());
                    servicesNode.child(deleteServiceName).setValue(null);
                    Toast.makeText(DeleteService.this, "Service deleted", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(DeleteService.this,DeleteService.class));
                }
                else{
                    EditTextDeleteServiceName.setError("No service with the given name has been found");
                }

            }

            @Override
            public void onCancelled(DatabaseError firebaseError) { }
        });


    }

    private boolean flag;
    @Override
    public void onClick(View v){
        switch(v.getId()){
            case R.id.confirmBtn:
                deleteService();
                break;
            case R.id.cancelBtn:
                startActivity(new Intent(this,AdminScreen.class));
                break;

        }
    }
}
