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

        binding.apply {
            animalTitle.text = animalsRespItem.name
            postAuthor.text = animalsRespItem.habitat

            Glide.with(imageView.context)
                .load(animalsRespItem.imageLink) // image url
                .into(imageView)

            root.setOnClickListener {
                onItemClicked(animalsRespItem)
            }
        }

    }
}
