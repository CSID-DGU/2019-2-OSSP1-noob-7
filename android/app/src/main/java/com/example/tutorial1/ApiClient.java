package com.example.tutorial1;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static final String BASE_URL = "http://kym0598.dothome.co.kr/get_growth_info.php";
            //"http://10.0.2.2/growthinfo/scripts/";
    private static Retrofit retrofit;


    public static Retrofit getApiClient(){

        if(retrofit == null)
        {
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();


        }

        return retrofit;

    }
}
