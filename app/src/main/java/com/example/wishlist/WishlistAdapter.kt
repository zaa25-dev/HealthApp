package com.example.wishlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class WishlistAdapter(
    private val wishlist: MutableList<WishlistItem>
) : RecyclerView.Adapter<WishlistAdapter.WishlistViewHolder>() {

    class WishlistViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName: TextView = itemView.findViewById(R.id.tvItemName)
        val tvPrice: TextView = itemView.findViewById(R.id.tvItemPrice)
        val tvStore: TextView = itemView.findViewById(R.id.tvItemStore)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WishlistViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_wishlist, parent, false)
        return WishlistViewHolder(view)
    }

    override fun onBindViewHolder(holder: WishlistViewHolder, position: Int) {
        val item = wishlist[position]
        holder.tvName.text = item.name
        holder.tvPrice.text = item.price
        holder.tvStore.text = item.storeOrUrl
    }

    override fun getItemCount(): Int {
        return wishlist.size
    }

    fun addItem(item: WishlistItem) {
        wishlist.add(item)
        notifyItemInserted(wishlist.size - 1)
    }
}