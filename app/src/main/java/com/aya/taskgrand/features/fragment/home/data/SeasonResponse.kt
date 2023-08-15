package com.aya.taskgrand.features.fragment.home.data

import android.os.Parcelable
import androidx.room.Entity
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Entity(tableName = "season")
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
