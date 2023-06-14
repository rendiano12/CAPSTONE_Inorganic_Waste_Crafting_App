package com.example.inorganicwastecraftingapp

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.inorganicwastecraftingapp.database.Favorite

class FavoriteAdapter(private val listFavorites: List<Favorite>) : RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): FavoriteViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.recycled_item_row, viewGroup, false)
        return FavoriteViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: FavoriteViewHolder, position: Int) {
        viewHolder.tvRecycledItemName.text = listFavorites[position].judul

        viewHolder.itemView.setOnClickListener {
            val intentDetail = Intent(viewHolder.itemView.context, DetailItemActivity::class.java)
            intentDetail.putExtra("judul", listFavorites[viewHolder.adapterPosition].judul)
            intentDetail.putExtra("link", listFavorites[viewHolder.adapterPosition].link)
            viewHolder.itemView.context.startActivity(intentDetail)
        }
    }

    override fun getItemCount(): Int {
        return listFavorites.size
    }

    class FavoriteViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvRecycledItemName: TextView = view.findViewById(R.id.tv_recycled_item_name)
    }
}
