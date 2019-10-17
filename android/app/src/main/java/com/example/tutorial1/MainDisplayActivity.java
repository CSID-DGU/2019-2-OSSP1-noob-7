package com.example.tutorial1;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/*
    메인 화면
*/

public class MainDisplayActivity extends AppCompatActivity {

    private TextView tv_id, tv_pass;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        tv_id = findViewById(R.id.tv_id);
        // tv_pass = findViewById(R.id.tv_pass);

        Intent intent = getIntent();
        String userID = intent.getStringExtra("userID");
        String userPass = intent.getStringExtra("userPass");

        String strColor = "#004489";

        tv_id.setText(userID);
        tv_id.setTextColor(Color.parseColor(strColor));
        tv_id.setTextSize(20);
        // tv_pass.setText(userPass);
    }
}
