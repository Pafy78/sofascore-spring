package com.baudoin.sofascore.network.manager.base

interface CallBackManagerWithError<T> {
    fun onSuccess(response : T)
    fun onError(pError: String)
}
