package com.capestone.shakeitup.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CocktailDao {

    @Query("Select * from cocktail")
    fun getAllSavedCocktails(): List<Cocktail>

    @Insert
    fun saveCocktail(vararg cocktail: Cocktail)

    @Delete
    fun deleteCocktail(cocktail: Cocktail)
}