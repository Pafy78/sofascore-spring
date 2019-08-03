package com.baudoin.sofascore.controller

import com.baudoin.sofascore.entity.DayMatch
import com.baudoin.sofascore.entity.Match
import com.baudoin.sofascore.entity.PeriodMatch
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

    @RequestMapping("/event/date/{date}/tournament/{name}", method = [RequestMethod.GET])
    fun tournamentEventsDate(@PathVariable("date") date : String, @PathVariable("name") name : String): DeferredResult<ResponseEntity<*>> {
        val output = DeferredResult<ResponseEntity<*>>()
        val day = DayMatch(date, name)
        day.getMatchs(object: CallBackManager {
            override fun onResponse(pError: String?) {
                if(pError != null){
                    output.setResult(ResponseEntity.ok(pError))
                }
                else{
                    output.setResult(ResponseEntity.ok(day.displayMatchs()))
                }

            }

        })
        return output
    }

    @RequestMapping("/event/tournament/{name}/period/{nbDays}", method = [RequestMethod.GET])
    fun tournamentEvents(@PathVariable("name") name : String, @PathVariable("nbDays") nbDays : Int): DeferredResult<ResponseEntity<*>> {
        val output = DeferredResult<ResponseEntity<*>>()
        val day = PeriodMatch(name, nbDays)
        day.getDayMatchs(object: CallBackManager {
            override fun onResponse(pError: String?) {
                if(pError != null){
                    output.setResult(ResponseEntity.ok(pError))
                }
                else{
                    output.setResult(ResponseEntity.ok(day.displayPeriodMatchs()))
                }

            }

        })
        return output
    }

    @RequestMapping("/event/season/tournament/{name}/period/{nbDays}", method = [RequestMethod.GET])
    fun tournamentSeasonEvents(@PathVariable("name") name : String, @PathVariable("nbDays") nbDays : Int): DeferredResult<ResponseEntity<*>> {
        val output = DeferredResult<ResponseEntity<*>>()
        val day = PeriodMatch(name, nbDays)
        day.getSeasonMatchs(object: CallBackManager {
            override fun onResponse(pError: String?) {
                if(pError != null){
                    output.setResult(ResponseEntity.ok(pError))
                }
                else{
                    output.setResult(ResponseEntity.ok(day.displayMatchs()))
                }

            }

        })
        return output
    }
}