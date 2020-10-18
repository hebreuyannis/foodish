package com.hebreuyannis.foodishapp.app.favorite

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hebreuyannis.foodishapp.R
import kotlinx.android.synthetic.main.item_favorite.view.*

class FavoriteRecyclerAdapter(private val itemClickListener: (String) -> Unit) :
    ListAdapter<String, FavoriteRecyclerAdapter.ViewHolder>(
        DiffCallback
    ) {

    companion object {
        @JvmStatic
        val DiffCallback = object : DiffUtil.ItemCallback<String>() {
            override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
                return oldItem == newItem
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val root = inflater.inflate(R.layout.item_favorite, parent, false)
        return ViewHolder(
            root,
            root.imageView
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), itemClickListener)
    }

    data class ViewHolder(val root: View, val image: ImageView) : RecyclerView.ViewHolder(root) {

        fun bind(url: String, clickListener: (String) -> Unit) {
            Glide.with(root.context)
                .load(url)
                .into(image)

            itemView.setOnClickListener { clickListener(url) }
        }
    }
}

