package com.baudoin.sofascore

interface CallBackManagerWithError<T> {
    fun onSuccess(response : T)
    fun onError(pError: String)
}
