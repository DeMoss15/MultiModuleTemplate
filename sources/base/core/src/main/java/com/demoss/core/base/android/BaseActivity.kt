package com.demoss.core.base.android

import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.demoss.core.base.mvvm.BaseAction
import com.demoss.core.base.mvvm.BaseCommandExecutor
import com.demoss.core.base.mvvm.BaseView
import com.demoss.core.base.mvvm.BaseViewModel

abstract class BaseActivity<A: BaseAction, CE: BaseCommandExecutor, VM : BaseViewModel<A, CE>>
    : AppCompatActivity(), BaseView<A, CE, VM> {

    abstract val layoutResourceId: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutResourceId)
        subscribeToViewModel(this)
    }

    protected fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    protected fun hideKeyboard() {
        (getSystemService(AppCompatActivity.INPUT_METHOD_SERVICE) as InputMethodManager)
            .hideSoftInputFromWindow((currentFocus ?: View(this)).windowToken, 0)
    }
}