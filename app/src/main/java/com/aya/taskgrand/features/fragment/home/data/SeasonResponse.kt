package com.aya.taskgrand.features.fragment.home.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
data class SeasonResponse(
    @field:SerializedName("id")
    val id: Int? = null,
    @field:SerializedName("startDate")
    val startDate: String? = null,
    @field:SerializedName("endDate")
    val endDate: String? = null,
    @field:SerializedName("currentMatchday")
    val currentMatchday: String? = null
) : Parcelable
