package com.baudoin.sofascore.network.entity.event

import com.google.gson.JsonObject

class TournamentResponse {

    lateinit var tournament: JsonObject
    lateinit var events: List<EventResponse>
}