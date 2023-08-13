package com.aya.taskgrand.features.activity

import android.app.Application
import com.aya.taskgrand.base.Action
import com.aya.taskgrand.base.AndroidBaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

sealed class MainAction : Action {
}

@HiltViewModel
class MainViewModel @Inject constructor(
    app: Application
) : AndroidBaseViewModel<MainAction>(app) {

}
