package com.sunil0354.demo.di.modules

import android.content.Context
import android.content.res.Resources
import androidx.room.Room
import com.sunil0354.demo.app.MyApplication
import com.sunil0354.demo.repository.api.ApiServices
import com.sunil0354.demo.repository.api.network.LiveDataCallAdapterFactoryForRetrofit
import com.sunil0354.demo.repository.db.AppDatabase
import com.sunil0354.demo.repository.db.MainDao
import com.sunil0354.demo.utils.Consts
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module(includes = [ ActivityModule::class, ViewModelModule::class])
class AppModule {
    /**
     * Provides ApiServices client for Retrofit
     */
    @Singleton
    @Provides
    fun provideNewsService(): ApiServices {
        return Retrofit.Builder()
            .baseUrl(Consts.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(LiveDataCallAdapterFactoryForRetrofit())
            .build()
            .create(ApiServices::class.java)
    }

    /**
     * Provides app AppDatabase
     */
    @Singleton
    @Provides
    fun provideDb(context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, Consts.DBNAME).build()
    }

    /**
     * Application application level context.
     */
    @Singleton
    @Provides
    fun provideContext(application: MyApplication): Context {
        return application.applicationContext
    }


    /**
     * Application resource provider, so that we can get the Drawable, Color, String etc at runtime
     */
    @Provides
    @Singleton
    fun providesResources(application: MyApplication): Resources = application.resources

    /**
     * Provides NewsArticlesDao an object to access NewsArticles table from Database
     */
    @Singleton
    @Provides
    fun provideMainDao(db: AppDatabase): MainDao {
        return db.mainDao()
    }
}
