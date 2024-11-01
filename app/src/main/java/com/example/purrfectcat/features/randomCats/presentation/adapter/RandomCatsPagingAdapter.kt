package com.example.purrfectcat.features.randomCats.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.purrfectcat.R
import com.example.purrfectcat.core.domain.model.Cat
import com.example.purrfectcat.databinding.CatItemBinding
import com.example.purrfectcat.utils.glideImageLoader.GlideImageLoader
import com.example.purrfectcat.utils.viewBinding.itemViewBinding.viewBinding

class RandomCatsPagingAdapter(
    val glideImageLoader: GlideImageLoader,
    val onItemClicked: (cat: Cat) -> Unit
) : PagingDataAdapter<Cat, RandomCatsPagingAdapter.ViewHolder>(
    CatDiffUtil()
) {
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        item?.let { holder.bind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cat_item, parent, false)
        return ViewHolder(view)
    }

    inner class ViewHolder(item: View) : RecyclerView.ViewHolder(item) {

        private val binding by viewBinding(CatItemBinding::bind)

        fun bind(cat: Cat) {

            glideImageLoader.loadImage(cat.url, binding.imageView)

            itemView.setOnClickListener {
                onItemClicked(cat)
            }
        }
    }
}

private class CatDiffUtil : DiffUtil.ItemCallback<Cat>() {
    override fun areItemsTheSame(oldItem: Cat, newItem: Cat): Boolean {
        return oldItem.id == newItem.id &&
                oldItem.url == newItem.url
    }

    override fun areContentsTheSame(oldItem: Cat, newItem: Cat): Boolean {
        return oldItem == newItem
    }
}