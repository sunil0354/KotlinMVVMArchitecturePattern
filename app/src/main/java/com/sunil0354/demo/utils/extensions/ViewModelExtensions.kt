package com.sunil0354.demo.utils.extensions

import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.sunil0354.demo.ui.base.BaseActivity
import com.sunil0354.demo.ui.base.BaseFragment

/**
 * Synthetic sugaring to get instance of [ViewModel] for [AppCompatActivity].
 */
inline fun <reified T : ViewModel,D : ViewDataBinding> BaseActivity<D>.getViewModel(): T {
    return ViewModelProviders.of(this, viewModelFactory).get(T::class.java)
}

/**
 * Synthetic sugaring to get instance of [ViewModel] for [Fragment].
 */
inline fun <reified T : ViewModel,D : ViewDataBinding> BaseFragment<D>.getViewModel(): T {
    return ViewModelProviders.of(this).get(T::class.java)
}
