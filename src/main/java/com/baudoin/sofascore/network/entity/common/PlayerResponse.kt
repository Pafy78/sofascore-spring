package com.baudoin.sofascore.network.entity.common

import com.baudoin.sofascore.network.entity.player.TransfertResponse
import com.baudoin.sofascore.network.manager.FootballNetworkManager
import com.baudoin.sofascore.network.manager.base.CallBackManagerWithError

class PlayerResponse {

    val id: Int? = null
    val name: String? = null
    val slug: String? = null
    var transfert: TransfertResponse? = null

    init {
        FootballNetworkManager.getPlayerTransfert(this.id.toString(), object: CallBackManagerWithError<TransfertResponse>{
            override fun onSuccess(response: TransfertResponse) {
                this@PlayerResponse.transfert = response
            }

            override fun onError(pError: String) {

            }

        })
    }
}