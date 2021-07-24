package com.example.count_vote;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

public class StartElection extends AppCompatActivity {

    Button start;
    Button end;
    Button vote;
    Button result;
    DatabaseReference databaseReference;
    public String electionGoingOn = "";

    public String vot1 = "";
    public String vot2 = "";
    public String vot3 = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_election);

        start = findViewById(R.id.start);
        end = findViewById(R.id.end);
        vote = findViewById(R.id.vote);
        result = findViewById(R.id.result);

        databaseReference = FirebaseDatabase.getInstance().getReference("Candidate's Votes");

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String count1 = Integer.toString(0);
                String count2 = Integer.toString(0);
                String count3 = Integer.toString(0);
                electionGoingOn = "true";

                update UploadInfo = new update(count1, count2, count3, electionGoingOn);
                databaseReference.setValue(UploadInfo).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getApplicationContext(), "Election has been started", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


        end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String,Object> map=new HashMap<>();
                map.put("electionGoingOn","false");

                FirebaseDatabase.getInstance().getReference().child("Candidate's Votes")
                        .child(electionGoingOn).updateChildren(map).addOnSuccessListener(new OnSuccessListener<Void>() {
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


                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        vot1 = snapshot.child("v1").getValue().toString();
                        vot2 = snapshot.child("v2").getValue().toString();
                        vot3 = snapshot.child("v3").getValue().toString();

                        HashMap<String, String> map3 = new HashMap<>();
                        map3.put("name1", vot1);
                        map3.put("name2", vot2);
                        map3.put("name3", vot3);

                        Intent intent = new Intent(StartElection.this, Result.class);
                        intent.putExtra("RESULT",map3);
                        startActivity(intent);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

        vote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartElection.this, MainActivity.class);
                startActivity(intent);
            }
        });

        result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        vot1 = snapshot.child("v1").getValue().toString();
                        vot2 = snapshot.child("v2").getValue().toString();
                        vot3 = snapshot.child("v3").getValue().toString();

                        HashMap<String, String> map3 = new HashMap<>();
                        map3.put("name1", vot1);
                        map3.put("name2", vot2);
                        map3.put("name3", vot3);

                        Intent intent = new Intent(StartElection.this, Result.class);
                        intent.putExtra("RESULT",map3);
                        startActivity(intent);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

    }
}