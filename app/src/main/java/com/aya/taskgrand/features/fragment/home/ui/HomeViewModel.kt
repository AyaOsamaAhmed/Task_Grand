package com.aya.taskgrand.features.fragment.home.ui

import android.app.Application
import androidx.lifecycle.viewModelScope
import androidx.room.withTransaction
import com.aya.taskgrand.base.Action
import com.aya.taskgrand.base.AndroidBaseViewModel
import com.aya.taskgrand.core.network.Resource
import com.aya.taskgrand.database.MatchesListDatabase
import com.aya.taskgrand.features.fragment.home.data.MatchDB
import com.aya.taskgrand.features.fragment.home.data.MatchesItems
import com.aya.taskgrand.features.fragment.home.domain.ListMatchesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject


sealed class HomeAction  : Action {
    data class ListMatchDB (val listMatches : List<MatchDB>) : HomeAction()

    data class ListMatches (val listMatches : MatchesItems) : HomeAction()
    data class ShowLoading(val show: Boolean) : HomeAction()
    data class ShowFailureMsg(val message: String?) : HomeAction()
}

@HiltViewModel
class HomeViewModel @Inject constructor(
    app: Application,
    private val listMatchesUseCase: ListMatchesUseCase,
    private val db : MatchesListDatabase
) : AndroidBaseViewModel<HomeAction>(app) {

//https://www.geeksforgeeks.org/how-to-implement-offline-caching-using-networkboundresource-in-android/
    private val matchesDao = db.matchDao()


    fun handleGetData(checkInternet : Boolean){
        if(checkInternet){
            listMatches()
        }else{
            getDatabase()
        }

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
       setDatabase(response)
        produce(HomeAction.ListMatches(response))
    }

    fun setDatabase(data: MatchesItems){

        repeat(data.matches!!.size) {
            val matchDb = MatchDB(
                it,
                data.competition?.name,
                data.competition?.lastUpdated,
                data.matches?.get(it)?.season?.startDate,
                data.matches?.get(it)?.score?.fullTime?.homeTeam,
                data.matches?.get(it)?.score?.fullTime?.awayTeam,
                data.matches?.get(it)?.homeTeam?.name,
                data.matches?.get(it)?.awayTeam?.name,
                data.matches?.get(it)?.utcDate,
                data.matches?.get(it)?.status,
                it
            )

            GlobalScope.launch {
                db.withTransaction {
                    matchesDao.insertMatches(matchDb)
                }
            }
        }
    }

    fun getDatabase ()  {
       val match = mutableListOf<MatchDB>()
        GlobalScope.launch {

          match.addAll( matchesDao.getMatches())
            produce(HomeAction.ListMatchDB(match))
        }

    }


}