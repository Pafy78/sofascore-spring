package com.baudoin.sofascore

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object HttpUtils {

    private val gson = GsonBuilder().setPrettyPrinting().create()

    private const val PATH_API = "https://www.sofascore.com/"

    lateinit var footballNetworkManager: FootballNetworkManagerInterface

    var retrofit = Retrofit.Builder()
        .baseUrl(this.PATH_API)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    fun setNetworkManagerInterfaces(){
        this.footballNetworkManager = this.retrofit.create(FootballNetworkManagerInterface::class.java)
    }
}