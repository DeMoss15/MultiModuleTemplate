package com.demoss.core.base.mvvm.pagination

import androidx.lifecycle.viewModelScope
import com.demoss.core.base.mvvm.BaseAction
import com.demoss.core.base.mvvm.BaseViewModel
import com.demoss.core.pagination.*

abstract class BasePaginatorViewModel<ItemType, A : BaseAction, CE : BasePaginatorCommandExecutor<ItemType>> :
    BaseViewModel<A, CE>() {

    // the lambda executes requests
    protected abstract val requestFabric: suspend (Int) -> List<ItemType>

    @Suppress("UNCHECKED_CAST")
    protected val paginator: Paginator<ItemType> by lazy {
        Paginator(
            viewModelScope,
            requestFabric,
            { newValue ->
                _commands.value = when (newValue) {
                    is EMPTY -> { { dispatchEmptyState() } }
                    is EMPTY_PROGRESS -> { { dispatchEmptyDataState() } }
                    is EMPTY_ERROR -> { { dispatchEmptyErrorState(newValue.exception.localizedMessage) } }
                    is EMPTY_DATA -> { { dispatchEmptyDataState() } }
                    is ERROR -> { { dispatchErrorState(newValue.exception.localizedMessage) } }
                    is PAGE_PROGRESS -> { { dispatchPageProgressState() } }
                    is REFRESH -> { { dispatchRefreshState() } }
                    is RELEASED -> { { dispatchReleasedState() } }
                    is DATA -> { { dispatchDataState(newValue.data) } }
                    is LAST_PAGE -> { { dispatchLastPageState(newValue.data) } }
                }
            }
        ).apply { refresh() }
    }

    // paginator actions are handled automatically, unhandled actions are handled by [this.executeNonPaginatorAction()]
    final override fun executeAction(action: A): Unit = if (action is PaginatorAction) when (action) {
        PAGINATOR_RESTART -> paginator.restart()
        PAGINATOR_REFRESH -> paginator.refresh()
        PAGINATOR_LOAD_NEXT_PAGE -> paginator.loadNewPage()
    } else executeNonPaginatorAction(action)

    // makes extensions with custom actions available, but not required
    protected open fun executeNonPaginatorAction(action: A) {}

    override fun onCleared() {
        paginator.release()
        super.onCleared()
    }
}