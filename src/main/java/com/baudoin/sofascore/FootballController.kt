package com.baudoin.sofascore

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/api")
@RestController
class FootballController {

    @RequestMapping(method = [RequestMethod.GET])
    fun index(): String {
        HttpUtils.setNetworkManagerInterfaces()
        FootballNetworkManager.getEvents("2019-04-22", object: CallBackManagerWithError<FootballResponse>{
            override fun onSuccess(response: FootballResponse) {

            }

            override fun onError(pError: String) {

            }

        })
        return "ok"
    }
}