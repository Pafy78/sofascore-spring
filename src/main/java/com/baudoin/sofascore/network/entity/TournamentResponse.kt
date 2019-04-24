package com.baudoin.sofascore.network.entity

import com.google.gson.JsonObject

class TournamentResponse {

    lateinit var tournament: JsonObject
    lateinit var events: List<Event>
}