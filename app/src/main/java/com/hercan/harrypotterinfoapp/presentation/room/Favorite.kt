package com.hercan.harrypotterinfoapp.presentation.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite")
data class Favorite(@PrimaryKey val id: String, val type: String)
