package com.baudoin.sofascore.network.entity

import com.google.gson.JsonObject

class SportItemResponse {

    lateinit var sport: Sport
    lateinit var tournaments: List<Tournament>
}