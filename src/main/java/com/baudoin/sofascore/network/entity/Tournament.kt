package com.baudoin.sofascore.network.entity

import com.google.gson.JsonObject

class Tournament {

    lateinit var tournament: JsonObject
    lateinit var events: List<Event>
}