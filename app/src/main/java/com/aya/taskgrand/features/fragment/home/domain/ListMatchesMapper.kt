package com.aya.taskgrand.features.fragment.home.domain

import com.aya.taskgrand.features.fragment.home.data.MatchItem
import com.aya.taskgrand.features.fragment.home.data.MatchesItems
import com.aya.taskgrand.features.fragment.home.data.MatchesResponse


object ListMatchesMapper {

    fun mapData(res: MatchesResponse): MatchesItems {

        val result = MatchesItems(viewType = 0)

        result.competition = res.competition
        result.filters = res.filters
        result.count = res.count

        result.matches = List<MatchItem>(res.matches!!.size){
            val data = res.matches[it]
            MatchItem(data.id,data.season,data.utcDate,data.status,data.matchday,data.stage,data.group,data.lastUpdated,
            data.odds,data.score,data.homeTeam,data.awayTeam,data.referees,data.id!!)
        }


        return result
    }

}