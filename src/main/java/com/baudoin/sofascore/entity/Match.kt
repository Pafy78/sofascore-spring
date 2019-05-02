package com.baudoin.sofascore.entity

import com.baudoin.sofascore.network.HttpUtils
import com.baudoin.sofascore.network.entity.event.EventDetailsResponse
import com.baudoin.sofascore.network.entity.event.EventResponse
import com.baudoin.sofascore.network.entity.lineup.MatchLineupResponse
import com.baudoin.sofascore.network.manager.FootballNetworkManager
import com.baudoin.sofascore.network.manager.base.CallBackManager
import com.baudoin.sofascore.network.manager.base.CallBackManagerWithError
import java.lang.Float.min

class Match(val pId: String) {

    var homeTeam: Team = Team()
    var awayTeam: Team = Team()

    fun getMatch(pCallBack: CallBackManager){
        HttpUtils.setNetworkManagerInterfaces()
        FootballNetworkManager.getEvent(this.pId, object: CallBackManagerWithError<EventDetailsResponse>{
            override fun onSuccess(response: EventDetailsResponse) {
                this@Match.homeTeam.name = response.event.homeTeam.name.toString()
                this@Match.awayTeam.name = response.event.awayTeam.name.toString()
                setLineups(object: CallBackManager{
                    override fun onResponse(pError: String?) {
                        pCallBack.onResponse(pError)
                    }

                })
            }

            override fun onError(pError: String) {
                pCallBack.onResponse(pError)
            }
        })
    }

    fun displayTeamValues(): String{
        val totalValue = this.homeTeam.getAvgValuePlayer() + this.awayTeam.getAvgValuePlayer()
        var percentHome = (this.homeTeam.getAvgValuePlayer() * 100.0f) / totalValue
        var percentAway = (this.awayTeam.getAvgValuePlayer() * 100.0f) / totalValue
        var halfPercentNull = min(percentAway, percentHome) * 0.33
        percentHome -= halfPercentNull.toFloat()
        percentAway -= halfPercentNull.toFloat()
        halfPercentNull *= 2
        val stringHome = "${this.homeTeam.name} : $percentHome%"
        val stringNull = " - NULL : $halfPercentNull% - "
        val stringAway = "${this.awayTeam.name} : $percentAway%"
        return "$stringHome$stringNull$stringAway"
    }

    private fun setLineups(pCallBack: CallBackManager){
        FootballNetworkManager.getEventLineups(this.pId, object: CallBackManagerWithError<MatchLineupResponse> {
            override fun onSuccess(response: MatchLineupResponse) {
                this@Match.homeTeam.setPlayers(response.homeTeam, object: CallBackManager{
                    override fun onResponse(pError: String?) {
                        this@Match.awayTeam.setPlayers(response.awayTeam, object: CallBackManager{
                            override fun onResponse(pError: String?) {
                                pCallBack.onResponse(null)
                            }
                        })
                    }
                })
            }
            override fun onError(pError: String) {
                pCallBack.onResponse(pError)
            }
        })
    }
}