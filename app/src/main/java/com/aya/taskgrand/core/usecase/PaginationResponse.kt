package com.aya.taskgrand.core.usecase

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class PaginationResponse(
    @SerializedName("page")
    var page: Int? = null,
    @SerializedName("page_size")
    var pageSize: Int? = null,
    @SerializedName("total_count")
    var totalCount: Int? = null
) : Parcelable