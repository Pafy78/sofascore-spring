package com.baudoin.sofascore;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface FootballService {

    @GET("/football//{date}/json?_=155596562")
    public Call<FootballResponse> getEvents(@Path("date") String date);
}
