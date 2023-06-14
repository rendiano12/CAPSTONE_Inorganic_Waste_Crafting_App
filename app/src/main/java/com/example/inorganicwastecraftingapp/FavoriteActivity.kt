package com.example.inorganicwastecraftingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.inorganicwastecraftingapp.databinding.ActivityFavoriteBinding

@Suppress("UNCHECKED_CAST")
class FavoriteActivity : AppCompatActivity() {
    private lateinit var _binding: ActivityFavoriteBinding
    private val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val layoutManager = LinearLayoutManager(this)
        binding.rvRecycledItemFavorite.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        binding.rvRecycledItemFavorite.addItemDecoration(itemDecoration)

        val favoriteViewModel = obtainViewModel(this@FavoriteActivity)
        favoriteViewModel.getAllFavorites().observe(this) { favoriteList ->
            if (favoriteList.size == 0) {
                binding.tvFavoriteListInfo.text = getString(R.string.favorite_info)
            } else {
                binding.tvFavoriteListInfo.text = null
                val adapter = FavoriteAdapter(favoriteList)
                binding.rvRecycledItemFavorite.adapter = adapter
            }
        }
    }

    private fun obtainViewModel(activity: AppCompatActivity): FavoriteViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory).get(FavoriteViewModel::class.java)
    }
}