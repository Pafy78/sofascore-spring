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

        val lineupWithtoutSubsitute = lineup?.lineupsSorted?.filterIndexed { index, playerLineupResponse ->
            playerLineupResponse.substitute == false
        }

        var i = 0
        while (i < lineupWithtoutSubsitute?.count()?:this.teamCount)
        {
            if(!this.loading){
                this.loading = true

                val player = Player(lineupWithtoutSubsitute?.get(i)?.player)
                player.setValue(object : CallBackManager {
                    override fun onResponse(pError: String?) {
                        if(this@Team.players.find { p -> p.id == player.id } == null){
                            this@Team.players += player
                            i++
                            if(i == lineupWithtoutSubsitute?.count()?:this@Team.teamCount){
                                pCallBack.onResponse(null)
                            }
                        }
                        this@Team.loading = false
                    }
                })
            }
        }
    }

    fun getPlayersValueInTeam(): Float{
        var number = 0f
        val maxRating = getMaxRatingPlayer()
        val minRating = getMinRatingPlayer()
        this.players.forEachIndexed { index, player ->
            number += (player.value?:0) * getPlayerTeamRatingPourcent(maxRating, minRating, player.rating)
        }
        return number / this.players.count()
    }

    fun getTeamValue(): Float {
        return getPlayersValueInTeam() * getPlayersRatingAvg()
    }

    private fun getPlayerTeamRatingPourcent(pMaxRating : Float, pMinRating: Float, pPlayerRating: Float): Float{
        val multiplicator = 100 / (pMaxRating - pMinRating)
        val playerRatingInRange = pPlayerRating - pMinRating
        val value = (playerRatingInRange * multiplicator) / 100
        return value
    }

    private fun getPlayersRatingAvg(): Float {
        var count = 0f
        this.players.forEachIndexed { index, player ->
            count += player.rating
        }
        return count / players.count()
    }

    private fun getMaxRatingPlayer(): Float {
        var maxRating = 0f
        this.players.forEachIndexed { index, player ->
            if(player.rating > maxRating){
                maxRating = player.rating
            }
        }
        return maxRating
    }

    private fun getMinRatingPlayer(): Float {
        var minRating = 10f
        this.players.forEachIndexed { index, player ->
            if(player.rating < minRating){
                minRating = player.rating
            }
        }
        return minRating
    }
}