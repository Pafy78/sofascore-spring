package com.baudoin.sofascore.entity

import com.baudoin.sofascore.network.manager.base.CallBackManager
import java.time.LocalDate

class PeriodMatch(val tournamentName: String, val periodInMonth: Int) {

    var dayMatchs: List<DayMatch> = emptyList()

    fun getDayMatchs(pCallBack: CallBackManager){
        val now = LocalDate.now()
        var day = now.plusMonths(- this.periodInMonth.toLong())
        var loading = false
        while (day < now) {
            if(!loading){
                loading = true
                val dayMatch = DayMatch(day.toString(), this.tournamentName)
                dayMatch.getMatchs(object: CallBackManager{
                    override fun onResponse(pError: String?) {
                        this@PeriodMatch.dayMatchs += dayMatch
                        day = day.plusDays(1)
                        loading = false
                    }
                })
            }
        }
        pCallBack.onResponse(null)
    }

    fun displayPeriodMatchs(): String{
        var string = ""
        this.dayMatchs.forEachIndexed { index, dayMatch ->
            string += dayMatch.displayMatchs() + "<br/>"
        }
        return string
    }
}