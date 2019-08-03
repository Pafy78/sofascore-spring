package com.baudoin.sofascore.entity

import com.baudoin.sofascore.network.HttpUtils
import com.baudoin.sofascore.network.entity.event.EventResponse
import com.baudoin.sofascore.network.entity.event.EventsResponse
import com.baudoin.sofascore.network.manager.FootballNetworkManager
import com.baudoin.sofascore.network.manager.base.CallBackManager
import com.baudoin.sofascore.network.manager.base.CallBackManagerWithError

class DayMatch(val date: String, val tournamentName: String) {

    var matchs : List<Match> = emptyList()

    fun getMatchs(pCallBack: CallBackManager, pPeriodMatch: PeriodMatch? = null){
        HttpUtils.setNetworkManagerInterfaces()
        FootballNetworkManager.getEvents(this.date, object: CallBackManagerWithError<EventsResponse> {
            override fun onSuccess(response: EventsResponse) {
                val tournament = response.sportItem.tournaments.find { tournamentResponse -> tournamentResponse.tournament.name == this@DayMatch.tournamentName }
                if(tournament == null){
                    pCallBack.onResponse("Tournament not found")
                    return
                }
                setMatchs(tournament.events, object: CallBackManager{
                    override fun onResponse(pError: String?) {
                        if(pError != null){
                            pCallBack.onResponse(pError)
                            return
                        }
                        pCallBack.onResponse(null)
                    }

                }, pPeriodMatch)
            }

            override fun onError(pError: String) {
                pCallBack.onResponse(pError)
            }

        })
    }

    private fun setMatchs(pListEventResponse: List<EventResponse>, pCallBack: CallBackManager, pPeriodMatch: PeriodMatch? = null){
        if(pPeriodMatch != null){
            if(pPeriodMatch.alreadyHasMatch(pListEventResponse.first().id)){
                setMatchs(pListEventResponse.subList(1,pListEventResponse.count()), pCallBack)
            }
            if(pListEventResponse.count() == 0){
                pCallBack.onResponse(null)
                return
            }
        }
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
                setMatchs(pListEventResponse.subList(1,pListEventResponse.count()), pCallBack)
            }
        })
    }

    fun displayMatchs(): String?{
        if(this.matchs.isEmpty()){
            return null
        }
        var string = ""
        this.matchs.forEachIndexed { index, match ->
            if(!match.displayTeamValues().isEmpty()){
                string += "${match.displayTeamValues()}<br/><br/>"
            }
        }
        return string
    }
}