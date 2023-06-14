package com.example.inorganicwastecraftingapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.inorganicwastecraftingapp.databinding.ActivityItemListsBinding

class ItemListsActivity : AppCompatActivity() {
    private lateinit var _binding: ActivityItemListsBinding
    private val binding get() = _binding

    private lateinit var itemListsViewModel: ItemListsViewModel

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityItemListsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val jenis = intent.getStringExtra("jenis")
        binding.tvItemListResult.text = jenis

        itemListsViewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(ItemListsViewModel::class.java)

        val layoutManager = LinearLayoutManager(this)
        binding.rvItemListsResult.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        binding.rvItemListsResult.addItemDecoration(itemDecoration)

        renderJenisItemImage(jenis!!)
        getListsItem(jenis)
    }

    private fun renderJenisItemImage(jenis: String) {
        when (jenis) {
            "Kaleng" -> binding.civItemListsResult.setImageResource(R.drawable.kaleng)
            "Cangkir" -> binding.civItemListsResult.setImageResource(R.drawable.cangkir)
            "Botol Kaca" -> binding.civItemListsResult.setImageResource(R.drawable.botol_kaca)
            "Kantong Plastik" -> binding.civItemListsResult.setImageResource(R.drawable.kantong_plastik)
            "Botol Plastik" -> binding.civItemListsResult.setImageResource(R.drawable.botol_plastik)
            "Tutup Botol Plastik" -> binding.civItemListsResult.setImageResource(R.drawable.tutup_botol_plastik)
            "Plastik Kemasan" -> binding.civItemListsResult.setImageResource(R.drawable.plastik_kemasan)
            "Sterofoam" -> binding.civItemListsResult.setImageResource(R.drawable.sterofoam)
            "Sedotan" -> binding.civItemListsResult.setImageResource(R.drawable.sedotan)
            else -> binding.civItemListsResult.setImageResource(R.drawable.sendok)
        }
    }

    private fun getListsItem(jenis: String) {
        itemListsViewModel.getListItem()
        itemListsViewModel.listItemResponse.observe(this) {data ->
            var i = 0
            while (i < data.size) {
                if (data[i].jenis == jenis) {
                    val adapter = RecycledItemAdapter(data[i].link)
                    binding.rvItemListsResult.adapter = adapter
                }
                i++
            }
        }
        itemListsViewModel.isLoading.observe(this) {
            showLoading(it)
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBarItemLists.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}