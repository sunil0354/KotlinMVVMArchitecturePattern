package com.sunil0354.demo.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sunil0354.demo.di.base.ViewModelFactory
import com.sunil0354.demo.di.base.ViewModelKey
import com.sunil0354.demo.ui.cart.CartViewModel
import com.sunil0354.demo.ui.cart.PaymentViewModel
import com.sunil0354.demo.ui.main.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(mainViewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CartViewModel::class)
    abstract fun bindCartViewModel(cartViewModel : CartViewModel): ViewModel    @Binds
    @IntoMap
    @ViewModelKey(PaymentViewModel::class)
    abstract fun bindPaymentViewModel(paymentViewModel: PaymentViewModel): ViewModel

    /**
     * Binds ViewModels factory to provide ViewModels.
     */
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}
