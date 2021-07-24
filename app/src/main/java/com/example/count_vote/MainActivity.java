package com.example.count_vote;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity {

    TextView username;
    TextView count1;
    Button vote1;
    TextView count2;
    Button vote2;
    TextView count3;
    Button vote3;
    Button update;

    int c1 = 0;
    int c2 = 0;
    int c3 = 0;


    DatabaseReference databaseReference;
    DatabaseReference db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.username);
        count1 = findViewById(R.id.count1);
        vote1 = findViewById(R.id.vote1);
        count2 = findViewById(R.id.count2);
        vote2 = findViewById(R.id.vote2);
        count3 = findViewById(R.id.count3);
        vote3 = findViewById(R.id.vote3);
        update = findViewById(R.id.update);


        Intent intent = getIntent();
        String name = intent.getStringExtra("NAME");
        username.setText(name);

        HashMap<String, String> map = new HashMap<>();
        map.put(name, "no");

        databaseReference = FirebaseDatabase.getInstance().getReference("Candidate's Votes");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                 c1 = Integer.parseInt(snapshot.child("v1").getValue().toString());
                 c2 = Integer.parseInt(snapshot.child("v2").getValue().toString());
                 c3 = Integer.parseInt(snapshot.child("v3").getValue().toString());
                count1.setText(c1+"");
                count2.setText(c2+"");
                count3.setText(c3+"");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        vote1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(map.get(name).equals("no")){
                    c1++;
                    count1.setText(c1+"");
                }
                else{
                    Toast.makeText(getApplicationContext(), "You have Already Voted", Toast.LENGTH_SHORT).show();
                }
                //upload(c1, c2, c3);
            }
        });

        vote2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(map.get(name).equals("no")){
                    c2++;
                    count2.setText(c2+"");
                }
                else{
                    Toast.makeText(getApplicationContext(), "You have Already Voted", Toast.LENGTH_SHORT).show();
                }
                //upload(c1, c2, c3);
            }
        });

        vote3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(map.get(name).equals("no")){
                    c3++;
                    count3.setText(c3+"");
                }
                else{
                    Toast.makeText(getApplicationContext(), "You have Already Voted", Toast.LENGTH_SHORT).show();
                }
                //upload(c1, c2, c3);
            }
        });


        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String,Object> map2 = new HashMap<>();
                Object vot1 = Integer.toString(c1);
                Object vot2 = Integer.toString(c2);
                Object vot3 = Integer.toString(c3);

                map2.put("v1", vot1);
                map2.put("v2", vot2);
                map2.put("v3", vot3);

                FirebaseDatabase.getInstance().getReference().child("Candidate's Votes")
                        .updateChildren(map2).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(v.getContext(), "Election Ended", Toast.LENGTH_SHORT).show();
                    }

                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(v.getContext(), e.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


    }
}