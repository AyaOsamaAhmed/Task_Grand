package com.aya.taskgrand.features.fragment.home.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
data class CompetitionResponse(
    @field:SerializedName("id")
    val id: Int? = null,
    @field:SerializedName("area")
    val area: AreaResponse? = null,
    @field:SerializedName("name")
    val name: String? = null,
    @field:SerializedName("code")
    val code: String? = null,
    @field:SerializedName("plan")
    val plan: String? = null,
    @field:SerializedName("lastUpdated")
    val lastUpdated: String? = null
) : Parcelable
