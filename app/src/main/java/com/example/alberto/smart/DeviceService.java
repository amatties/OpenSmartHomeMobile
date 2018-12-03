package com.example.alberto.smart;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface DeviceService {

    @GET("light")
    Call<List<Device>> getDevice();


}
