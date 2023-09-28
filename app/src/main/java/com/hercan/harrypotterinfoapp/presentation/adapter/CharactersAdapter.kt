package com.hercan.harrypotterinfoapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hercan.harrypotterinfoapp.R
import com.hercan.harrypotterinfoapp.databinding.ItemCharacterBinding
import com.hercan.harrypotterinfoapp.presentation.model.CharacterUIModel
import com.hercan.harrypotterinfoapp.presentation.room.model.FavoriteCharacterModel
import com.squareup.picasso.Picasso

class CharactersAdapter :
    ListAdapter<CharacterUIModel, CharactersAdapter.ViewHolder>(DiffCallback) {

    private var itemClickListener: ((CharacterUIModel) -> Unit)? = null
    private var updateFavorite: ((FavoriteCharacterModel, Boolean) -> Unit)? = null
    var favoriteCharacters: List<FavoriteCharacterModel> = listOf()

    inner class ViewHolder(
        private val binding: ItemCharacterBinding,
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: CharacterUIModel) = with(binding) {

            tvName.text = item.characterName
            tvType.text = root.context.getString(item.typeId)
            icHouse.background = AppCompatResources.getDrawable(root.context, item.houseDrawableId)
            icLiveOrDead.background = AppCompatResources.getDrawable(root.context, item.aliveId)

            Picasso.get()
                .load(item.image)
                .error(R.drawable.iv_default_character_photo)
                .placeholder(R.drawable.iv_default_character_photo)
                .into(ivPhoto)

            root.setOnClickListener {
                itemClickListener?.invoke(item)
            }

            if (favoriteCharacters.isNotEmpty()) {
                val favorite = favoriteCharacters.find {
                    it.id == item.id
                }
                if (favorite != null) {
                    if (favorite.isFavorite) {
                        animationView.progress = 1.0F
                    }
                }
            }

            animationView.setOnClickListener {
                val favorite = favoriteCharacters.find {
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

    fun updateFavorite(updateFavorite: (FavoriteCharacterModel, Boolean) -> Unit) {
        this.updateFavorite = updateFavorite
    }

    fun setItemClickListener(listener: (CharacterUIModel) -> Unit) {
        this.itemClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCharacterBinding.inflate(LayoutInflater.from(parent.context))
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