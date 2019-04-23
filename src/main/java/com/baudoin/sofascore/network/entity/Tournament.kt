package com.baudoin.sofascore.network.entity

import com.google.gson.JsonArray
import com.google.gson.JsonObject

class Tournament {

    lateinit var tournament: JsonObject
    lateinit var category: JsonObject
    lateinit var season: JsonObject
    lateinit var events: JsonArray
}