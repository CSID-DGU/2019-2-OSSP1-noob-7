package com.example.tutorial1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.LineChart;

public class GraphActivity extends AppCompatActivity {

    EditText xValue, yValue;
    Button insertBtn;
    LineChart lineChart;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_graph);

        xValue = findViewById(R.id.xTextView);
        yValue = findViewById(R.id.yTextView);
        insertBtn = findViewById(R.id.btnInsert);
        lineChart = findViewById(R.id.lineChartView);
    }


}
