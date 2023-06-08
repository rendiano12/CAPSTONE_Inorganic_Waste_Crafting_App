package com.example.inorganicwastecraftingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.inorganicwastecraftingapp.databinding.ActivityTutorialBinding

class TutorialActivity : AppCompatActivity() {
    private lateinit var _binding: ActivityTutorialBinding
    private val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityTutorialBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = getString(R.string.tutorial)
    }
}