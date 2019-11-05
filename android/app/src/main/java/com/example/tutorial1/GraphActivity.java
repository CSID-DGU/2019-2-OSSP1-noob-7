package com.example.tutorial1;

import com.google.gson.annotations.SerializedName;

public class GraphActivity {

    @SerializedName("year")

    private int Year;

    @SerializedName("growth_rate")
    private float Growth_Rate;


    public int getYear() {
        return Year;
    }

    public float getGrowth_Rate() {
        return Growth_Rate;
    }
}
