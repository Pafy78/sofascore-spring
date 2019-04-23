package com.steamulo.gestiontpe.network.manager

import com.steamulo.gestiontpe.entity.Delivery
import com.steamulo.gestiontpe.network.entity.request.ValidateDeliveryRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface DeliveryNetworkManagerInterface{

    @GET("deliveries/{idDelivery}")
    fun getDelivery(@Path("idDelivery") idDelivery: Int) : Call<Delivery>

    @PUT("deliveries/{idDelivery}/validate")
    fun validateDelivery(@Path("idDelivery") idDelivery: Int, @Body validateDeliveryRequest: ValidateDeliveryRequest) : Call<Delivery>
}