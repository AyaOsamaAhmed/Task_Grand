package com.aya.taskgrand.features.fragment.home.ui

import android.os.Bundle
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
import com.aya.taskgrand.features.fragment.home.data.MatchesResponse
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<HomeFragmentBinding, HomeViewModel>(){

    private lateinit var nav : NavHostFragment
    private lateinit var navController : NavController
    override val mViewModel: HomeViewModel by viewModels()


    override fun onFragmentReady() {

        mViewModel.apply {
            observe(viewState) {
                handleViewState(it)
            }
        }

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


         }
    }

    private fun handleListMatches(data : MatchesResponse) {

    }

}