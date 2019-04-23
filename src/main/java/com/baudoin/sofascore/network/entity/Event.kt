package com.baudoin.sofascore.network.entity

import com.google.gson.JsonObject

class Event {
    lateinit var sport: Sport
    lateinit var homeTeam: Team
    lateinit var awayTeam: Team
    val winnerCode: Int? = null
    val id: Int? = null
    val name: String? = null
    lateinit var homeScore: Score
    lateinit var awayScore: Score
    lateinit var status: Status
}