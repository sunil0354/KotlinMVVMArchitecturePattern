package com.sunil0354.demo.ui.main

import androidx.lifecycle.ViewModel
import com.sunil0354.demo.repository.model.TestListItem
import com.sunil0354.demo.repository.repo.Repository
import javax.inject.Inject

class MainViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    private fun newsArticlesFromOnlyServer() = repository.getDataServerOnly()

    fun getNewsArticlesFromServer() = newsArticlesFromOnlyServer()

    fun addDataInRoom(testListItem: TestListItem){
        repository.insertDataInRoom(testListItem)
    }

    private fun getDataFroRoom() = repository.getDataInRoom()

    fun getDataFromRoom() = getDataFroRoom()
}