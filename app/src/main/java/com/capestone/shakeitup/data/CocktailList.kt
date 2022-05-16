package com.capestone.shakeitup.data

import com.google.gson.annotations.SerializedName

data class CocktailList(
    @SerializedName("drinks")
    val cocktailList: List<Cocktail>
    )
