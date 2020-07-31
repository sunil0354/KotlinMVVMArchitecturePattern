package com.sunil0354.demo.repository.api

import androidx.lifecycle.LiveData
import com.sunil0354.demo.repository.api.network.Resource
import com.sunil0354.demo.repository.model.TestList
import com.sunil0354.demo.utils.Consts
import retrofit2.http.GET

/**
 * Api services to communicate with server
 *
 */
interface ApiServices {
    @GET(Consts.GETTESTLIST)
    fun getTestList(): LiveData<Resource<TestList>>
}
