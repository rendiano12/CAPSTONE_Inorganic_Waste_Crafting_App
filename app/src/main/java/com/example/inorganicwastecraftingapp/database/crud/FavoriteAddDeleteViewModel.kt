package com.example.inorganicwastecraftingapp.database.crud

import android.app.Application
import androidx.lifecycle.ViewModel
import com.example.inorganicwastecraftingapp.database.Favorite
import com.example.inorganicwastecraftingapp.database.FavoriteRepository
import com.example.inorganicwastecraftingapp.response.LinkItem

class FavoriteAddDeleteViewModel(application: Application) : ViewModel() {
    private val mNoteRepository: FavoriteRepository = FavoriteRepository(application)
    fun insert(favorite: Favorite) {
        mNoteRepository.insert(favorite)
    }
    fun delete(favorite: Favorite) {
        mNoteRepository.delete(favorite)
    }
}