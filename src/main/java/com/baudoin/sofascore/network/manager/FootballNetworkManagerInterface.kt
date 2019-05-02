package com.baudoin.sofascore.network.manager

import com.baudoin.sofascore.network.entity.event.*
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

    @GET("/u-tournament/{tournamentId}/season/{seasonId}/matches/week/{timeStart}/{timeEnd}?_=155682094")
    fun getTournamentEvents(
            @Path("tournamentId") tournamentId: Int,
            @Path("seasonId") seasonId: Int,
            @Path("timeStart") timeStart: Long,
            @Path("timeEnd") timeEnd: Long)
            : Call<TournamentEventsResponse>

}