package com.baudoin.sofascore.entity

import com.baudoin.sofascore.network.HttpUtils
import com.baudoin.sofascore.network.entity.event.EventResponse
import com.baudoin.sofascore.network.entity.event.EventsResponse
import com.baudoin.sofascore.network.manager.FootballNetworkManager
import com.baudoin.sofascore.network.manager.base.CallBackManager
import com.baudoin.sofascore.network.manager.base.CallBackManagerWithError

class DayMatch(val date: String, val tournamentPosition: Int) {

    var matchs : List<Match> = emptyList()

    fun getMatchs(pCallBack: CallBackManager){
        HttpUtils.setNetworkManagerInterfaces()
        FootballNetworkManager.getEvents(this.date, object: CallBackManagerWithError<EventsResponse> {
            override fun onSuccess(response: EventsResponse) {
                setSetMatchs(response.sportItem.tournaments.get(this@DayMatch.tournamentPosition).events, object: CallBackManager{
                    override fun onResponse(pError: String?) {
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