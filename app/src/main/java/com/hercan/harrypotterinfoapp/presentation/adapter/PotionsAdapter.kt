package com.hercan.harrypottercharactersapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hercan.harrypotterinfoapp.R
import com.hercan.harrypotterinfoapp.databinding.RvPotterdbItemBinding
import com.hercan.harrypotterinfoapp.presentation.model.PotionUIModel
import com.squareup.picasso.Picasso

class PotionsAdapter :
    ListAdapter<PotionUIModel, PotionsAdapter.ViewHolder>(DiffCallback) {

    class ViewHolder(
        private val binding: RvPotterdbItemBinding,
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: PotionUIModel) = with(binding) {

            setIsRecyclable(false)
            tvName.text = item.name

            Picasso.get()
                .load(item.image)
                .resize(100, 100)
                .centerCrop()
                .error(R.drawable.iv_default_potion_photo)
                .placeholder(R.drawable.iv_default_potion_photo)
                .into(ivPhoto)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RvPotterdbItemBinding.inflate(LayoutInflater.from(parent.context))
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        item?.let {
            holder.bind(item)
        }
    }

    object DiffCallback : DiffUtil.ItemCallback<PotionUIModel>() {
        override fun areItemsTheSame(
            oldItem: PotionUIModel,
            newItem: PotionUIModel
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: PotionUIModel,
            newItem: PotionUIModel
        ): Boolean {
            return oldItem == newItem
        }
    }
}