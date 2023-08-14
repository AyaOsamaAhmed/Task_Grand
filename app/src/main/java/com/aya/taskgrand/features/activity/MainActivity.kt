package com.aya.taskgrand.features.activity

import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.aya.taskgrand.R
import com.aya.taskgrand.base.BaseActivity
import com.aya.taskgrand.core.extension.observe
import com.aya.taskgrand.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>()  {

    private lateinit var nav : NavHostFragment
    private lateinit var navController : NavController
    override val mViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel.apply {
            observe(viewState) {
                handleViewState(it)
            }
        }
        nav =   supportFragmentManager.findFragmentById(R.id.main_layout) as NavHostFragment
        navController = nav.navController
        changeColorStatusBar()

    }


    private fun handleViewState(action: MainAction) {
        when (action) {

        }
    }

    private fun changeColorStatusBar(){

        if (Build.VERSION.SDK_INT >= 21) {
            val window = this.window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.statusBarColor = this.resources.getColor(R.color.black)
        }
    }


}