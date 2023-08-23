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
import com.hercan.harrypotterinfoapp.presentation.room.Favorite
import com.hercan.harrypotterinfoapp.presentation.room.FavoriteDatabase
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CharactersAdapter(private val db: FavoriteDatabase?) :
    ListAdapter<CharacterUIModel, CharactersAdapter.ViewHolder>(DiffCallback) {

    private var itemClickListener: ((CharacterUIModel) -> Unit)? = null

    inner class ViewHolder(
        private val binding: ItemCharacterBinding,
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: CharacterUIModel) = with(binding) {

            setIsRecyclable(false)
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

            val dao = db?.favoriteDao()
            val coroutineScope = CoroutineScope(Dispatchers.Default)
            val type=root.context.getString(R.string.character)

            coroutineScope.launch {
                val favoriteCharactersId = dao?.findId(item.id ?: "", type)
                if (favoriteCharactersId == item.id) {
                    animationView.progress = 1.0F
                } else {
                    animationView.progress = 0.0F
                }
            }

            animationView.setOnClickListener {
                if (animationView.progress == 0.0F) {
                    animationView.playAnimation()
                    coroutineScope.launch {
                        dao?.insert(Favorite(item.id ?: "", type))
                    }
                } else {
                    animationView.progress = 0.0F
                    coroutineScope.launch {
                        dao?.delete(Favorite(item.id ?: "", type))
                    }
                }
            }
        }
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