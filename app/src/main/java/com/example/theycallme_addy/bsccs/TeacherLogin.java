package com.example.theycallme_addy.bsccs;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TeacherLogin extends AppCompatActivity {

    private EditText teacherEmail, teacherPassword;
    private Button teacherLogin;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;
    private FirebaseDatabase firebaseDatabase;


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
                    progressDialog.dismiss();
                    Toast.makeText(TeacherLogin.this,"Login Successful",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(TeacherLogin.this, TeacherInterface.class));


                }else{
                    Toast.makeText(TeacherLogin.this,"Login Failed",Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }

                }
        });


    }
}
