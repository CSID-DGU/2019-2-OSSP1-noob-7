package com.example.tutorial1;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/*
    메인 화면
*/

public class MainDisplayActivity extends AppCompatActivity {

    private TextView tv_id, tv_pass;
    ImageButton btn_setting, btn_graph;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        tv_id = findViewById(R.id.tv_id);
        // tv_pass = findViewById(R.id.tv_pass);
        btn_setting = findViewById(R.id.btn_setting);
        btn_graph = findViewById(R.id.btn_graph);

        Intent intent = getIntent();
        String userID = intent.getStringExtra("userID");
        String userPass = intent.getStringExtra("userPass");

        String strColor = "#004489";

        tv_id.setText(userID);
        tv_id.setTextColor(Color.parseColor(strColor));
        tv_id.setTextSize(20);
        // tv_pass.setText(userPass);

        btn_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainDisplayActivity.this, SettingActivity.class);

                startActivity(intent);
            }
        });

        btn_graph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainDisplayActivity.this, BarChart_Activity.class);

                startActivity(intent);
            }
        });
    }
}
