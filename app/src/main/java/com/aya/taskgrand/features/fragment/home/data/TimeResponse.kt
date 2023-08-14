package com.aya.taskgrand.features.fragment.home.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
data class TimeResponse(
    @field:SerializedName("homeTeam")
    val homeTeam: Int? = null,
    @field:SerializedName("awayTeam")
    val awayTeam: Int? = null
) : Parcelable
