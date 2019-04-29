package com.example.theycallme_addy.bsccs;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SecondActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private Button fyAsg, syAsg, tyAsg;
    String year;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        firebaseAuth = FirebaseAuth.getInstance();
        fyAsg = (Button)findViewById(R.id.btnStudFirst);
        syAsg = (Button)findViewById(R.id.btnStudSecond);
        tyAsg = (Button)findViewById(R.id.btnStudThird);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();

        DatabaseReference databaseReference = firebaseDatabase.getReference().child("Students").child(firebaseAuth.getUid());

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                UserProfile userProfile = dataSnapshot.getValue(UserProfile.class);
                year = userProfile.getYear().toString();

                if(year.equals("First Year")){


                    syAsg.setEnabled(false);
                    tyAsg.setEnabled(false);

                }

                else if(year.equals("Third Year")){


                    fyAsg.setEnabled(false);
                    syAsg.setEnabled(false);

                }

                else if(year.equals("Second Year")){


                    fyAsg.setEnabled(false);
                    tyAsg.setEnabled(false);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(SecondActivity.this, databaseError.getCode(), Toast.LENGTH_SHORT).show();


            }
        });



        fyAsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SecondActivity.this, StudFirstYear.class));
            }
        });

        syAsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SecondActivity.this, StudSecondYear.class));
            }
        });

        tyAsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SecondActivity.this, StudThirdYear.class));
            }
        });







    }

    private void Logout(){
        firebaseAuth.signOut();
        finish();
        startActivity(new Intent(SecondActivity.this, MainActivity.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()){
            case R.id.logoutMenu:{

                Logout();
                break;
            }

            case R.id.ProfileMenu:{

                startActivity(new Intent(SecondActivity.this , ProfileActivity.class));
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
