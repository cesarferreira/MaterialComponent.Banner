package com.cesar.bannerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import cesarferreira.faker.loadFromUrl
import kotlinx.android.synthetic.main.item_list_item.view.*

class ListItemsAdapter(private val items: ArrayList<ItemViewModel>) :
    RecyclerView.Adapter<ListItemsAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_list_item, parent, false)
        return ItemViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: ItemViewModel) {
            itemView.thumbnail.loadFromUrl(url = item.thumbnail)
            itemView.setOnClickListener { Toast.makeText(itemView.context, item.toString(), Toast.LENGTH_SHORT).show() }
        }
    }
}
