package com.baudoin.sofascore.network.entity

import com.google.gson.JsonObject

class SportItem {

    lateinit var sport: Sport
    lateinit var tournaments: List<Tournament>
}