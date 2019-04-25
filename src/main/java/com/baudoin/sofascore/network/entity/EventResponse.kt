package com.baudoin.sofascore.network.entity

class EventResponse {
    lateinit var sport: SportResponse
    lateinit var homeTeam: TeamResponse
    lateinit var awayTeam: TeamResponse
    val winnerCode: Int? = null
    val id: Int? = null
    val name: String? = null
    lateinit var homeScore: ScoreResponse
    lateinit var awayScore: ScoreResponse
    lateinit var status: StatusResponse
}