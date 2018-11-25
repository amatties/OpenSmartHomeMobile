package com.example.alberto.smart;

/**
 * Created by Alberto on 20/06/2018.
 */

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;



public interface LightService {


    @GET("list")
    Call<List<Light>> getLight();




}
