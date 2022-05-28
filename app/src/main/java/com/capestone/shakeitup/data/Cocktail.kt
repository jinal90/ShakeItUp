package com.capestone.shakeitup.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "cocktail")
data class Cocktail(
    @SerializedName("strDrink")
    @ColumnInfo(name="cocktail_name") val strDrink: String,
    @SerializedName("strDrinkThumb")
    @ColumnInfo(name="thumbnail_url") val strDrinkThumb: String,
    @SerializedName("idDrink")
    @PrimaryKey val idDrink: String,
    var isFavorite: Boolean
)
