package com.baudoin.sofascore.controller

import com.baudoin.sofascore.entity.Match
import com.baudoin.sofascore.network.HttpUtils
import com.baudoin.sofascore.network.entity.event.EventsResponse
import com.baudoin.sofascore.network.entity.lineup.MatchLineupResponse
import com.baudoin.sofascore.network.manager.base.CallBackManagerWithError
import com.baudoin.sofascore.network.manager.FootballNetworkManager
import com.baudoin.sofascore.network.manager.base.CallBackManager
import org.springframework.http.ResponseEntity
import org.springframework.web.context.request.async.DeferredResult
import org.springframework.web.bind.annotation.*


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

    @RequestMapping("/event/{eventID}", method = [RequestMethod.GET])
    fun event(@PathVariable("eventID") pEventID : String): DeferredResult<ResponseEntity<*>> {
        val output = DeferredResult<ResponseEntity<*>>()
        val match = Match(pEventID)
        match.getMatch(object: CallBackManager {
            override fun onResponse(pError: String?) {
                output.setResult(ResponseEntity.ok(match.displayTeamValues()))
            }

        })
        return output
    }
}