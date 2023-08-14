package com.aya.taskgrand.features.fragment.home.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
data class MatchesResponse(
    @field:SerializedName("count")
    val count: Int? = null,
    @field:SerializedName("filters")
    val filters: FiltersResponse? = null,
    @field:SerializedName("competition")
    val competition: CompetitionResponse? = null,
    @field:SerializedName("matches")
    val matches: List<MatchResponse>? = null
) : Parcelable
