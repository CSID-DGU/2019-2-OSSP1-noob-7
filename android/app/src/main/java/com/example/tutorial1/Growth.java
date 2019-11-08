package com.example.tutorial1;

import com.google.gson.annotations.SerializedName;

public class Growth {

    @SerializedName("year")
    private int Year;

    @SerializedName("growth_rate")
    private Float Growth_Rate;

    public int getYear() {
        return Year;
    }

    public Float getGrowth_Rate() {
        return Growth_Rate;
    }
}
