package com.baudoin.sofascore.network.manager

import com.baudoin.sofascore.network.entity.event.EventsResponse
import com.baudoin.sofascore.network.HttpUtils
import com.baudoin.sofascore.network.entity.event.EventDetailsResponse
import com.baudoin.sofascore.network.entity.event.EventResponse
import com.baudoin.sofascore.network.entity.lineup.MatchLineupResponse
import com.baudoin.sofascore.network.entity.player.TransfertResponse
import com.baudoin.sofascore.network.manager.base.BaseNetworkManager
import com.baudoin.sofascore.network.manager.base.CallBackManagerWithError
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object FootballNetworkManager: BaseNetworkManager() {

    fun getEvents(pDate: String, pCallBack: CallBackManagerWithError<EventsResponse>) {
        HttpUtils.footballNetworkManager.getEvents(pDate).enqueue(object: Callback<EventsResponse> {
            override fun onFailure(call: Call<EventsResponse>, t: Throwable) {
                pCallBack.onError(t.message.toString())
            }

            override fun onResponse(call: Call<EventsResponse>, response: Response<EventsResponse>) {
                val events = checkOnResponseError(call, response, pCallBack) ?: return

                pCallBack.onSuccess(events)
            }
        })
    }

    fun getEventLineups(pEventID: String, pCallBack: CallBackManagerWithError<MatchLineupResponse>){
        HttpUtils.footballNetworkManager.getEventLineups(pEventID).enqueue(object: Callback<MatchLineupResponse>{
            override fun onFailure(call: Call<MatchLineupResponse>, t: Throwable) {
                pCallBack.onError(t.message.toString())
            }

            override fun onResponse(call: Call<MatchLineupResponse>, response: Response<MatchLineupResponse>) {
                val matchLineup = checkOnResponseError(call, response, pCallBack) ?: return

                pCallBack.onSuccess(matchLineup)
            }

        })
    }

    fun getEvent(pEventID: String, pCallBack: CallBackManagerWithError<EventDetailsResponse>){
        HttpUtils.footballNetworkManager.getEvent(pEventID).enqueue(object: Callback<EventDetailsResponse>{
            override fun onFailure(call: Call<EventDetailsResponse>, t: Throwable) {
                pCallBack.onError(t.message.toString())
            }

            override fun onResponse(call: Call<EventDetailsResponse>, response: Response<EventDetailsResponse>) {
                val event = checkOnResponseError(call, response, pCallBack) ?: return

                pCallBack.onSuccess(event)
            }

        })
    }

    fun getPlayerTransfert(pPlayerID: String, pCallBack: CallBackManagerWithError<TransfertResponse>){
        HttpUtils.footballNetworkManager.getPlayerTransfer(pPlayerID).enqueue(object: Callback<TransfertResponse>{
            override fun onFailure(call: Call<TransfertResponse>, t: Throwable) {
                pCallBack.onError(t.message.toString())
            }

            override fun onResponse(call: Call<TransfertResponse>, response: Response<TransfertResponse>) {
                val playerTransfert = checkOnResponseError(call, response, pCallBack) ?: return

                pCallBack.onSuccess(playerTransfert)
            }

        })
    }
}