package com.lloydtechassignment.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.lloydtechassignment.data.models.AnimalUIModel
import com.lloydtechassignment.domain.model.AnimalDomainModel
import com.lloydtechassignment.databinding.ItemAnimalBinding
import com.lloydtechassignment.presentation.adapters.viewholder.AnimalViewHolder

/**
 * Adapter class [RecyclerView.Adapter] for [RecyclerView] which binds [AnimalDomainModel] along with [AnimalViewHolder]
 * @param onItemClicked which will receive callback when item is clicked.
 */
class AnimalListAdapter(
    private val onItemClicked: (AnimalUIModel) -> Unit
) : ListAdapter<AnimalUIModel, AnimalViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = AnimalViewHolder(
        ItemAnimalBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: AnimalViewHolder, position: Int) =
        holder.bind(getItem(position),onItemClicked)

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<AnimalUIModel>() {
            override fun areItemsTheSame(oldItem: AnimalUIModel, newItem: AnimalUIModel): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: AnimalUIModel, newItem: AnimalUIModel): Boolean =
                oldItem == newItem
        }
    }
}
