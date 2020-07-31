package com.sunil0354.demo.di.modules

import com.sunil0354.demo.ui.cart.CartActivity
import com.sunil0354.demo.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector
/**
 * All your Activities participating in DI would be listed here.
 */
@Module(includes = [FragmentModule::class]) // Including Fragment Module Available For Activities
abstract class ActivityModule {
    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun contributeCartActivity(): CartActivity
}
