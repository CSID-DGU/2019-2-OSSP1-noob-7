package com.example.tutorial1;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;

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


                    }

                    else if(method.equals("pie"))
                    {


                    }

                }




            }

            @Override
            public void onFailure(Call<List<GraphActivity>> call, Throwable t) {

            }
        });

    }

}
