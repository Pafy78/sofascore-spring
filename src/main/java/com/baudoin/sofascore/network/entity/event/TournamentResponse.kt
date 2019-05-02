package com.baudoin.sofascore.network.entity.event

import com.google.gson.JsonObject

class TournamentResponse {

    lateinit var tournament: TournamentObjectResponse
    lateinit var events: List<EventResponse>
    lateinit var season: Season
}