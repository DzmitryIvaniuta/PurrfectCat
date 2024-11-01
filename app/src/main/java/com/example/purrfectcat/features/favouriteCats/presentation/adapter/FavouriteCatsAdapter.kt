package com.example.purrfectcat.features.favouriteCats.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.purrfectcat.R
import com.example.purrfectcat.core.domain.model.Cat
import com.example.purrfectcat.databinding.CatItemBinding
import com.example.purrfectcat.utils.glideImageLoader.GlideImageLoader
import com.example.purrfectcat.utils.viewBinding.itemViewBinding.viewBinding

class FavouriteCatsAdapter(
    val glideImageLoader: GlideImageLoader,
    val onItemClicked: (cat: Cat) -> Unit
) : RecyclerView.Adapter<FavouriteCatsAdapter.ViewHolder>() {

    inner class ViewHolder(item: View) : RecyclerView.ViewHolder(item) {

        private val binding by viewBinding(CatItemBinding::bind)


        fun bind(cat: Cat) {

            glideImageLoader.loadImage(cat.url, binding.imageView)

            itemView.setOnClickListener {
                onItemClicked(cat)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cat_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = differ.currentList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }

    private val differCallback = object : DiffUtil.ItemCallback<Cat>() {
        override fun areItemsTheSame(oldItem: Cat, newItem: Cat): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Cat, newItem: Cat): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)
}