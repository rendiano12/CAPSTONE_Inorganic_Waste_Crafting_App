package com.example.inorganicwastecraftingapp.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.inorganicwastecraftingapp.response.LinkItem

@Dao
interface FavoriteDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(favorite: Favorite)
    @Delete
    fun delete(favorite: Favorite)
    @Query("SELECT * from favorite")
    fun getAllFavorites(): LiveData<List<Favorite>>
    @Query("SELECT * FROM favorite WHERE judul = :judul AND link = :link")
    fun getFavoriteItemByLink(judul: String, link: String): LiveData<Favorite>
}