package com.capestone.shakeitup.data

import com.google.gson.annotations.SerializedName

data class CocktailDetailList(
    @SerializedName("drinks")
    val cocktailDetailsList: List<CocktailDetails>
)