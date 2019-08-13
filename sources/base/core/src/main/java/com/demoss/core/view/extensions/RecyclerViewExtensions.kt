package com.demoss.core.view.extensions

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.demoss.core.base.android.BaseRecyclerViewAdapter
import com.demoss.core.view.SimpleSwipeItemCallback

fun RecyclerView.addItemTouchHelperWithCallback(callback: ItemTouchHelper.SimpleCallback) {
    with(ItemTouchHelper(callback)) { attachToRecyclerView(this@addItemTouchHelperWithCallback) }
}

fun <T> RecyclerView.setupSwipeToDelete(
    adapter: BaseRecyclerViewAdapter<T, *>,
    swipeDirection: SwipeDirection,
    onItemDeleteAction: (T) -> Unit
) {
    val swipeAction: (Int) -> Unit = { itemPosition: Int ->
        adapter.apply {
            onItemDeleteAction(differ.currentList[itemPosition])
            val updatedList = differ.currentList.toMutableList()
            updatedList.removeAt(itemPosition)
            differ.submitList(updatedList)
        }
    }

    val actions: Pair<((Int) -> Unit)?, ((Int) -> Unit)?> = when (swipeDirection) {
        SwipeDirection.LEFT -> swipeAction to null
        SwipeDirection.RIGHT -> null to swipeAction
        SwipeDirection.LEFT_AND_RIGHT -> swipeAction to swipeAction
    }

    this.addItemTouchHelperWithCallback(SimpleSwipeItemCallback(actions.first, actions.second))
}

enum class SwipeDirection {
    LEFT,
    RIGHT,
    LEFT_AND_RIGHT
}