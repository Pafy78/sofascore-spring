package com.baudoin.sofascore.network.entity.event

import com.baudoin.sofascore.network.entity.common.SportResponse

class SportItemResponse {

    lateinit var sport: SportResponse
    lateinit var tournaments: List<TournamentResponse>
}