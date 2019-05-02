package com.baudoin.sofascore.entity

import com.baudoin.sofascore.network.entity.lineup.LineupResponse
import com.baudoin.sofascore.network.manager.base.CallBackManager

class Team {

    private val teamCount = 11
    lateinit var players: List<Player>
    lateinit var name : String
    var loading: Boolean = false

    fun setPlayers(lineup: LineupResponse?, pCallBack: CallBackManager) {
        this.players = emptyList()
        if(lineup?.lineupsSorted?.count() == 0){
            pCallBack.onResponse("No lineups")
            return
        }
        var i = 0
        while (i < this.teamCount)
        {
            if(!this.loading){
                this.loading = true

                val playerLineupResponse = lineup?.lineupsSorted?.get(i)
                val player = Player(playerLineupResponse?.player)
                player.setValue(object : CallBackManager {
                    override fun onResponse(pError: String?) {
                        this@Team.players += player
                        i++
                        this@Team.loading = false
                    }
                })
            }
        }
        pCallBack.onResponse(null)
    }

    fun getAvgValuePlayer(): Int{
        var number = 0
        this.players.forEachIndexed { index, player ->
            number += player.value?:0
        }
        return number / this.teamCount
    }
}