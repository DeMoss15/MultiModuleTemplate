package com.demoss.core.base.mvvm.pagination

import com.demoss.core.base.mvvm.BaseCommandExecutor

interface BasePaginatorCommandExecutor<T>: BaseCommandExecutor {
    fun dispatchEmptyState()
    fun dispatchEmptyProgressState()
    fun dispatchEmptyErrorState(message: String)
    fun dispatchEmptyDataState()
    fun dispatchErrorState(message: String)
    fun dispatchPageProgressState()
    fun dispatchRefreshState()
    fun dispatchReleasedState()
    fun dispatchDataState(data: List<T>)
    fun dispatchLastPageState(data: List<T>)

}