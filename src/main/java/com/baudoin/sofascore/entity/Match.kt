package com.baudoin.sofascore.entity

import com.baudoin.sofascore.network.HttpUtils
import com.baudoin.sofascore.network.entity.lineup.MatchLineupResponse
import com.baudoin.sofascore.network.manager.FootballNetworkManager
import com.baudoin.sofascore.network.manager.base.CallBackManager
import com.baudoin.sofascore.network.manager.base.CallBackManagerWithError

class Match(val pId: String) {

    var homeTeam: Team = Team()
    var awayTeam: Team = Team()

    fun getMatch(pCallBack: CallBackManager){
        HttpUtils.setNetworkManagerInterfaces()
        FootballNetworkManager.getEvent(this.pId, object: CallBackManagerWithError<MatchLineupResponse> {
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