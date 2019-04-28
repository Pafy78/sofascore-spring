package com.baudoin.sofascore.entity

import com.baudoin.sofascore.network.HttpUtils
import com.baudoin.sofascore.network.entity.event.EventsResponse
import com.baudoin.sofascore.network.manager.FootballNetworkManager
import com.baudoin.sofascore.network.manager.base.CallBackManager
import com.baudoin.sofascore.network.manager.base.CallBackManagerWithError
import java.util.*

class DayMatch(val date: String) {

    var matchs : List<Match> = emptyList()

    fun getMatchs(pCallBack: CallBackManager){
        HttpUtils.setNetworkManagerInterfaces()
        FootballNetworkManager.getEvents(this.date, object: CallBackManagerWithError<EventsResponse> {
            override fun onSuccess(response: EventsResponse) {
                val match = Match(response.sportItem.tournaments.get(0).events.get(0).id.toString())
                match.getMatch(object: CallBackManager{
                    override fun onResponse(pError: String?) {
                        if(pError != null){
                            pCallBack.onResponse(pError)
                        }
                        this@DayMatch.matchs += match
                        pCallBack.onResponse(null)
                    }
                })
            }

            override fun onError(pError: String) {
                pCallBack.onResponse(pError)
            }

        })
    }

    fun displayMatchs(): String{
        var string = ""
        this.matchs.forEachIndexed { index, match ->
            string += "${match.displayTeamValues()}\n"
        }
        return string
    }
}