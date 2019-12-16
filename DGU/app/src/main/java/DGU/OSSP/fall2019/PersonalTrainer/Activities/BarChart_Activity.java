package DGU.OSSP.fall2019.PersonalTrainer.Activities;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;

import DGU.OSSP.fall2019.DGU.R;

public class BarChart_Activity extends AppCompatActivity {

    BarChart barChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_chart);

        Button btn_Meal;

        btn_Meal = findViewById(R.id.btnMeal);

        barChart = findViewById(R.id.mp_BarChart);

        String strColor = "#004489";

        BarDataSet barDataSet1 = new BarDataSet(dataValues1(), "WEIGHT");
        barDataSet1.setColor(Color.parseColor(strColor));

        BarData barData = new BarData();
        barData.addDataSet(barDataSet1);

        barChart.setData(barData);
        barChart.invalidate();

        btn_Meal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(BarChart_Activity.this, MainActivity.class);

                startActivity(intent);

            }
        });

    }

    private ArrayList<BarEntry> dataValues1(){

        ArrayList<BarEntry> dataVals = new ArrayList<>();

        dataVals.add(new BarEntry(1, 100));
        dataVals.add(new BarEntry(2, 90));
//        dataVals.add(new BarEntry(3, 80));
//        dataVals.add(new BarEntry(4, 70));
//        dataVals.add(new BarEntry(5, 80));
//        dataVals.add(new BarEntry(6, 60));
//        dataVals.add(new BarEntry(7, 50));

        return dataVals;

    }
}
