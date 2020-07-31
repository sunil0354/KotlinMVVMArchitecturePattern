package com.sunil0354.demo.di.components

import com.sunil0354.demo.app.MyApplication
import com.sunil0354.demo.di.modules.AppModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

/**
 * AndroidInjectionModule::class to support Dagger
 * AppModule::class is loading all modules for app
 */
@Singleton
@Component(modules = [AndroidInjectionModule::class, AppModule::class])
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: MyApplication): Builder

        fun build(): AppComponent
    }

    fun inject(application: MyApplication)

}
