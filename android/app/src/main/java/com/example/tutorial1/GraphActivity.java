package com.example.tutorial1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class GraphActivity extends AppCompatActivity {

    EditText xValue, yValue;
    Button insertBtn;
    LineChart lineChart;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference myRef;

    LineDataSet lineDataSet = new LineDataSet(null,null);
    ArrayList<ILineDataSet> iLineDataSets = new ArrayList<>();

    LineData lineData;

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

                myRef.child(id).setValue(dataPoint);

                retrieveData();

            }
        });

    }

    private void retrieveData() {

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                ArrayList<Entry> dataVals = new ArrayList<Entry>();

                if(dataSnapshot.hasChildren()){

                    for(DataSnapshot myDataSnapshot : dataSnapshot.getChildren()){

                        DataPoint dataPoint = myDataSnapshot.getValue(DataPoint.class);
                        dataVals.add(new Entry(dataPoint.getxValue(), dataPoint.getyValue()));

                    }

                    showChart(dataVals);
                }
                else {

                    lineChart.clear();
                    lineChart.invalidate();

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void showChart(ArrayList<Entry> dataVals) {

        lineDataSet.setValues(dataVals);

        lineDataSet.setLabel("DataSet 1");

        iLineDataSets.clear();

        iLineDataSets.add(lineDataSet);
        lineData = new LineData(iLineDataSets);

    }


}
