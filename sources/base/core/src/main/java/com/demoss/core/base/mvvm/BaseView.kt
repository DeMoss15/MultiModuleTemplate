package com.demoss.core.base.mvvm

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer

interface BaseView<A: BaseAction, CE: BaseCommandExecutor, VM : BaseViewModel<A, CE>> {

    // for sending commands to view model
    val viewModel: VM
    val commandExecutor: CE

    fun subscribeToViewModel(lifecycleOwner: LifecycleOwner) {
        viewModel.commands.observe(lifecycleOwner, Observer { commandExecutor.it() })
    }
}