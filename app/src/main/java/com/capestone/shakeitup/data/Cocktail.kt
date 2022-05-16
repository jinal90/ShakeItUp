package com.capestone.shakeitup.data

import com.google.gson.annotations.SerializedName

data class Cocktail(
    @SerializedName("strDrink")
    val strDrink: String,
    @SerializedName("strDrinkThumb")
    val strDrinkThumb: String,
    @SerializedName("idDrink")
    val idDrink: String
)
