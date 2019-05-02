package com.baudoin.sofascore.entity

import com.baudoin.sofascore.network.HttpUtils
import com.baudoin.sofascore.network.entity.event.EventResponse
import com.baudoin.sofascore.network.entity.event.EventsResponse
import com.baudoin.sofascore.network.manager.FootballNetworkManager
import com.baudoin.sofascore.network.manager.base.CallBackManager
import com.baudoin.sofascore.network.manager.base.CallBackManagerWithError

class DayMatch(val date: String, val tournamentName: String) {

    var matchs : List<Match> = emptyList()

    fun getMatchs(pCallBack: CallBackManager){
        HttpUtils.setNetworkManagerInterfaces()
        FootballNetworkManager.getEvents(this.date, object: CallBackManagerWithError<EventsResponse> {
            override fun onSuccess(response: EventsResponse) {
                val tournament = response.sportItem.tournaments.find { tournamentResponse -> tournamentResponse.tournament.name == this@DayMatch.tournamentName }
                if(tournament == null){
                    pCallBack.onResponse("Tournament not found")
                    return
                }
                setSetMatchs(tournament.events, object: CallBackManager{
                    override fun onResponse(pError: String?) {
                        if(pError != null){
                            pCallBack.onResponse(pError)
                            return
                        }
                        pCallBack.onResponse(null)
                    }

                })
            }

            override fun onError(pError: String) {
                pCallBack.onResponse(pError)
            }

        })
    }

    private fun setSetMatchs(pListEventResponse: List<EventResponse>, pCallBack: CallBackManager){
        val match = Match(pListEventResponse.first().id.toString())
        match.getMatch(object: CallBackManager{
            override fun onResponse(pError: String?) {
                if(pError != null){
                    pCallBack.onResponse(pError)
                }
                this@DayMatch.matchs += match
                if(pListEventResponse.count() == 1){
                    pCallBack.onResponse(null)
                    return
                }
                setSetMatchs(pListEventResponse.subList(1,pListEventResponse.count()), pCallBack)
            }
        })
    }

    fun displayMatchs(): String{
        var string = ""
        this.matchs.forEachIndexed { index, match ->
            string += "${match.displayTeamValues()}<br/><br/>"
        }
        return string
    }
}