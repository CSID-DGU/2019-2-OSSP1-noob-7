package com.example.tutorial1;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface
{

    @GET("get_growth_info.php")
    Call<List<Growth>> getGrowthInfo();

}
