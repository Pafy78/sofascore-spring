package com.baudoin.sofascore.network.entity.event

import com.baudoin.sofascore.network.entity.common.ScoreResponse
import com.baudoin.sofascore.network.entity.common.SportResponse
import com.baudoin.sofascore.network.entity.common.TeamResponse

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
    lateinit var playerMatchInfo : PlayerMatchInfoResponse
}