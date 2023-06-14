package com.example.inorganicwastecraftingapp

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.inorganicwastecraftingapp.api.ApiConfig
import com.example.inorganicwastecraftingapp.response.RecycledItemsResponseItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ItemListsViewModel: ViewModel() {
    private val _listItemResponse = MutableLiveData<List<RecycledItemsResponseItem>>()
    val listItemResponse: LiveData<List<RecycledItemsResponseItem>> = _listItemResponse
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun getListItem() {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getListItem()
        client.enqueue(object : Callback<List<RecycledItemsResponseItem>> {
            override fun onResponse(
                call: Call<List<RecycledItemsResponseItem>>,
                response: Response<List<RecycledItemsResponseItem>>
            ) {
                if (response.isSuccessful) {
                    _isLoading.value = false
                    _listItemResponse.value = response.body()
                } else {
                    Log.e(ContentValues.TAG, "onFailure: ${response.message()}}")
                }
            }
            override fun onFailure(call: Call<List<RecycledItemsResponseItem>>, t: Throwable) {
                _isLoading.value = false
                Log.e(ContentValues.TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }
}