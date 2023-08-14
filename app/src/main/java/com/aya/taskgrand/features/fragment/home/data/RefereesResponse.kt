package com.aya.taskgrand.features.fragment.home.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
data class RefereesResponse(
    @field:SerializedName("id")
    val id: Int? = null,
    @field:SerializedName("name")
    val name: String? = null,
    @field:SerializedName("role")
    val role: String? = null,
    @field:SerializedName("nationality")
    val nationality: String? = null
) : Parcelable
