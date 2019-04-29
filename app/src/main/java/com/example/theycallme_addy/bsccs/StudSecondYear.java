package com.example.theycallme_addy.bsccs;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class StudSecondYear extends AppCompatActivity {

    ListView listView;
    FirebaseDatabase database;
    DatabaseReference ref;
    ArrayList<String> list;
    ArrayAdapter<String> adapter;
    RecieveAssignment recieveAssignment;
    String titleasg[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stud_second_year);

        recieveAssignment = new RecieveAssignment();
        titleasg = new String[100];

        listView = (ListView)findViewById(R.id.lvMarksList);
        database = FirebaseDatabase.getInstance();
        ref = database.getReference("Assignment Second Year");

        list = new ArrayList<>();
        adapter = new ArrayAdapter<String>(this,R.layout.user_info, R.id.userInfo, list);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int i=0;
                for(DataSnapshot ds : dataSnapshot.getChildren()){

                    recieveAssignment = ds.getValue(RecieveAssignment.class);
                    titleasg[i] = recieveAssignment.getAsgTitle().toString();
                    i++;

                    list.add(i+ "."+recieveAssignment.getAsgTitle().toString()+ "\n(Last Date:"+recieveAssignment.getSubDate()+")");

                }

                listView.setAdapter(adapter);

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Toast.makeText(StudSecondYear.this,titleasg[position],Toast.LENGTH_SHORT).show();

                        String value = titleasg[position];
                        Intent intent = new Intent(StudSecondYear.this, RecieveSecond.class);
                        intent.putExtra("title",value);
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

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
