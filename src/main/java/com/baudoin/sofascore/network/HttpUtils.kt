package com.baudoin.sofascore.network

import com.baudoin.sofascore.network.manager.FootballNetworkManagerInterface
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object HttpUtils {

    private val gson = GsonBuilder().setPrettyPrinting().create()

    private const val PATH_API = "https://www.sofascore.com/"

    lateinit var footballNetworkManager: FootballNetworkManagerInterface

    var retrofit = Retrofit.Builder()
        .baseUrl(PATH_API)
            .client(OkHttpClient.Builder()
                    .readTimeout(180, TimeUnit.SECONDS)
                    .connectTimeout(180, TimeUnit.SECONDS)
                    .build())
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    fun setNetworkManagerInterfaces(){
        footballNetworkManager = retrofit.create(FootballNetworkManagerInterface::class.java)
    }
}