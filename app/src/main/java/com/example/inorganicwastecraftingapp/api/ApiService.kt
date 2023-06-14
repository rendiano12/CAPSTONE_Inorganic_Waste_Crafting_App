package com.example.inorganicwastecraftingapp.api

import com.example.inorganicwastecraftingapp.response.RecycledItemsResponse
import com.example.inorganicwastecraftingapp.response.RecycledItemsResponseItem
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("jenis")
    fun getListItem(): Call<List<RecycledItemsResponseItem>>
}