package com.example.walkinclinicv01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EditService extends AppCompatActivity implements View.OnClickListener{

    private FirebaseDatabase mDataBase;
    private DatabaseReference myRef;
    EditText EditTextOldServiceName, EditTextNewServiceName, EditTextNewRoleName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_service);

        EditTextOldServiceName = (EditText) findViewById(R.id.oldServiceName);
        EditTextNewServiceName = (EditText) findViewById (R.id.newServiceName);
        EditTextNewRoleName = (EditText) findViewById(R.id.newRoleName);
        //createBtn, cancelBtn
        findViewById(R.id.confirmBtn2).setOnClickListener(this);
        findViewById(R.id.cancelBtn2).setOnClickListener(this);

    }

    private void replaceOldService() {
        String deleteServiceName = EditTextOldServiceName.getText().toString().trim();

        myRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference servicesNode = myRef.child("Services");
        servicesNode.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if (snapshot.hasChild(deleteServiceName)) {
                    Log.d("PassedCheck", servicesNode.child(deleteServiceName).toString());
                    servicesNode.child(deleteServiceName).setValue(null);
                    addNewService();
                    startActivity(new Intent(EditService.this,EditService.class));
                }
                else{
                    EditTextOldServiceName.setError("No service with the given name to be deleted has been found");
                }

            }

            @Override
            public void onCancelled(DatabaseError firebaseError) { }
        });

    }

    private void addNewService() {
        String serviceName = EditTextNewServiceName.getText().toString().trim();
        String roleName = EditTextNewRoleName.getText().toString().trim();

        final Service service = new Service(serviceName,roleName);

        myRef = FirebaseDatabase.getInstance().getReference();
        myRef.child("Services").child(serviceName).setValue(service);
        Toast.makeText(this, "Service replaced", Toast.LENGTH_LONG).show();

    }

    private void editService() {
        String oldServiceName = EditTextOldServiceName.getText().toString().trim();
        String newServiceName = EditTextNewServiceName.getText().toString().trim();
        String roleName = EditTextNewRoleName.getText().toString().trim();

        if (oldServiceName.isEmpty()) {
            EditTextOldServiceName.setError("Old service name is required");
            EditTextOldServiceName.requestFocus();
            return ;
        }

        if (newServiceName.isEmpty()) {
            EditTextNewServiceName.setError("New service name is required");
            EditTextNewServiceName.requestFocus();
            return ;
        }

        if (roleName.isEmpty()) {
            EditTextNewRoleName.setError("Role is required");
            EditTextNewRoleName.requestFocus();
            return ;
        }

        replaceOldService();

    }
    @Override
    public void onClick(View v){
        switch(v.getId()){
            case R.id.confirmBtn2:
                editService();
                break;
            case R.id.cancelBtn2:
                startActivity(new Intent(this,AdminScreen.class));
                break;

        }
    }
}
