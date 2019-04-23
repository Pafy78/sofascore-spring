package com.steamulo.gestiontpe

interface CallBackManagerWithError<T> {
    fun onSuccess(response : T)
    fun onError(pError: String)
}
