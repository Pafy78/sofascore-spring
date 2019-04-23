package com.baudoin.sofascore

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface FootballNetworkManagerInterface{

    @GET("/football//{date}/json?_=155596562")
    fun getEvents(@Path("date") pDate: String) : Call<FootballResponse>
}