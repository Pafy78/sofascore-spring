package com.baudoin.sofascore.controller

import com.baudoin.sofascore.network.HttpUtils
import com.baudoin.sofascore.network.entity.event.EventsResponse
import com.baudoin.sofascore.network.manager.base.CallBackManagerWithError
import com.baudoin.sofascore.network.manager.FootballNetworkManager
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/football")
@RestController
class FootballController {

    @RequestMapping(method = [RequestMethod.GET])
    fun index(): String {
        HttpUtils.setNetworkManagerInterfaces()
        FootballNetworkManager.getEvents("2019-04-22", object: CallBackManagerWithError<EventsResponse> {
            override fun onSuccess(response: EventsResponse) {
                println(response.sportItem.toString())
            }

            override fun onError(pError: String) {

            }

        })
        return "Route index"
    }
}