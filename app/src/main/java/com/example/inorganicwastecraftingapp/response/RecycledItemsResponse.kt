package com.example.inorganicwastecraftingapp.response

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

data class RecycledItemsResponse(

	@field:SerializedName("RecycledItemsResponse")
	val recycledItemsResponse: List<RecycledItemsResponseItem>
)

data class LinkItem(

	@field:SerializedName("langkah")
	val langkah: List<String>,

	@field:SerializedName("bahan")
	val bahan: List<String>,

	@field:SerializedName("link")
	val link: String,

	@field:SerializedName("judul")
	val judul: String,

	@field:SerializedName("langkah_langkah")
	val langkahLangkah: List<String>
)

data class RecycledItemsResponseItem(

	@field:SerializedName("kelas")
	val kelas: String,

	@field:SerializedName("jenis")
	val jenis: String,

	@field:SerializedName("link")
	val link: List<LinkItem>,

	@field:SerializedName("id")
	val id: String
)
