package com.steamulo.gestiontpe.network.manager

import com.steamulo.gestiontpe.CallBackManagerWithError
import com.steamulo.gestiontpe.entity.Delivery
import com.steamulo.gestiontpe.network.HttpUtils
import com.steamulo.gestiontpe.network.entity.request.ValidateDeliveryRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object DeliveryNetworkManager: BaseNetworkManager() {

    fun getDelivery(pId: Int, pCallBack: CallBackManagerWithError<Delivery>) {
        HttpUtils.deliveryNetworkManager.getDelivery(pId).enqueue(object: Callback<Delivery> {
            override fun onFailure(call: Call<Delivery>, t: Throwable) {
                pCallBack.onError(t.message.toString())
            }

            override fun onResponse(call: Call<Delivery>, response: Response<Delivery>) {
                val delivery = checkOnResponseError(call, response, pCallBack) ?: return

                pCallBack.onSuccess(delivery)
            }
        })
    }

    fun validateDelivery(pDelivery: Delivery, pCallBack: CallBackManagerWithError<Delivery>){
        HttpUtils.deliveryNetworkManager.validateDelivery(pDelivery.id, ValidateDeliveryRequest(pDelivery)).enqueue(object: Callback<Delivery>{
            override fun onFailure(call: Call<Delivery>, t: Throwable) {
                pCallBack.onError(t.message.toString())
            }

            override fun onResponse(call: Call<Delivery>, response: Response<Delivery>) {
                val delivery = checkOnResponseError(call, response, pCallBack) ?: return

                pCallBack.onSuccess(delivery)
            }
        })
    }
}