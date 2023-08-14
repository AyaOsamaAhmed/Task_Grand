package com.aya.taskgrand.features.fragment.home.data

import com.aya.taskgrand.base.BasePaginationParcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
data class MatchesItems(
    val id: Int ,

    @field:SerializedName("count")
    val count: Int? = null,
    @field:SerializedName("filters")
    val filters: FiltersResponse? = null,
    @field:SerializedName("competition")
    val competition: CompetitionResponse? = null,
    @field:SerializedName("matches")
    val matches: List<MatchResponse>? = null,

            override var viewType: Int

): BasePaginationParcelable {
    override fun unique(): Any = id
}
