package com.baudoin.sofascore.network.entity.event

class PlayerMatchInfoResponse {

    val playedAt: Int? = null
    val playerId: Int? = null
    val rating: String? = null

    fun getRating(): Float? {
        try{
            return this.rating?.toFloat()?:0f
        }
        catch(e:Exception){
            return null
        }
    }
}
