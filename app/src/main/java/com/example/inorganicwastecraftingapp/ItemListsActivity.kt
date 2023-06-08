package com.example.inorganicwastecraftingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.inorganicwastecraftingapp.databinding.ActivityItemListsBinding

class ItemListsActivity : AppCompatActivity() {
    private lateinit var _binding: ActivityItemListsBinding
    private val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityItemListsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val item = intent.getStringExtra("item")
        binding.tvItemListsResult.text = item
    }
}