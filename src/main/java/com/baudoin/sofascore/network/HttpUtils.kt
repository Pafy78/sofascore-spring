package com.baudoin.sofascore.network

import com.baudoin.sofascore.network.manager.FootballNetworkManagerInterface
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object HttpUtils {

    private val gson = GsonBuilder().setPrettyPrinting().create()

    private const val PATH_API = "https://www.sofascore.com/"

    lateinit var footballNetworkManager: FootballNetworkManagerInterface

    var retrofit = Retrofit.Builder()
        .baseUrl(PATH_API)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    fun setNetworkManagerInterfaces(){
        footballNetworkManager = retrofit.create(FootballNetworkManagerInterface::class.java)
    }
}