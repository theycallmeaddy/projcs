package com.example.theycallme_addy.bsccs;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class RegistrationActivity extends AppCompatActivity {

    private EditText userName,userPassword, userEmail, userRoll;
    private Button regButton;
    private TextView userLogin;
    private FirebaseAuth firebaseAuth;
    //private ImageView userProfilePic;
    private Spinner yearch;
    String name, email, password,roll, yr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        setupUIViews();

        firebaseAuth = FirebaseAuth.getInstance();



        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate()){
                    //database
                    String user_email = userEmail.getText().toString().trim();
                    String user_password = userPassword.getText().toString().trim();

                    firebaseAuth.createUserWithEmailAndPassword(user_email,user_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {

                                sendEmailVerification();
                                //startActivity(new Intent(RegistrationActivity.this, MainActivity.class));



                            }else{
                                Toast.makeText(RegistrationActivity.this, "Registration failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }

            }
        });

        userLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegistrationActivity.this, MainActivity.class));
            }
        });
    }

    private void    setupUIViews(){

        userName = (EditText)findViewById(R.id.etUserName);
        userPassword = (EditText)findViewById(R.id.etUserPassword);
        userEmail = (EditText)findViewById(R.id.etUserEmail);
        regButton = (Button) findViewById(R.id.btnRegister);
        userLogin = (TextView)findViewById(R.id.tvUserLogin);
        userRoll = (EditText) findViewById(R.id.etRoll);
        //userProfilePic = (ImageView)findViewById(R.id.ivProfile);
        yearch = (Spinner)findViewById(R.id.spinner1);


        List<String> adapter = new ArrayList<>();
        adapter.add(0, "Choose Year");
        adapter.add("First Year");
        adapter.add("Second Year");
        adapter.add("Third Year");

        ArrayAdapter<String> dataAdapter;
        dataAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,adapter);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        yearch.setAdapter(dataAdapter);
        yearch.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if(parent.getItemAtPosition(position).equals("Choose Year")){
                    //do nothing
                }

                else{
                    yr = parent.getItemAtPosition(position).toString();
                    Toast.makeText(parent.getContext(), yr, Toast.LENGTH_SHORT ).show();

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                yr = null;

            }
        });

    }


    private Boolean validate(){

        Boolean result = false;

        name = userName.getText().toString();
        password = userPassword.getText().toString();
        email = userEmail.getText().toString();
        roll = userRoll.getText().toString();


        if(name.isEmpty() || password.isEmpty() || email.isEmpty() || roll.isEmpty() || yr =="Choose Year" || yr == null){
            Toast.makeText(this,"Please Enter All Details",Toast.LENGTH_SHORT).show();
        }
        else{
            result = true;
        }
        return result;
    }

    private void sendEmailVerification(){
        final FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if(firebaseUser != null){

            firebaseUser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        sendUserData();
                        firebaseAuth.signOut();

                        Toast.makeText(RegistrationActivity.this , "Successful Registration, Verification Mail Sent",Toast.LENGTH_SHORT).show();
                        finish();
                        //startActivity(new Intent(RegistrationActivity.this, MainActivity.class));

                    }
                    else{
                        Toast.makeText(RegistrationActivity.this , "Verification Mail Hasn't been Send", Toast.LENGTH_SHORT).show();

                    }

                }
            });
        }


    }

    private void sendUserData(){
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myRef = firebaseDatabase.getReference().child("Students").child(firebaseAuth.getUid());
        UserProfile userProfile = new UserProfile(email, name, roll,true, yr);
        myRef.setValue(userProfile);
    }


}