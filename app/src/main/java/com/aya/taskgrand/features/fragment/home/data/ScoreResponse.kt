package com.aya.taskgrand.features.fragment.home.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
data class ScoreResponse(
    @field:SerializedName("winner")
    val winner: String? = null,
    @field:SerializedName("duration")
    val duration: String? = null,
    @field:SerializedName("fullTime")
    val fullTime: TimeResponse? = null,
    @field:SerializedName("halfTime")
    val halfTime: TimeResponse? = null,
    @field:SerializedName("extraTime")
    val extraTime: TimeResponse? = null,
    @field:SerializedName("penalties")
    val penalties: TimeResponse? = null
) : Parcelable
