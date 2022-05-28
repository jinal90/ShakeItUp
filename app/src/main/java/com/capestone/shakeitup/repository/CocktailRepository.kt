package com.capestone.shakeitup.repository

import androidx.annotation.WorkerThread
import com.capestone.shakeitup.data.Cocktail
import com.capestone.shakeitup.data.CocktailDao
import com.capestone.shakeitup.data.CocktailDetailList
import com.capestone.shakeitup.data.CocktailList
import com.capestone.shakeitup.service.CocktailApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CocktailRepository @Inject constructor(private val cocktailDao: CocktailDao) :
    ICocktailRepository {
    override suspend fun getAlcoholicCocktails() =
        CocktailApi.retrofitService.getAlcoholicCocktails()

    override suspend fun getNonAlcoholicCocktails() =
        CocktailApi.retrofitService.getNonAlcoholicCocktails()

    override suspend fun getCocktailDetails(id: String): CocktailDetailList {
        return CocktailApi.retrofitService.getCocktailDetails(id)
    }

    override suspend fun getRandomCocktail() = CocktailApi.retrofitService.getRandomCocktail()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    override suspend fun saveCocktail(cocktail: Cocktail) {
        cocktailDao.saveCocktail(cocktail)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    override suspend fun deleteCocktail(cocktail: Cocktail) {
        cocktailDao.deleteCocktail(cocktail)
    }

    override suspend fun getAllSavedCocktails(): CocktailList {
        val listOfCocktails = cocktailDao.getAllSavedCocktails()
        return CocktailList(cocktailList = listOfCocktails)
    }
}