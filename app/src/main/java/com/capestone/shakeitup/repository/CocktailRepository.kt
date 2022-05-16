package com.capestone.shakeitup.repository

import com.capestone.shakeitup.data.CocktailDetailList
import com.capestone.shakeitup.service.CocktailApi
import javax.inject.Inject

class CocktailRepository @Inject constructor() : ICocktailRepository {
    override suspend fun getAlcoholicCocktails() =
        CocktailApi.retrofitService.getAlcoholicCocktails()

    override suspend fun getNonAlcoholicCocktails() =
        CocktailApi.retrofitService.getNonAlcoholicCocktails()

    override suspend fun getCocktailDetails(id: String): CocktailDetailList {
        return CocktailApi.retrofitService.getCocktailDetails(id)
    }

    override suspend fun getRandomCocktail() = CocktailApi.retrofitService.getRandomCocktail()
}