package com.example.theycallme_addy.bsccs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class TeacherInterface extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private Button First, Second, Third;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_interface);

        First = (Button)findViewById(R.id.btnFirst);
        Second = (Button)findViewById(R.id.btnSecond);
        Third = (Button)findViewById(R.id.btnThird);

        firebaseAuth = FirebaseAuth.getInstance();


        First.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TeacherInterface.this, FirstYear.class));
            }
        });

        Second.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TeacherInterface.this, SecondYear.class));
            }
        });

        Third.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TeacherInterface.this, ThirdYear.class));
            }
        });
    }



    private void Logout(){
        firebaseAuth.signOut();
        finish();
        startActivity(new Intent(TeacherInterface.this, TeacherLogin.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menuti, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()){
            case R.id.logoutMenu:{
                Logout();
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
