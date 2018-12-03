package com.example.alberto.smart;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface SensorService {

    @GET("sensor")
    Call<List<Sensor>> getSensor();





}
