package com.baudoin.sofascore.network.entity.event

class TournamentResponse {

    lateinit var tournament: TournamentObjectResponse
    lateinit var events: List<EventResponse>
    lateinit var season: SeasonResponse
}