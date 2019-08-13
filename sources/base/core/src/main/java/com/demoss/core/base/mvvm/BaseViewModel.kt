package com.demoss.core.base.mvvm

import androidx.annotation.CallSuper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineExceptionHandler

abstract class BaseViewModel<A : BaseAction, CE : BaseCommandExecutor> : ViewModel() {

    // commands passed to this LD will be executed by view
    protected open val _commands: MutableLiveData<CE.() -> Unit> = MutableLiveData()
    open val commands: LiveData<CE.() -> Unit> get() = _commands.also { onSubscribe() }
    // this method is commonly called on take view
    protected open fun onSubscribe() {}

    // base error handler for coroutines
    protected val handler: CoroutineExceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        throwable.printStackTrace()
        handleException(throwable)
    }

    // handling of non general exceptions, can be extended by child
    @CallSuper
    protected open fun handleException(e: Throwable) {
        _commands.value = { showError(e.localizedMessage) }
    }

    // user's action handling, should be implemented by child
    abstract fun executeAction(action: A)
}