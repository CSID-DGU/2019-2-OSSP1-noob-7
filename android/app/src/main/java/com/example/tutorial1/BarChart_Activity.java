package com.example.tutorial1;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;

public class BarChart_Activity extends AppCompatActivity {

    BarChart barChart;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_chart);

        barChart = findViewById(R.id.mp_BarChart);

        String strColor = "#004489";

        BarDataSet barDataSet1 = new BarDataSet(dataValues1(), "WEIGHT");
        barDataSet1.setColor(Color.parseColor(strColor));

        BarData barData = new BarData();
        barData.addDataSet(barDataSet1);

        barChart.setData(barData);
        barChart.invalidate();

    }

    private ArrayList<BarEntry> dataValues1(){

        ArrayList<BarEntry> dataVals = new ArrayList<>();

        dataVals.add(new BarEntry(1, 120));
        dataVals.add(new BarEntry(2, 110));
        dataVals.add(new BarEntry(3, 90));
        dataVals.add(new BarEntry(4, 85));
        dataVals.add(new BarEntry(5, 80));
        dataVals.add(new BarEntry(6, 60));
        dataVals.add(new BarEntry(7, 50));

        return dataVals;

    }
}
