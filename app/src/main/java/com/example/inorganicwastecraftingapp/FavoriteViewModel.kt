package com.example.inorganicwastecraftingapp

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.inorganicwastecraftingapp.database.Favorite
import com.example.inorganicwastecraftingapp.database.FavoriteRepository

class FavoriteViewModel(application: Application) : ViewModel() {
    private val mFavoriteRepository: FavoriteRepository = FavoriteRepository(application)

    fun getAllFavorites(): LiveData<List<Favorite>> = mFavoriteRepository.getAllFavorites()
    fun getFavoriteItemByLink(judul: String, link: String): LiveData<Favorite> = mFavoriteRepository.getFavoriteItemByLink(judul, link)
}