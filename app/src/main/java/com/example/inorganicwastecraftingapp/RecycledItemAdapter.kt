package com.example.inorganicwastecraftingapp

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.inorganicwastecraftingapp.response.LinkItem

class RecycledItemAdapter(private val listItem: List<LinkItem>) : RecyclerView.Adapter<RecycledItemAdapter.ViewHolder>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.recycled_item_row, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.tvRecycledItemName.text = listItem[position].judul

        viewHolder.itemView.setOnClickListener {
            val intentDetail = Intent(viewHolder.itemView.context, DetailItemActivity::class.java)
            intentDetail.putExtra("judul", listItem[viewHolder.adapterPosition].judul)
            intentDetail.putExtra("link", listItem[viewHolder.adapterPosition].link)
            viewHolder.itemView.context.startActivity(intentDetail)
        }
    }

    override fun getItemCount(): Int {
        return listItem.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvRecycledItemName: TextView = view.findViewById(R.id.tv_recycled_item_name)
    }
}