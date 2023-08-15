package com.aya.taskgrand.features.fragment.home.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class FiltersResponse(
    @field:SerializedName("count")
    val count: Int? = null
) : Parcelable
