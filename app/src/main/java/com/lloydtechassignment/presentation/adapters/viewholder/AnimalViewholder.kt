package com.lloydtechassignment.presentation.adapters.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lloydtechassignment.data.models.AnimalUIModel
import com.lloydtechassignment.databinding.ItemAnimalBinding

/**
 * [RecyclerView.ViewHolder] implementation to inflate View for RecyclerView.
 * See AnimalListAdapter
 */
class AnimalViewHolder(private val binding: ItemAnimalBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(animalsRespItem: AnimalUIModel, onItemClicked: (AnimalUIModel) -> Unit) {
        binding.animalTitle.text = animalsRespItem.name
        binding.postAuthor.text = animalsRespItem.habitat

        Glide.with(binding.imageView.context)
            .load(animalsRespItem.imageLink) // image url
            .into(binding.imageView)

        binding.root.setOnClickListener {
            onItemClicked(animalsRespItem)
        }
    }
}
