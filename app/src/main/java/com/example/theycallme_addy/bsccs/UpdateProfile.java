package com.example.theycallme_addy.bsccs;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UpdateProfile extends AppCompatActivity {

    private EditText newUserName, newUserEmail, newUserRoll;
    private Button save;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    String year;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);


        newUserName = findViewById(R.id.etNameUpdate);
        newUserEmail = findViewById(R.id.etEmailUpdate);
        newUserRoll = findViewById(R.id.etRollUpdate);
        save = findViewById(R.id.btnSave);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();

        final DatabaseReference databaseReference = firebaseDatabase.getReference().child("Students").child(firebaseAuth.getUid());

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                UserProfile userProfile = dataSnapshot.getValue(UserProfile.class);
                newUserName.setText(userProfile.getUserName());
                newUserEmail.setText(userProfile.getUserEmail());
                newUserRoll.setText(userProfile.getUserRoll());
                year = userProfile.getYear();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(UpdateProfile.this, databaseError.getCode(), Toast.LENGTH_SHORT).show();


            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = newUserName.getText().toString();
                String roll = newUserRoll.getText().toString();
                String email = newUserEmail.getText().toString();

                UserProfile userProfile = new UserProfile(email , name , roll , true ,year, firebaseAuth.getUid());

                databaseReference.setValue(userProfile);

                finish();


            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()){
            case android.R.id.home:
                onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
