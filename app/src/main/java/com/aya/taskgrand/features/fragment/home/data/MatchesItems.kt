package com.aya.taskgrand.features.fragment.home.data

import com.aya.taskgrand.base.BasePaginationParcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
data class MatchesItems(
    val id: Int? = null,
    @field:SerializedName("count")
    var count: Int? = null,
    @field:SerializedName("filters")
    var filters: FiltersResponse? = null,
    @field:SerializedName("competition")
    var competition: CompetitionResponse? = null,
    @field:SerializedName("matches")
    var matches: List<MatchItem>? = null,

            override var viewType: Int

): BasePaginationParcelable {
    override fun unique(): Any = id!!
}
