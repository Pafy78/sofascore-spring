package com.baudoin.sofascore.network.entity

import com.google.gson.JsonObject

class SportItem {

    lateinit var sport: JsonObject
    lateinit var tournaments: MutableList<Tournament>
}