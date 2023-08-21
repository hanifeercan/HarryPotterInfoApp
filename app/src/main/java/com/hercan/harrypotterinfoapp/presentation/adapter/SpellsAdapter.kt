package com.hercan.harrypotterinfoapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hercan.harrypotterinfoapp.R
import com.hercan.harrypotterinfoapp.databinding.ItemPotterdbBinding
import com.hercan.harrypotterinfoapp.presentation.model.SpellUIModel
import com.squareup.picasso.Picasso

class SpellsAdapter :
    ListAdapter<SpellUIModel, SpellsAdapter.ViewHolder>(DiffCallback) {

    class ViewHolder(
        private val binding: ItemPotterdbBinding,
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: SpellUIModel) = with(binding) {

            setIsRecyclable(false)
            tvName.text = item.name

            Picasso.get()
                .load(item.image)
                .resize(100, 100)
                .centerCrop()
                .error(R.drawable.iv_default_spell_photo)
                .placeholder(R.drawable.iv_default_spell_photo)
                .into(ivPhoto)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemPotterdbBinding.inflate(LayoutInflater.from(parent.context))
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        item?.let {
            holder.bind(item)
        }
    }

    object DiffCallback : DiffUtil.ItemCallback<SpellUIModel>() {
        override fun areItemsTheSame(
            oldItem: SpellUIModel,
            newItem: SpellUIModel
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: SpellUIModel,
            newItem: SpellUIModel
        ): Boolean {
            return oldItem == newItem
        }
    }
}