package com.aya.taskgrand.features.fragment.home.domain

import com.aya.taskgrand.core.response.ErrorResponse
import com.aya.taskgrand.core.response.NetworkResponse
import com.aya.taskgrand.core.usecase.BaseUseCase
import com.aya.taskgrand.features.fragment.home.data.MatchesItems
import com.aya.taskgrand.features.fragment.home.data.MatchesResponse
import com.aya.taskgrand.features.fragment.homeRepo.MainRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ListMatchesUseCase  @Inject constructor(private val mainRepository: MainRepository) :
    BaseUseCase<MatchesResponse, MatchesItems, String>() {
    override fun mapper(req: MatchesResponse): MatchesItems =
        ListMatchesMapper.mapData(req)


    override fun executeRemote(params: String?): Flow<NetworkResponse<MatchesResponse, ErrorResponse>> =
        flow {
            emit(mainRepository.listMatches())
        }

}