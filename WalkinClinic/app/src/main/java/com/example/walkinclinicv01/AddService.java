package com.example.walkinclinicv01;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddService extends AppCompatActivity implements View.OnClickListener{

    private FirebaseDatabase mDataBase;
    private DatabaseReference myRef;
    EditText EditTextServiceName, EditTextRoleName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_service);

        EditTextServiceName = (EditText) findViewById(R.id.serviceName);
        EditTextRoleName = (EditText) findViewById(R.id.roleName);
        //createBtn, cancelBtn
        findViewById(R.id.createBtn).setOnClickListener(this);
        findViewById(R.id.cancelBtn).setOnClickListener(this);

    }

    private void addService() {
        String serviceName = EditTextServiceName.getText().toString().trim();
        String roleName = EditTextRoleName.getText().toString().trim();

        if (serviceName.isEmpty()) {
            EditTextServiceName.setError("Service name is Required");
            EditTextServiceName.requestFocus();
            return ;
        }

        if (roleName.isEmpty()) {
            EditTextRoleName.setError("Role is required");
            EditTextRoleName.requestFocus();
            return ;
        }

        final Service service = new Service(serviceName,roleName);

        myRef = FirebaseDatabase.getInstance().getReference();
        myRef.child("Services").child(serviceName).setValue(service);
        Toast.makeText(this, "Service added", Toast.LENGTH_LONG).show();

    }
    @Override
    public void onClick(View v){
        switch(v.getId()){
            case R.id.createBtn:
                addService();
                break;
            case R.id.cancelBtn:
                startActivity(new Intent(this,AdminScreen.class));
                break;

        }
    }

}
