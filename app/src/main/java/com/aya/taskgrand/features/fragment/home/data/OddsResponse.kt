package com.aya.taskgrand.features.fragment.home.data

import android.os.Parcelable
import androidx.room.Entity
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Entity(tableName = "odds")
@Parcelize
data class OddsResponse(
    @field:SerializedName("msg")
    val msg: String? = null
) : Parcelable
