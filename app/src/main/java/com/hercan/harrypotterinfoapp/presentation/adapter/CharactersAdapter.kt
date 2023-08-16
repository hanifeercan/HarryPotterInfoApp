package com.hercan.harrypottercharactersapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hercan.harrypotterinfoapp.R
import com.hercan.harrypotterinfoapp.databinding.RvCharacterItemBinding
import com.hercan.harrypotterinfoapp.presentation.model.CharacterUIModel
import com.squareup.picasso.Picasso

class CharactersAdapter :
    ListAdapter<CharacterUIModel, CharactersAdapter.ViewHolder>(DiffCallback) {

    class ViewHolder(
        private val binding: RvCharacterItemBinding,
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: CharacterUIModel) = with(binding) {

            setIsRecyclable(false)
            tvName.text = item.characterName ?: "-"
            tvType.text = root.context.getString(item.typeId)
            icHouse.background = AppCompatResources.getDrawable(root.context, item.houseDrawableId)
            icLiveOrDead.background = AppCompatResources.getDrawable(root.context, item.aliveId)

            Picasso.get()
                .load(item.image)
                .resize(100, 100)
                .centerCrop()
                .error(R.drawable.iv_default_character_photo)
                .placeholder(R.drawable.iv_default_character_photo)
                .into(ivPhoto)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RvCharacterItemBinding.inflate(LayoutInflater.from(parent.context))
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        item?.let {
            holder.bind(item)
        }
    }

    object DiffCallback : DiffUtil.ItemCallback<CharacterUIModel>() {
        override fun areItemsTheSame(
            oldItem: CharacterUIModel,
            newItem: CharacterUIModel
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: CharacterUIModel,
            newItem: CharacterUIModel
        ): Boolean {
            return oldItem == newItem
        }
    }
}