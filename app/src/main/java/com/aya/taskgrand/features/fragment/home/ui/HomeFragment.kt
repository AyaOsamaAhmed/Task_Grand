package com.aya.taskgrand.features.fragment.home.ui

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.aya.taskgrand.R
import com.aya.taskgrand.base.BaseFragment
import com.aya.taskgrand.core.extension.observe
import com.aya.taskgrand.databinding.HomeFragmentBinding
import com.aya.taskgrand.features.fragment.home.data.MatchDB
import com.aya.taskgrand.features.fragment.home.data.MatchesItems
import com.aya.taskgrand.features.fragment.home.ui.adapter.ListMatchesAdapter
import com.aya.taskgrand.features.fragment.home.ui.adapter.ListMatchesDBAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<HomeFragmentBinding, HomeViewModel>(){

    private lateinit var nav : NavHostFragment
    private lateinit var navController : NavController
    override val mViewModel: HomeViewModel by viewModels()

    private val adapter = ListMatchesAdapter()

    private val adapterDB = ListMatchesDBAdapter()


    override fun onFragmentReady() {

        mViewModel.apply {
            observe(viewState) {
                handleViewState(it)
            }
        }

        mViewModel.handleGetData(isOnline(requireContext()))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        nav =  activity?.supportFragmentManager?.findFragmentById(R.id.main_layout) as NavHostFragment
        navController = nav.navController

        return super.onCreateView(inflater, container, savedInstanceState)
    }


    private fun handleViewState(action: HomeAction) {
        when (action) {
            is HomeAction.ShowLoading -> {
                showProgress(action.show)
            }

            is HomeAction.ShowFailureMsg -> showToast(action.message)

            is HomeAction.ListMatches ->{
                handleListMatches(action.listMatches)
            }

            is HomeAction.ListMatchDB ->{
                showToast("Get Database ** ${action.listMatches.size}")
                setHandleDatabase(action.listMatches)
            }


         }
    }

    private fun setHandleDatabase(listMatches: List<MatchDB>) {
        binding.rvMatches.adapter = adapterDB
        adapterDB.submitList(listMatches)
    }

    private fun handleListMatches(data : MatchesItems) {
        binding.rvMatches.adapter = adapter
        adapter.submitList(data.matches)
        adapter.setCompetition(data.competition!!)
    }

    fun isOnline(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        if (capabilities != null) {
            if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
                return true
            } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
                return true
            } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                Log.i("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
                return true
            }
        }
        return false
    }

}