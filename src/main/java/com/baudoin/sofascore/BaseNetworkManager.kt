package com.baudoin.sofascore

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
            pCallBack.onError("Error")
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
            pCallBack.onResponse("Error")
            return null
        }
        return responseData
    }

    companion object {
        private const val codeSuccess = 200
    }
}