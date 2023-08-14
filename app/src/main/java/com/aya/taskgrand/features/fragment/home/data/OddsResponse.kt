package com.aya.taskgrand.features.fragment.home.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
data class OddsResponse(
    @field:SerializedName("msg")
    val msg: String? = null
) : Parcelable
