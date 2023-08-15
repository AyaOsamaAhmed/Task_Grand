package com.aya.taskgrand.features.fragment.home.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.aya.taskgrand.base.BasePaginationParcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Entity(tableName = "match")
@Parcelize
data class MatchDB(
    @PrimaryKey
    val id: Int ,
    //competition
    val name: String? = null,

    val lastUpdated: String? = null,

    //season
    val startDate: String? = null,
    //score
    @field:SerializedName("homeTeam")
    val scoreHomeTeam: Int? = null,
    @field:SerializedName("awayTeam")
    val scoreAwayTeam: Int? = null,
    //name teams
    val HomeTeam: String? = null,
    val AwayTeam: String? = null,
    //
    @field:SerializedName("utcDate")
    val utcDate: String? = null,
    @field:SerializedName("status")
    val status: String? = null,


    override var viewType: Int

): BasePaginationParcelable {
    override fun unique(): Any = id
}