package com.sunil0354.demo.repository.repo

import android.content.Context
import androidx.lifecycle.LiveData
import com.sunil0354.demo.app.AppExecutors
import com.sunil0354.demo.repository.api.ApiServices
import com.sunil0354.demo.repository.api.network.NetworkResource
import com.sunil0354.demo.repository.api.network.Resource
import com.sunil0354.demo.repository.db.MainDao
import com.sunil0354.demo.repository.model.TestList
import com.sunil0354.demo.repository.model.TestListItem
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Repository abstracts the logic of fetching the data and persisting it for
 * offline. They are the data source as the single source of truth.
 *
 */

@Singleton
class Repository @Inject constructor(
    private val newsDao: MainDao,
    private val apiServices: ApiServices, private val context: Context,
    private val appExecutors: AppExecutors = AppExecutors()
) {

    fun getDataServerOnly():
            LiveData<Resource<TestList>> {
        return object : NetworkResource<TestList>() {
            override fun createCall(): LiveData<Resource<TestList>> {
                return apiServices.getTestList()
            }

        }.asLiveData()
    }

    fun insertDataInRoom(testListItem: TestListItem) {
        appExecutors.diskIO().execute {
            newsDao.insert(testListItem)
        }
    }

    fun getDataInRoom(): LiveData<List<TestListItem>> {
        return newsDao.getNewsData()
    }

    fun deleteAllDataInRoom() {
        appExecutors.diskIO().execute {
            newsDao.deleteAllData()
        }
    }

    fun deleteById(id: String) {
        appExecutors.diskIO().execute {
            newsDao.deleteById(id)
        }
    }
}