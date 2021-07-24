package com.example.count_vote;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.HashMap;

public class Result extends AppCompatActivity {

    TextView candidate1;
    TextView candidate2;
    TextView candidate3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        candidate1 = findViewById(R.id.candidate1);
        candidate2 = findViewById(R.id.candidate2);
        candidate3 = findViewById(R.id.candidate3);

        Intent intent = getIntent();
        HashMap<String, String> hashMap = (HashMap<String, String>) intent.getSerializableExtra("RESULT");

        candidate1.setText("name1 -> " + hashMap.get("name1"));
        candidate2.setText("name2 -> " + hashMap.get("name2"));
        candidate3.setText("name3 -> " + hashMap.get("name3"));

    }
}