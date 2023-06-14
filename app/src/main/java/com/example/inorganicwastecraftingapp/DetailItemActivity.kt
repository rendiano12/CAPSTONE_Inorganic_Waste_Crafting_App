package com.example.inorganicwastecraftingapp

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.inorganicwastecraftingapp.database.Favorite
import com.example.inorganicwastecraftingapp.database.crud.FavoriteAddDeleteViewModel
import com.example.inorganicwastecraftingapp.databinding.ActivityDetailItemBinding

class DetailItemActivity : AppCompatActivity() {
    private lateinit var _binding: ActivityDetailItemBinding
    private val binding get() = _binding

    private lateinit var favoriteAddDeleteViewModel: FavoriteAddDeleteViewModel
    private var fvrt: Favorite? = null
    private lateinit var itemListsViewModel: ItemListsViewModel

    private var judul: String = ""
    private var link: String = ""
    private var bahan: ArrayList<String> = ArrayList()
    private var langkah: ArrayList<String> = ArrayList()

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailItemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        itemListsViewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(ItemListsViewModel::class.java)

        judul = intent.getStringExtra("judul")!!
        link = intent.getStringExtra("link") ?: "Mohon maaf video belum tersedia"

        renderDetailItem(judul, link)
        favoriteAddDeleteViewModel = obtainViewModel(this@DetailItemActivity)
        var checkFavorite = false
        val favoriteViewModel = obtainViewModelFavorite(this)
        fvrt = Favorite(judul, link)
        favoriteViewModel.getFavoriteItemByLink(judul, link).observe(this, { check ->
            if (check != null) {
                checkFavorite = true
                binding.btnDetailAddFavorite.setImageResource(R.drawable.ic_baseline_bookmark_filled_green01_90)
            } else {
                checkFavorite = false
                binding.btnDetailAddFavorite.setImageResource(R.drawable.ic_baseline_bookmark_outline_green01_90)
            }
        })

        binding.btnDetailAddFavorite.setOnClickListener {
            if (checkFavorite) {
                checkFavorite = false
                favoriteAddDeleteViewModel.delete(fvrt as Favorite)
                Toast.makeText(this, "Sukses Menghapus Favorit!", Toast.LENGTH_SHORT).show()
            } else {
                checkFavorite = true
                favoriteAddDeleteViewModel.insert(fvrt as Favorite)
                Toast.makeText(this, "Sukses Menambah Favorit!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun renderDetailItem(judul: String, link: String) {
        itemListsViewModel.getListItem()
        itemListsViewModel.listItemResponse.observe(this) { data ->
            var i = 0
            while (i < data.size) {
                for (getData in data[i].link) {
                    if (getData.judul == judul || getData.link == link) {
                        bahan = getData.bahan as ArrayList<String>
                        langkah = getData.langkah as ArrayList<String>
                        // Print Gambar
                        binding.ivDetailItemGambar.setImageResource(R.drawable.detail_item_image)
                        // Print Judul & Link
                        binding.tvDetailItemJudul.text = judul
                        binding.btnDetailItemLink.text = link
                        binding.btnDetailItemLink.setOnClickListener {
                            if (link == "Mohon maaf video belum tersedia") {
                                Toast.makeText(
                                    this,
                                    "Mohon maaf video belum tersedia",
                                    Toast.LENGTH_SHORT
                                ).show()
                            } else {
                                val intent = Intent(Intent.ACTION_VIEW)
                                intent.data = Uri.parse(link)
                                startActivity(intent)
                            }
                        }
                        // Print Bahan
                        var j = 0
                        var teksBahan = "Bahan yang dibutuhkan:\n"
                        while (j < bahan.size) {
                            teksBahan += "${j + 1}. ${bahan[j]}\n"
                            binding.tvDetailItemBahan.text = teksBahan
                            j++
                        }
                        // Print Langkah
                        j = 0
                        var teksLangkah = "Langkah-langkah:\n"
                        while (j < langkah.size) {
                            teksLangkah += "${j + 1}. ${langkah[j]}\n"
                            binding.tvDetailItemLangkah.text = teksLangkah
                            j++
                        }
                    }
                }
                i++
            }
        }
        itemListsViewModel.isLoading.observe(this) {
            showLoading(it)
        }
    }

    private fun obtainViewModelFavorite(activity: AppCompatActivity): FavoriteViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory).get(FavoriteViewModel::class.java)
    }

    private fun obtainViewModel(activity: AppCompatActivity): FavoriteAddDeleteViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory).get(FavoriteAddDeleteViewModel::class.java)
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBarDetailItem.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}