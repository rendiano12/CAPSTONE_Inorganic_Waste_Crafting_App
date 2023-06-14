package com.example.inorganicwastecraftingapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.inorganicwastecraftingapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var _binding: ActivityMainBinding
    private val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
        binding.civHomeImage.setImageResource(R.drawable.app_icon)
        binding.topBarTutorial.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.tutorial_menu -> {
                    val intent = Intent(this, TutorialActivity::class.java)
                    startActivity(intent)
                    true
                }
                else -> false
            }
        }

        binding.btnMainScan.setOnClickListener {
            val intent = Intent(this, CameraScanActivity::class.java)
            startActivity(intent)
        }
        binding.btnMainFavorite.setOnClickListener{
            val intent = Intent(this, FavoriteActivity::class.java)
            startActivity(intent)
        }
    }
}