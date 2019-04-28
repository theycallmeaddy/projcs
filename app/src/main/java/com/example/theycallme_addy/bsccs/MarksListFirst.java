package com.example.theycallme_addy.bsccs;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MarksListFirst extends AppCompatActivity {

    ListView listView;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase database;
    DatabaseReference ref;
    ArrayList<String> listmarks;
    ArrayAdapter<String> adapter;
    UserProfile userProfile;
    String titleasg[],i,value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marks_list_first);

        value= getIntent().getStringExtra("title");

        userProfile = new UserProfile();
        titleasg = new String[100];

        listView = (ListView)findViewById(R.id.lvMarksList);
        database = FirebaseDatabase.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        ref = database.getReference("Students");
        listmarks = new ArrayList<>();
        adapter = new ArrayAdapter<String>(this,R.layout.user_info, R.id.userInfo, listmarks);


        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int i=0;
                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    userProfile = ds.getValue(UserProfile.class);
                    if (userProfile.getYear().toString().equals("First Year")) {
                        titleasg[i] = userProfile.getUid();
                        listmarks.add(userProfile.getUserName().toString() + "    " + userProfile.getUserRoll().toString() );
                        i++;
                    }
                }

                listView.setAdapter(adapter);

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Toast.makeText(MarksListFirst.this,titleasg[position],Toast.LENGTH_SHORT).show();

                        String uid = titleasg[position];
                        Intent intent = new Intent(MarksListFirst.this, AssessmentFirst.class);
                        intent.putExtra("uid",uid);
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
}
