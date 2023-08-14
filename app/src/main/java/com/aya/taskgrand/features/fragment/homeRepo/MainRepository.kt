package com.aya.taskgrand.features.fragment.homeRepo

import com.aya.taskgrand.core.network.MainApis
import com.aya.taskgrand.core.response.ErrorResponse
import com.aya.taskgrand.core.response.NetworkResponse
import com.aya.taskgrand.features.fragment.home.data.MatchesResponse
import javax.inject.Inject

class MainRepository @Inject constructor(private val mainApiService: MainApis) {

    suspend fun listMatches(): NetworkResponse<MatchesResponse, ErrorResponse> =
        mainApiService.listMatches()



}