package com.baudoin.sofascore.network.entity.lineup

import com.baudoin.sofascore.network.entity.common.PlayerResponse

class PlayerLineupResponse {

    val captain: Boolean = false
    val player: PlayerResponse? = null
    val position: Int? = null
    val positionName: String? = null
    val substitute: Boolean = true
}