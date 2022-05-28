package com.capestone.shakeitup.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.capestone.shakeitup.data.Cocktail
import com.capestone.shakeitup.repository.CocktailRepository
import com.capestone.shakeitup.service.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CocktailListViewModel @Inject constructor(private val repository: CocktailRepository) :
    ViewModel() {

    fun getAlcoholicCocktails() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {

            val alcoholicDrinks = repository.getAlcoholicCocktails()
            val savedDrinks = repository.getAllSavedCocktails()
            for(savedCocktail in savedDrinks.cocktailList){
                for(cocktail in alcoholicDrinks.cocktailList)
                {
                    if (cocktail.idDrink == savedCocktail.idDrink)
                    {
                        cocktail.isFavorite = true
                        break
                    }
                }
            }
            emit(Resource.success(data = alcoholicDrinks))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

    fun getNonAlcoholicCocktails() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            val nonalcoholicDrinks = repository.getNonAlcoholicCocktails()
            val savedDrinks = repository.getAllSavedCocktails()
            for(savedCocktail in savedDrinks.cocktailList){
                for(cocktail in nonalcoholicDrinks.cocktailList)
                {
                    if (cocktail.idDrink == savedCocktail.idDrink)
                    {
                        cocktail.isFavorite = true
                        break
                    }
                }
            }
            emit(Resource.success(data = nonalcoholicDrinks))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

    fun getCocktailDetails(id: String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = repository.getCocktailDetails(id)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

    fun getRandomCocktail() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = repository.getRandomCocktail()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

    fun saveCocktail(cocktail: Cocktail) {
        CoroutineScope(Dispatchers.IO).launch {
            repository.saveCocktail(cocktail)
        }
    }

    fun deleteCocktail(cocktail: Cocktail) {
        CoroutineScope(Dispatchers.IO).launch {
            repository.deleteCocktail(cocktail)
        }
    }

    fun getAllSavedCocktails(cocktail: Cocktail) {
        viewModelScope.launch {
            repository.saveCocktail(cocktail)
        }
    }

    override fun onCleared() {
        super.onCleared()
    }
}