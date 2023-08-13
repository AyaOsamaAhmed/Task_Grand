package com.aya.taskgrand.features.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.aya.taskgrand.R
import com.aya.taskgrand.base.BaseFragment
import com.aya.taskgrand.databinding.SplashFragmentBinding
import com.aya.taskgrand.features.activity.MainViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SplashFragment : BaseFragment<SplashFragmentBinding, MainViewModel>()  {

    private lateinit var nav : NavHostFragment
    private lateinit var navController : NavController

    override val mViewModel: MainViewModel by viewModels()

    override fun onFragmentReady() {

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        nav =  activity?.supportFragmentManager?.findFragmentById(R.id.main_layout) as NavHostFragment
        navController = nav.navController
    }



}