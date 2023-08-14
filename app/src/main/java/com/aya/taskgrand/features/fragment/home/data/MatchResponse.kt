package com.aya.taskgrand.features.fragment.home.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
data class MatchResponse(
    @field:SerializedName("id")
    val id: Int? = null,
    @field:SerializedName("season")
    val season: SeasonResponse? = null,
    @field:SerializedName("utcDate")
    val utcDate: String? = null,
    @field:SerializedName("status")
    val status: String? = null,
    @field:SerializedName("matchday")
    val matchday: String? = null,
    @field:SerializedName("stage")
    val stage: String? = null,
    @field:SerializedName("group")
    val group: String? = null,
    @field:SerializedName("lastUpdated")
    val lastUpdated: String? = null,
    @field:SerializedName("odds")
    val odds: OddsResponse? = null,
    @field:SerializedName("score")
    val score: ScoreResponse? = null,
    @field:SerializedName("homeTeam")
    val homeTeam: TeamResponse? = null,
    @field:SerializedName("awayTeam")
    val awayTeam: TeamResponse? = null,
    @field:SerializedName("referees")
    val referees: List<RefereesResponse>? = null
) : Parcelable
