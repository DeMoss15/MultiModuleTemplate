package com.demoss.core.base.android

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.demoss.core.base.mvvm.BaseAction
import com.demoss.core.base.mvvm.BaseCommandExecutor
import com.demoss.core.base.mvvm.BaseView
import com.demoss.core.base.mvvm.BaseViewModel

abstract class BaseFragment<A : BaseAction, CE : BaseCommandExecutor, VM : BaseViewModel<A, CE>>
    : Fragment(), BaseView<A, CE, VM> {

    abstract val layoutResourceId: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // TODO: think how to resolve this unchecked cast and force programmer to inherit his Fragments from it
        subscribeToViewModel(this, this as CE)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(layoutResourceId, container, false)
    }

    protected fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    protected fun hideKeyboard() {
        (activity?.getSystemService(AppCompatActivity.INPUT_METHOD_SERVICE) as InputMethodManager)
            .hideSoftInputFromWindow((activity?.currentFocus ?: View(context)).windowToken, 0)
    }
}