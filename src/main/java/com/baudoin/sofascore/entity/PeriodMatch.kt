package com.baudoin.sofascore.entity

import com.baudoin.sofascore.network.HttpUtils
import com.baudoin.sofascore.network.entity.event.EventsResponse
import com.baudoin.sofascore.network.entity.event.TournamentEventsResponse
import com.baudoin.sofascore.network.manager.FootballNetworkManager
import com.baudoin.sofascore.network.manager.base.CallBackManager
import com.baudoin.sofascore.network.manager.base.CallBackManagerWithError
import java.time.Instant
import java.time.LocalDate
import java.time.temporal.ChronoUnit

class PeriodMatch(val tournamentName: String, val periodInDay: Int) {

    var dayMatchs: List<DayMatch> = emptyList()
    var matchs: List<Match> = emptyList()

    fun getDayMatchs(pCallBack: CallBackManager){
        val now = LocalDate.now()
        var day = now.plusDays(- this.periodInDay.toLong())
        var loading = false
        while (day <= now) {
            if(!loading){
                loading = true
                val dayMatch = DayMatch(day.toString(), this.tournamentName)
                dayMatch.getMatchs(object: CallBackManager{
                    override fun onResponse(pError: String?) {
                        this@PeriodMatch.dayMatchs += dayMatch
                        day = day.plusDays(1)
                        println(day)
                        loading = false
                    }
                })
            }
        }
        pCallBack.onResponse(null)
    }

    fun getSeasonMatchs(pCallBack: CallBackManager){
        HttpUtils.setNetworkManagerInterfaces()
        val dayBeforeStart = 0
        FootballNetworkManager.getEvents(LocalDate.now().plus((dayBeforeStart).toLong(), ChronoUnit.DAYS).toString(), object: CallBackManagerWithError<EventsResponse>{
            override fun onSuccess(response: EventsResponse) {
                val tournamentId = response.sportItem
                        .tournaments
                        .find { tournamentResponse ->
                            tournamentResponse.tournament.name == this@PeriodMatch.tournamentName }
                        ?.tournament
                        ?.uniqueId
                val seasonId =response.sportItem
                        .tournaments
                        .find { tournamentResponse ->
                            tournamentResponse.tournament.name == this@PeriodMatch.tournamentName }
                        ?.season
                        ?.id
                if(tournamentId == null || seasonId == null){
                    pCallBack.onResponse("Tournament not found")
                    return
                }
                val plusDay = 1
                val endTime = Instant.now().plus(plusDay.toLong(), ChronoUnit.DAYS).epochSecond
                val startTime = Instant.now().plus(- this@PeriodMatch.periodInDay.toLong(), ChronoUnit.DAYS).epochSecond

                FootballNetworkManager.getTournamentEvents(
                        tournamentId,
                        seasonId,
                        startTime,
                        endTime,
                        object: CallBackManagerWithError<TournamentEventsResponse>{
                            override fun onSuccess(response: TournamentEventsResponse) {
                                var count = 0
                                var loading = false
                                var countFinal = 0
                                response.weekMatches.tournaments.forEachIndexed { index, tournamentResponse ->
                                    countFinal += tournamentResponse.events.count()
                                }
                                response.weekMatches.tournaments.forEachIndexed { index, tournamentResponse ->
                                    tournamentResponse.events.forEachIndexed { index, eventResponse ->
                                        if(eventResponse.id != null){
                                            val match = Match(eventResponse.id.toString())
                                            while(loading){}
                                            loading = true
                                            match.getMatch(object: CallBackManager{
                                                override fun onResponse(pError: String?) {
                                                    if(pError != null){
                                                        println(pError)
                                                    }
                                                    this@PeriodMatch.matchs += match
                                                    count ++
                                                    loading = false
                                                    if(count == countFinal){
                                                        pCallBack.onResponse(null)
                                                    }
                                                }
                                            })
                                        }
                                        else{
                                            count ++
                                        }
                                    }
                                }
                            }
                            override fun onError(pError: String) {
                                pCallBack.onResponse(pError)
                            }
                        })
            }
            override fun onError(pError: String) {
                pCallBack.onResponse(pError)
            }
        })
    }

    fun displayPeriodMatchs(): String?{
        if(this.dayMatchs.isEmpty()){
            return null
        }
        var string = ""
        this.dayMatchs.forEachIndexed { index, dayMatch ->
            if(dayMatch.displayMatchs() != null){
                string += dayMatch.displayMatchs() + "<br/>"
            }
        }
        return string
    }

    fun displayMatchs(): String?{
        if(this.matchs.isEmpty()){
            return null
        }
        var string = ""
        this.matchs.forEachIndexed { index, match ->
            if(match.displayTeamValues() != null){
                string += "${match.displayTeamValues()}<br/><br/>"
            }
        }
        return string
    }

    fun alreadyHasMatch(id: Int?): Boolean {
        return this.dayMatchs.find {  dayMatch ->
            dayMatch.matchs.find { match ->
                match.pId.toInt() == id } != null
        } != null
    }
}