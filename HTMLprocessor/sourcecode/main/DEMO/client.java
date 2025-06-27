package com.ritesh.demo.htmlfilewoker.client;

import java.util.Set;

import com.ritesh.demo.htmlfilewoker.model.URL;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface URLFeederService {

    @POST("/batch")
    public Call<Void> batchPublish(@Body Set<URL> urls);
}
