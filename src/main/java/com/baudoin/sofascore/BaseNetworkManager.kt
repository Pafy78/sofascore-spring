package com.steamulo.gestiontpe.network.manager

import android.content.res.Resources
import com.steamulo.gestiontpe.CallBackManager
import com.steamulo.gestiontpe.CallBackManagerWithError
import com.steamulo.gestiontpe.R
import retrofit2.Call
import retrofit2.Response

open class BaseNetworkManager {

    protected fun <T> checkOnResponseError(pCall: Call<T>, pResponse: Response<T>, pCallBack: CallBackManagerWithError<T>): T? {
        if(pResponse.code() != codeSuccess){
            pCallBack.onError(pResponse.code().toString())
            return null
        }

        val responseData = pResponse.body()

        if(responseData == null){
            pCallBack.onError(Resources.getSystem().getString(R.string.error_response_null))
            return null
        }
        return responseData
    }

    protected fun <T> checkOnResponseError(pCall: Call<T>, pResponse: Response<T>, pCallBack: CallBackManager): T? {
        if(pResponse.code() != codeSuccess){
            pCallBack.onResponse(pResponse.code().toString())
            return null
        }

        val responseData = pResponse.body()

        if(responseData == null){
            pCallBack.onResponse(Resources.getSystem().getString(R.string.error_response_null))
            return null
        }
        return responseData
    }

    companion object {
        private const val codeSuccess = 200
    }
}