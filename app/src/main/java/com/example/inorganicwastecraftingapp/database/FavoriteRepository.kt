package com.example.inorganicwastecraftingapp.database

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.inorganicwastecraftingapp.response.LinkItem
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class FavoriteRepository(application: Application) {
    private val mFavoritesDao: FavoriteDao
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()
    init {
        val db = FavoriteRoomDatabase.getDatabase(application)
        mFavoritesDao = db.favoriteDao()
    }
    fun getAllFavorites(): LiveData<List<Favorite>> = mFavoritesDao.getAllFavorites()
    fun getFavoriteItemByLink(judul: String, link: String): LiveData<Favorite> = mFavoritesDao.getFavoriteItemByLink(judul, link)
    fun insert(favorite: Favorite) {
        executorService.execute { mFavoritesDao.insert(favorite) }
    }
    fun delete(favorite: Favorite) {
        executorService.execute { mFavoritesDao.delete(favorite) }
    }
}