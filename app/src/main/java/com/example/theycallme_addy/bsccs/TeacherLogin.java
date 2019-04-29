package com.example.theycallme_addy.bsccs;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

public class TeacherLogin extends AppCompatActivity {

    private EditText teacherEmail, teacherPassword;
    private Button teacherLogin;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;
    private FirebaseDatabase firebaseDatabase;
    Boolean type;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_login);

        teacherEmail = (EditText)findViewById(R.id.etTeacherEmail);
        teacherPassword = (EditText)findViewById(R.id.etTeacherPassword);
        teacherLogin = (Button)findViewById(R.id.btnTeacherLogin);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();

        progressDialog = new ProgressDialog(this);

        FirebaseUser user = firebaseAuth.getCurrentUser();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        teacherLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(teacherPassword.length()==0 && teacherEmail.length()==0) {
                    teacherPassword.setError("Enter Password");
                    teacherEmail.setError("Enter Email");
                }

                else if(teacherEmail.length()==0){
                    teacherEmail.setError("Enter Email");
                }

                else if(teacherPassword.length()==0){
                    teacherPassword.setError("Enter Password");
                }


                else {
                    teacherValidate(teacherEmail.getText().toString(), teacherPassword.getText().toString());
                }
            }
        });


    }

    private void teacherValidate(String tEmail, String tPassword){


        progressDialog.setMessage("Authenticating");
        progressDialog.show();


        firebaseAuth.signInWithEmailAndPassword(tEmail, tPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){

                    checkTeacher();
                    progressDialog.dismiss();
                    //Toast.makeText(TeacherLogin.this,"Login Successful",Toast.LENGTH_SHORT).show();



                }else{
                    Toast.makeText(TeacherLogin.this,"Login Failed",Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }

                }
        });


    }

    private void checkTeacher(){

        FirebaseUser firebaseUser = firebaseAuth.getInstance().getCurrentUser();


        DatabaseReference databaseReference = firebaseDatabase.getReference().child("Teacher").child(firebaseAuth.getUid());

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                UserProfile userProfile = dataSnapshot.getValue(UserProfile.class);


                try {
                    type = userProfile.getId();

                    if(type.toString() == "false"){
                        finish();

                        startActivity(new Intent(TeacherLogin.this, TeacherInterface.class));


                    }

                }
                catch (Exception e){
                    Toast.makeText(TeacherLogin.this, "Teacher Not Registered" , Toast.LENGTH_SHORT).show();
                    firebaseAuth.signOut();

                }





            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(TeacherLogin.this, databaseError.getCode(), Toast.LENGTH_SHORT).show();


            }
        });





    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//
//        switch(item.getItemId()){
//            case android.R.id.home:
//                onBackPressed();
//        }
//        return super.onOptionsItemSelected(item);
//    }

    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivityForResult(myIntent, 0);
        finish();
        return true;

    }



}
