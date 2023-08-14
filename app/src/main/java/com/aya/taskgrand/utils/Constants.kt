package com.aya.taskgrand.utils

import com.aya.taskgrand.BuildConfig

object Constants {

    const val BASE_URL_API: String = BuildConfig.SERVER_HOST

    enum class MatchType {
        FINISHED, CANCELED , LIVE_NOW , NOT_START , SCHEDULED
    }

}
