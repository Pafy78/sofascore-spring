package com.steamulo.gestiontpe.network

import com.steamulo.gestiontpe.BuildConfig
import com.google.gson.GsonBuilder
import com.steamulo.gestiontpe.network.entity.response.LoginResponse
import com.steamulo.gestiontpe.network.manager.*
import okhttp3.Interceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.OkHttpClient
import okhttp3.Request

object HttpUtils {

    private val gson = GsonBuilder().setPrettyPrinting().create()

    private var bearerKey: String? = null

    private const val PATH_API = BuildConfig.BASE_URL + "/api/"

    lateinit var siteNetworkManager: SiteNetworkManagerInterface
    lateinit var userNetworkManager: UserNetworkManagerInterface
    lateinit var TPENetworkManager: TPENetworkManagerInterface
    lateinit var deliveryNetworkManager: DeliveryNetworkManagerInterface

    var retrofit = Retrofit.Builder()
        .baseUrl(this.PATH_API)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()

    fun setBearerKeyAndInitHttpUtils(pLoginResponse: LoginResponse?){
        this.bearerKey = pLoginResponse?.token
        setHeader()
        setNetworkManagerInterfaces()
    }

    private fun setHeader() {
        val okHttpClient = OkHttpClient().newBuilder().addNetworkInterceptor(object : Interceptor {
            override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
                var request: Request? = null
                if (bearerKey != null) {

                    val original = chain.request()
                    val requestBuilder = original.newBuilder()
                        .addHeader("Authorization", bearerKey)
                    request = requestBuilder.build()
                }
                return chain.proceed(request)
            }
        }).build()

        this.retrofit = Retrofit.Builder()
            .baseUrl(this.PATH_API)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()
    }

    private fun setNetworkManagerInterfaces(){
        this.siteNetworkManager = this.retrofit.create(SiteNetworkManagerInterface::class.java)
        this.userNetworkManager = this.retrofit.create(UserNetworkManagerInterface::class.java)
        this.TPENetworkManager = this.retrofit.create(TPENetworkManagerInterface::class.java)
        this.deliveryNetworkManager = this.retrofit.create(DeliveryNetworkManagerInterface::class.java)
    }
}