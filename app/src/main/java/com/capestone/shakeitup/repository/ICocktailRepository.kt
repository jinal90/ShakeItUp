package com.capestone.shakeitup.repository

import com.capestone.shakeitup.data.CocktailDetailList
import com.capestone.shakeitup.data.CocktailList

interface ICocktailRepository {

    suspend fun getAlcoholicCocktails(): CocktailList

    suspend fun getNonAlcoholicCocktails(): CocktailList

    suspend fun getCocktailDetails(id: String): CocktailDetailList

    suspend fun getRandomCocktail(): CocktailDetailList
}