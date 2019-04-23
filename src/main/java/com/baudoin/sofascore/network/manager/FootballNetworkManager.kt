package com.baudoin.sofascore.network.manager

import com.baudoin.sofascore.network.entity.EventsResponse
import com.baudoin.sofascore.network.HttpUtils
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
                val delivery = checkOnResponseError(call, response, pCallBack) ?: return

                pCallBack.onSuccess(delivery)
            }
        })
    }
}