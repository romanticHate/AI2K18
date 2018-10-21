package com.example.romantichate.guayababeta;

import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Nullable;




public class MainActivity extends AppCompatActivity {

    private EditText editTextName;
    private TextView textViewMsg;
    private Button buttonAdd,buttonLoad;
    private Spinner spinnerGender;
    //region Code: Basura
    //DatabaseReference databaseUsers;
    //endregion
    FirebaseFirestore mFireStore;

    private RecyclerView recyclerViewList;
    private List<User> userList;
    public UserListAdapter userListAdapter;

    private static final String FIRE_LOG = "Fire_Log";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userList = new ArrayList<>();
        userListAdapter = new UserListAdapter(userList);

        recyclerViewList = (RecyclerView)findViewById(R.id.recyclerViewList);
        recyclerViewList.setHasFixedSize(true);
        recyclerViewList.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewList.setAdapter(userListAdapter);

        //FireStore Instance
        mFireStore = FirebaseFirestore.getInstance();
        //region Code: Basura
        //databaseUsers = FirebaseDatabase.getInstance().getReference("users");
        //endregion

        //UI Controls
        textViewMsg = findViewById(R.id.textViewMsg);
        editTextName = findViewById(R.id.editTextName);
        buttonAdd = findViewById(R.id.buttonAdd);
        buttonLoad = findViewById(R.id.buttonLoad);
        spinnerGender = findViewById(R.id.spinnerGender);

        recyclerViewList = findViewById(R.id.recyclerViewList);

        mFireStore.collection("users").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                if(e != null){

                    Log.d(FIRE_LOG,"Error: "+e.getMessage());
                }
                for(DocumentChange doc: queryDocumentSnapshots.getDocumentChanges()){

                    if(doc.getType() == DocumentChange.Type.ADDED){

                       User user = doc.getDocument().toObject(User.class);
                       userList.add(user);

                        userListAdapter.notifyDataSetChanged();
                    }
                }
            }
        });



        //EventListener's
        buttonLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFireStore.collection("users").document("xyz").get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if(task.isSuccessful()){
                            DocumentSnapshot documentSnapshot = task.getResult();
                            String name = documentSnapshot.getString("name");
                            textViewMsg.setText("Welcome guayaba user: " + name);
                        }else{
                            Log.d(FIRE_LOG,"Error: "+task.getException().getMessage());
                        }
                    }
                });
            }
        });

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //region Code: Basura
                //addUser();
                //endregion
                addDataToFireBase();
            }
        });
    }

    private void addDataToFireBase() {
        String name = editTextName.getText().toString().trim();
        String gender = spinnerGender.getSelectedItem().toString();
        //User user = new User(name,gender);
        Map<String, String> userMap = new HashMap<>();
        userMap.put("name", name);
        userMap.put("gender", gender);

        mFireStore.collection("users").document("xyz").set(userMap).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                Toast.makeText(MainActivity.this,"Error: " + e.getMessage(),Toast.LENGTH_LONG).show();
            }
        });

        /*
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
        */
    }
    //region  Code: basura
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
//endregion
}
