package com.example.theycallme_addy.bsccs;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class AddFirst extends AppCompatActivity {

    private TextView Head,Submission;
    private EditText asgTitle, subDate, asgQuestions,subMonth, subYear;
    private Button AddSend;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseAuth firebaseAuth;
    String title,sdate,smonth,syear,question;
    Integer finalValue;
    Boolean res = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_first);

        Head = (TextView)findViewById(R.id.tvAddAsg);
        Submission = (TextView)findViewById(R.id.tvSub);
        asgTitle = (EditText)findViewById(R.id.etAsgTitle);
        subDate = (EditText)findViewById(R.id.subDate);
        subMonth = (EditText)findViewById(R.id.subMonth);
        subYear = (EditText)findViewById(R.id.subYear);
        asgQuestions = (EditText)findViewById(R.id.etQuestions);
        AddSend = (Button) findViewById(R.id.btnAddSub);



        //checkDate();


        AddSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate()){
                    sendAssignment();
                }

            }
        });


    }


    private void sendAssignment(){
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myRef = firebaseDatabase.getReference().child("Assignment First Year").child(title);

        Map map = new HashMap();

        map = ServerValue.TIMESTAMP;


        SendAssignment sendAssignment = new SendAssignment(title,sdate,smonth,syear,question,map);
        myRef.setValue(sendAssignment);

        Toast.makeText(AddFirst.this,"Assignment Added Successfully" ,Toast.LENGTH_SHORT).show();
    }

//    private void checkDate(){
//        Calendar today = Calendar.getInstance();
//        int dayOfMonth = today.get(Calendar.DAY_OF_MONTH);
//        int month = today.get(Calendar.MONTH);
//        int year = today.get(Calendar.YEAR);
//        if (month > finalValue|| dayOfMonth > 26 || year > 2019) {
//
//            AddSend.setEnabled(true);
//        }
//        else
//        {
//
//            AddSend.setEnabled(true);
//        }
//    }

    private Boolean validate(){

        Boolean result = false;

        title = asgTitle.getText().toString();
        sdate = subDate.getText().toString();
        smonth = subMonth.getText().toString();
        syear = subYear.getText().toString();
        question = asgQuestions.getText().toString();






        if(title.isEmpty() || sdate.isEmpty() || question.isEmpty() || smonth.isEmpty() || syear.isEmpty()){
            Toast.makeText(AddFirst.this,"Please Fill All Columns",Toast.LENGTH_SHORT).show();
            int sDateInt=Integer.parseInt(sdate);
            int sMonthInt=Integer.parseInt(smonth);
            int sYearInt=Integer.parseInt(syear);
            if(sDateInt>=32 || sMonthInt>=13 || sYearInt>=10000);{
                Toast.makeText(AddFirst.this,"Invalid Date",Toast.LENGTH_SHORT).show();

            }
        }
        else{
            result = true;
        }
        return result;
    }

}
