package com.sunil0354.demo.ui.cart
import androidx.lifecycle.ViewModel
import com.sunil0354.demo.repository.repo.Repository
import javax.inject.Inject

class CartViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    private fun getDataFroRoom() = repository.getDataInRoom()

    fun getDataFromRoom() = getDataFroRoom()

    fun removeData(id:String){
        repository.deleteById(id)
    }
}