package com.aya.taskgrand.features.fragment.home.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
data class TeamResponse(
    @field:SerializedName("id")
    val id: Int? = null,
    @field:SerializedName("name")
    val name: String? = null
) : Parcelable
