package com.demoss.core.base.mvvm.pagination

import com.demoss.core.base.mvvm.BaseAction

sealed class PaginatorAction: BaseAction

object PAGINATOR_RESTART: PaginatorAction()
object PAGINATOR_REFRESH: PaginatorAction()
object PAGINATOR_LOAD_NEXT_PAGE: PaginatorAction()