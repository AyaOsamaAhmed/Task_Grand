package com.aya.taskgrand.features.fragment.home.ui

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.aya.taskgrand.base.Action
import com.aya.taskgrand.base.AndroidBaseViewModel
import com.aya.taskgrand.core.network.Resource
import com.aya.taskgrand.features.fragment.home.data.MatchesItems
import com.aya.taskgrand.features.fragment.home.domain.ListMatchesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


sealed class HomeAction  : Action {
    data class ListMatches (val listMatches : MatchesItems) : HomeAction()
    data class ShowLoading(val show: Boolean) : HomeAction()
    data class ShowFailureMsg(val message: String?) : HomeAction()
}

@HiltViewModel
class HomeViewModel @Inject constructor(
    app: Application,
    private val listMatchesUseCase: ListMatchesUseCase
) : AndroidBaseViewModel<HomeAction>(app) {

    init {
        listMatches()
    }

     private fun listMatches() {
        listMatchesUseCase.invoke(viewModelScope) { res ->
            when (res) {
                is Resource.Failure ->
                    produce(HomeAction.ShowFailureMsg(res.message))
                is Resource.Progress ->
                    produce(HomeAction.ShowLoading(res.loading))
                is Resource.Success -> {
                    handleListMatchesSuccess(res.data)
                }
            }
        }
    }

    private fun handleListMatchesSuccess(response: MatchesItems) {
        produce(HomeAction.ListMatches(response))
    }


}