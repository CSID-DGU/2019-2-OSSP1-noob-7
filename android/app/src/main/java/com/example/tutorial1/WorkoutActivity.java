package com.example.tutorial1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class WorkoutActivity extends AppCompatActivity {

    Button btn_chest, btn_back, btn_legs, btn_shoulders, btn_arms;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);

        btn_chest = findViewById(R.id.btn_chest);

        btn_back = findViewById(R.id.btn_back);

        btn_legs = findViewById(R.id.btn_legs);

        btn_shoulders = findViewById(R.id.btn_shoulders);

        btn_arms = findViewById(R.id.btn_arms);

        btn_chest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(WorkoutActivity.this, ChestActivity.class);

                startActivity(intent);
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(WorkoutActivity.this, BackActivity.class);

                startActivity(intent);
            }
        });






    }

}
