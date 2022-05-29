package com.capestone.shakeitup.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.capestone.shakeitup.data.Cocktail
import com.capestone.shakeitup.data.CocktailDetails
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

    fun prepareIngredientsToDisplay(cocktail: CocktailDetails, title: String): String {
        var ingredients = title

        cocktail.strIngredient1?.let {
            ingredients = "$ingredients${cocktail.strMeasure1} $it \n"
        } ?: run {
            Log.d("", "ingredient1 is null")
            return ingredients
        }
        cocktail.strIngredient2?.let {
            ingredients = "$ingredients${cocktail.strMeasure2} $it \n"
        } ?: run {
            Log.d("", "ingredient2 is null")
            return ingredients
        }
        cocktail.strIngredient3?.let {
            ingredients = "$ingredients${cocktail.strMeasure3} $it \n"
        } ?: run {
            Log.d("", "ingredient3 is null")
            return ingredients
        }
        cocktail.strIngredient4?.let {
            ingredients = "$ingredients${cocktail.strMeasure4} $it \n"
        } ?: run {
            Log.d("", "ingredient4 is null")
            return ingredients
        }
        cocktail.strIngredient5?.let {
            ingredients = "$ingredients${cocktail.strMeasure5} $it \n"
        } ?: run {
            Log.d("", "ingredient5 is null")
            return ingredients
        }
        cocktail.strIngredient6?.let {
            ingredients = "$ingredients${cocktail.strMeasure6} $it \n"
        } ?: run {
            Log.d("", "ingredient6 is null")
            return ingredients
        }
        cocktail.strIngredient7?.let {
            ingredients = "$ingredients${cocktail.strMeasure7} $it \n"
        } ?: run {
            Log.d("", "ingredient7 is null")
            return ingredients
        }

        cocktail.strIngredient8?.let {
            ingredients = "$ingredients${cocktail.strMeasure8} $it \n"
        } ?: run {
            Log.d("", "ingredient8 is null")
            return ingredients
        }

        cocktail.strIngredient9?.let {
            ingredients = "$ingredients${cocktail.strMeasure9} $it \n"
        } ?: run {
            Log.d("", "ingredient9 is null")
            return ingredients
        }

        cocktail.strIngredient10?.let {
            ingredients = "$ingredients${cocktail.strMeasure10} $it \n"
        } ?: run {
            Log.d("", "ingredient10 is null")
            return ingredients
        }

        cocktail.strIngredient11?.let {
            ingredients = "$ingredients${cocktail.strMeasure11} $it \n"
        } ?: run {
            Log.d("", "ingredient11 is null")
            return ingredients
        }

        cocktail.strIngredient12?.let {
            ingredients = "$ingredients${cocktail.strMeasure12} $it \n"
        } ?: run {
            Log.d("", "ingredient12 is null")
            return ingredients
        }

        cocktail.strIngredient13?.let {
            ingredients = "$ingredients${cocktail.strMeasure13} $it \n"
        } ?: run {
            Log.d("", "ingredient13 is null")
            return ingredients
        }

        cocktail.strIngredient14?.let {
            ingredients = "$ingredients${cocktail.strMeasure14} $it \n"
        } ?: run {
            Log.d("", "ingredient14 is null")
            return ingredients
        }

        cocktail.strIngredient15?.let {
            ingredients = "$ingredients${cocktail.strMeasure15} $it \n"
        } ?: run {
            Log.d("", "ingredient15 is null")
            return ingredients
        }

        return ingredients
    }

    override fun onCleared() {
        super.onCleared()
    }
}