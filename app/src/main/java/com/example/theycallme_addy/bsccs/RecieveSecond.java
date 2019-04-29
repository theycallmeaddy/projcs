package com.example.theycallme_addy.bsccs;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class RecieveSecond extends AppCompatActivity {

    private TextView heading,question;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseAuth firebaseAuth;
    private EditText answerAsg;
    private Button ansSubmit;
    String value,answer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recieve_second);

        value= getIntent().getStringExtra("title");

        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        heading = (TextView)findViewById(R.id.heading);
        question = (TextView)findViewById(R.id.tvQues);
        answerAsg = (EditText)findViewById(R.id.etAnswers);
        ansSubmit = (Button)findViewById(R.id.btnAnswerSubmit);

        heading.setText(value);


        DatabaseReference databaseReference = firebaseDatabase.getReference().child("Assignment Second Year").child(value);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                RecieveAssignment recieveAssignment = dataSnapshot.getValue(RecieveAssignment.class);
                question.setText(recieveAssignment.getAsgQuestions());
                question.setMovementMethod(new ScrollingMovementMethod());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        ansSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate()){
                    sendAssignmentAns();
                }

            }
        });




    }

    private void sendAssignmentAns(){

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myRef = firebaseDatabase.getReference().child("Assignment Second Year Answers").child(value).child(firebaseAuth.getUid());

        Map map = new HashMap();

        map = ServerValue.TIMESTAMP;

        RecieveAnswer recieveAnswer = new RecieveAnswer(answer ,map);
        myRef.setValue(recieveAnswer);

        Toast.makeText(RecieveSecond.this,"Assignment Submitted Successfully" ,Toast.LENGTH_SHORT).show();
    }

    private Boolean validate(){

        Boolean result = false;

        answer = answerAsg.getText().toString();


        if(answer.isEmpty()){
            Toast.makeText(RecieveSecond.this,"I Like Your Confidence! Now Type the Answers",Toast.LENGTH_SHORT).show();
        }
        else{
            result = true;
        }
        return result;
    }
}
