package com.example.tutorial1;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChartFragment extends Fragment {


    private BarChart mBarChart;
    private PieChart mPieChart;

    public ChartFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_chart, container, false);

        mBarChart = view.findViewById(R.id.barChart);
        mPieChart = view.findViewById(R.id.pieChart);

        getGrowthChart(getArguments().getString("method"));

        return view;
    }

    private void getGrowthChart(final String method)
    {
        Call<List<GraphActivity>> call = ApiClient.getApiClient().create(ApiInterface.class).getGrowthInfo();


        call.enqueue(new Callback<List<GraphActivity>>() {
            @Override
            public void onResponse(Call<List<GraphActivity>> call, Response<List<GraphActivity>> response) {

                if(response.body()!=null)
                {
                    if(method.equals("bars"))
                    {
                        List<BarEntry> barEntries = new ArrayList<>();

                        for(GraphActivity growth : response.body())
                        {
                            barEntries.add(new BarEntry(growth.getYear(),growth.getGrowth_Rate()));

                        }

                        BarDataSet barDataSet = new BarDataSet(barEntries, "Growth");
                        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);

                        BarData barData = new BarData(barDataSet);
                        barData.setBarWidth(0.9f);

                        mBarChart.setVisibility(View.VISIBLE);
                        mBarChart.animateY(5000);
                        mBarChart.setData(barData);
                        mBarChart.setFitBars(true);

                        Description description = new Description();
                        description.setText("Weight Change");
                        mBarChart.setDescription(description);
                        mBarChart.invalidate();


                    }

                    else if(method.equals("pie"))
                    {

                        List<PieEntry> pieEntries = new ArrayList<>();

                        for(GraphActivity growth : response.body())
                        {
                            pieEntries.add(new PieEntry(growth.getGrowth_Rate(),Integer.toString(growth.getYear())));

                        }

                        mPieChart.setVisibility(View.VISIBLE);
                        mPieChart.animateXY(5000, 5000);

                        PieDataSet pieDataSet = new PieDataSet(pieEntries, "Weight Change");
                        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);

                        PieData pieData = new PieData(pieDataSet);

                        mPieChart.setData(pieData);

                        Description description = new Description();
                        description.setText("Weight Change");
                        mPieChart.setDescription(description);
                        mPieChart.invalidate();

                    }

                }




            }

            @Override
            public void onFailure(Call<List<GraphActivity>> call, Throwable t) {

            }
        });

    }

}
