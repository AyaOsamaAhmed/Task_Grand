package com.aya.taskgrand.core.usecase

import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class BaseListResponse<T>(
    @SerializedName("pagination")
    var pagination: PaginationResponse? = null,
    @SerializedName("data")
    var data: @RawValue List<T>? = null
) : BaseCommonResponse()