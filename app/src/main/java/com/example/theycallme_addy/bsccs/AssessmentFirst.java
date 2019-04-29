package com.example.theycallme_addy.bsccs;

import android.graphics.Color;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

public class AssessmentFirst extends AppCompatActivity {
    String uid,value;
    private TextView ques,ans;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    Long subtime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessment_first);

        uid= getIntent().getStringExtra("uid");
        value= getIntent().getStringExtra("title");

        ques = (TextView)findViewById(R.id.tvques);
        //ans = (TextView)findViewById(R.id.tvans);
        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();


        DatabaseReference databaseReference = firebaseDatabase.getReference().child("Assignment First Year").child(value);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                RecieveAssignment recieveAssignment = dataSnapshot.getValue(RecieveAssignment.class);
                ques.setText("QUESTIONS : \n"+recieveAssignment.getAsgQuestions());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        DatabaseReference databaseReference1 = firebaseDatabase.getReference().child("Assignment First Year Answers").child(value).child(uid);
        databaseReference1 .addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try {
                    RetreiveAnswer retreiveAnswer = dataSnapshot.getValue(RetreiveAnswer.class);
                    ques.append("\n\nANSWERS : \n" + retreiveAnswer.getAnswer());
                    subtime = retreiveAnswer.getTimestamp();

                    String dateofsubmission=getDate(subtime);


                    Toast.makeText(AssessmentFirst.this,"Answers Submitted on:"+ dateofsubmission,Toast.LENGTH_LONG).show();
                }
                catch (Exception e){
                    Toast.makeText(AssessmentFirst.this,"Answers not Submitted yet",Toast.LENGTH_SHORT).show();
                    ques.append("\n\n"+"Answers not Submitted yet");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private String getDate(long subtime) {
        Calendar cal = Calendar.getInstance(Locale.ENGLISH);
        cal.setTimeInMillis(subtime);
        String date = DateFormat.format("dd-MM-yyyy", cal).toString();
        return date;
    }
}
