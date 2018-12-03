package com.example.alberto.smart;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface LockService {


    @GET("lock")
    Call<List<Lock>> getLock();


}
