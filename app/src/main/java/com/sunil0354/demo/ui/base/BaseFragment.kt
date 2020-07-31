package com.sunil0354.demo.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.sunil0354.demo.di.base.Injectable
import dagger.android.DispatchingAndroidInjector
import javax.inject.Inject

abstract class BaseFragment<D : ViewDataBinding> :Fragment(), Injectable {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    @LayoutRes
    protected abstract fun getLayoutRes(): Int

    var dataBinding: D? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil.inflate(inflater, getLayoutRes(), container, false)
        return dataBinding?.root
    }
}