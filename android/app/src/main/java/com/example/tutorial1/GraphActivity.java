package com.example.tutorial1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.LineChart;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class GraphActivity extends AppCompatActivity {

    EditText xValue, yValue;
    Button insertBtn;
    LineChart lineChart;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference myRef;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_graph);

        xValue = findViewById(R.id.xTextView);
        yValue = findViewById(R.id.yTextView);
        insertBtn = findViewById(R.id.btnInsert);
        lineChart = findViewById(R.id.lineChartView);

        firebaseDatabase = FirebaseDatabase.getInstance();

        myRef = firebaseDatabase.getReference("ChartValues");

        insertData();
    }

    private void insertData() {

        insertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String id = myRef.push().getKey();

                int x = Integer.parseInt(xValue.getText().toString());

                int y = Integer.parseInt(yValue.getText().toString());

                DataPoint dataPoint = new DataPoint(x,y);

            }
        });

    }


}
