package com.baudoin.sofascore.network.entity

import com.google.gson.JsonObject

class SportItemResponse {

    lateinit var sport: SportResponse
    lateinit var tournaments: List<TournamentResponse>
}