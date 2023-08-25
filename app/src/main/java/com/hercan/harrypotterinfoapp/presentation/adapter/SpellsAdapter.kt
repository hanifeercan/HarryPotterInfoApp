package com.hercan.harrypotterinfoapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hercan.harrypotterinfoapp.R
import com.hercan.harrypotterinfoapp.databinding.ItemPotterdbBinding
import com.hercan.harrypotterinfoapp.presentation.model.SpellUIModel
import com.hercan.harrypotterinfoapp.presentation.room.model.FavoriteSpellModel
import com.squareup.picasso.Picasso

class SpellsAdapter :
    ListAdapter<SpellUIModel, SpellsAdapter.ViewHolder>(DiffCallback) {

    private var itemClickListener: ((SpellUIModel) -> Unit)? = null
    private var updateFavorite: ((FavoriteSpellModel, Boolean) -> Unit)? = null
    var favoriteSpells: List<FavoriteSpellModel> = listOf()

    inner class ViewHolder(
        private val binding: ItemPotterdbBinding,
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: SpellUIModel) = with(binding) {

            tvName.text = item.name

            Picasso.get()
                .load(item.image)
                .error(R.drawable.iv_default_spell_photo)
                .placeholder(R.drawable.iv_default_spell_photo)
                .into(ivPhoto)

            root.setOnClickListener {
                itemClickListener?.invoke(item)
            }

            if (favoriteSpells.isNotEmpty()) {
                val favorite = favoriteSpells.find {
                    it.id == item.id
                }
                if (favorite != null) {
                    if (favorite.isFavorite) {
                        animationView.progress = 1.0F
                    }
                }
            }

            animationView.setOnClickListener {
                val favorite = favoriteSpells.find {
                    it.id == item.id
                }
                if (favorite != null) {
                    if (favorite.isFavorite) {
                        animationView.cancelAnimation()
                        animationView.progress = 0.0F
                        updateFavorite?.invoke(favorite, false)
                    } else {
                        updateFavorite?.invoke(favorite, true)
                        animationView.playAnimation()
                    }
                }
            }
        }
    }

    fun updateFavorite(updateFavorite: (FavoriteSpellModel, Boolean) -> Unit) {
        this.updateFavorite = updateFavorite
    }

    fun setItemClickListener(listener: (SpellUIModel) -> Unit) {
        this.itemClickListener = listener
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