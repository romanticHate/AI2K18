package com.example.romantichate.guayababeta;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity {
    private EditText editTextName;
    private Button buttonAdd;
    private Spinner spinnerGender;

    //DatabaseReference databaseUsers;
    FirebaseFirestore mFireStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFireStore = FirebaseFirestore.getInstance();

        //databaseUsers = FirebaseDatabase.getInstance().getReference("users");

        editTextName = findViewById(R.id.editTextName);
        buttonAdd = findViewById(R.id.buttonAdd);
        spinnerGender = findViewById(R.id.spinnerGender);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //addUser();
                addDataToFireBase();
            }
        });
    }

    private void addDataToFireBase() {
        String name = editTextName.getText().toString().trim();
        String gender = spinnerGender.getSelectedItem().toString();
        Map<String, String> userMap = new HashMap<>();
        userMap.put("name", name);
        userMap.put("gender", gender);

        mFireStore.collection("users").add(userMap).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Toast.makeText(MainActivity.this,"User add to fireBase DB",Toast.LENGTH_LONG).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                String error = e.getMessage();
                Toast.makeText(MainActivity.this,"Error: " + error,Toast.LENGTH_LONG).show();
            }
        });
    }
/*
    private void addUser(){

        //String name = editTextName.getText().toString().trim();
        String gender = spinnerGender.getSelectedItem().toString();

        if(!TextUtils.isEmpty(name)){

            String id = databaseUsers.push().getKey();
            User user = new User(id,name,gender);

            databaseUsers.child(id).setValue(user);//databaseUsers.child("users").child(id).setValue(user);

            Toast.makeText(this,"User add to fireBase DB",Toast.LENGTH_LONG).show();

        }else{
            Toast.makeText(this,"You should enter a name!",Toast.LENGTH_LONG).show();
        }

    }
    */
}
