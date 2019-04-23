package com.baudoin.sofascore

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object FootballNetworkManager: BaseNetworkManager() {

    fun getEvents(pDate: String, pCallBack: CallBackManagerWithError<FootballResponse>) {
        HttpUtils.footballNetworkManager.getEvents(pDate).enqueue(object: Callback<FootballResponse> {
            override fun onFailure(call: Call<FootballResponse>, t: Throwable) {
                pCallBack.onError(t.message.toString())
            }

            override fun onResponse(call: Call<FootballResponse>, response: Response<FootballResponse>) {
                val delivery = checkOnResponseError(call, response, pCallBack) ?: return

                pCallBack.onSuccess(delivery)
            }
        })
    }
}