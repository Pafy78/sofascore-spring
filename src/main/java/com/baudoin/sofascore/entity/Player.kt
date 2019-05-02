package com.baudoin.sofascore.entity

import com.baudoin.sofascore.network.entity.common.PlayerResponse
import com.baudoin.sofascore.network.entity.player.TransfertResponse
import com.baudoin.sofascore.network.manager.FootballNetworkManager
import com.baudoin.sofascore.network.manager.base.CallBackManager
import com.baudoin.sofascore.network.manager.base.CallBackManagerWithError

class Player {

    var id : Int? = null
    var name: String?
    var value: Int? = null

    constructor(player: PlayerResponse?){
        this.id = player?.id
        this.name = player?.name
    }

    fun setValue(pCallBack: CallBackManager){
        FootballNetworkManager.getPlayerTransfert(this.id.toString(), object: CallBackManagerWithError<TransfertResponse> {
            override fun onSuccess(response: TransfertResponse) {
                this@Player.value = valueStringToInt(response.playerValue)
                pCallBack.onResponse(null)
            }

            override fun onError(pError: String) {
                pCallBack.onResponse(pError)
            }

        })
    }

    private fun valueStringToInt(pValue: String?): Int{
        if(pValue == null){
            return 0
        }
        val millionValue = pValue.substringBefore("M €")
        if(millionValue == pValue){
            val value = pValue.substringBefore("k €")
            return (value.toFloat() * 1000).toInt()
        }
        return (millionValue.toFloat() * 1000000).toInt()
    }
}