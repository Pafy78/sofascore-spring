package com.baudoin.sofascore.network.manager

import com.baudoin.sofascore.network.entity.event.EventsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface FootballNetworkManagerInterface{

    @GET("/football//{date}/json?_=155596562")
    fun getEvents(@Path("date") pDate: String) : Call<EventsResponse>
}