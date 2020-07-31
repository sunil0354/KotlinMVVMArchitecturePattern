package com.sunil0354.demo.di.modules

import com.sunil0354.demo.ui.cart.CartFragment
import com.sunil0354.demo.ui.cart.PaymentFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class FragmentModule {
    /**
     * Injecting Fragments
     */
    @ContributesAndroidInjector
    internal abstract fun contributeCartFragment(): CartFragment

    /**
     * Injecting Fragments
     */
    @ContributesAndroidInjector
    internal abstract fun contributePaymentFragment(): PaymentFragment
}