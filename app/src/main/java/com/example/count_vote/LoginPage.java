package com.example.count_vote;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class LoginPage extends AppCompatActivity {

    EditText uname;
    Button start_election;
    Button vote;

    public String usrname = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        uname = findViewById(R.id.uname);
        start_election = findViewById(R.id.startElection);
        vote = findViewById(R.id.vote);



        start_election.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginPage.this, StartElection.class);
                startActivity(intent);
            }
        });

        vote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usrname += uname.getText().toString();
                Intent intent = new Intent(LoginPage.this, MainActivity.class);
                intent.putExtra("NAME",usrname);
                startActivity(intent);
            }
        });
    }
}