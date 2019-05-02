package com.baudoin.sofascore.network.manager

import com.baudoin.sofascore.network.entity.event.EventDetailsResponse
import com.baudoin.sofascore.network.entity.event.EventResponse
import com.baudoin.sofascore.network.entity.event.EventsResponse
import com.baudoin.sofascore.network.entity.event.SportItemResponse
import com.baudoin.sofascore.network.entity.lineup.MatchLineupResponse
import com.baudoin.sofascore.network.entity.player.TransfertResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface FootballNetworkManagerInterface{

    @GET("/football//{date}/json?_=155596562")
    fun getEvents(@Path("date") pDate: String) : Call<EventsResponse>

    @GET("/event/{eventID}/lineups/json?_=155638462")
    fun getEventLineups(@Path("eventID") pEventID: String) : Call<MatchLineupResponse>

    @GET("/event/{eventID}/json?_=155638462")
    fun getEvent(@Path("eventID") pEventID: String) : Call<EventDetailsResponse>

    @GET("/player/{playerID}/transfers/json?_=155638580")
    fun getPlayerTransfer(@Path("playerID") pPlayerID: String) : Call<TransfertResponse>

    @GET("/player/{playerID}/events/json?_=155638580")
    fun getPlayerEvents(@Path("playerID") pPlayerID: String) : Call<SportItemResponse>
}