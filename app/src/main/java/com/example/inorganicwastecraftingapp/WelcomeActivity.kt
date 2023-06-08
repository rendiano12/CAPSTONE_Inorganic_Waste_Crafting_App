package com.example.inorganicwastecraftingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.inorganicwastecraftingapp.databinding.ActivityWelcomeBinding

class WelcomeActivity : AppCompatActivity() {
    private lateinit var _binding: ActivityWelcomeBinding
    private val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Glide.with(binding.civHomeImage)
            .load(R.drawable.app_icon)
            .into(binding.civHomeImage)
    }
}