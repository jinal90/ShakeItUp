package com.capestone.shakeitup.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.capestone.shakeitup.R
import com.capestone.shakeitup.data.CocktailDetails
import com.capestone.shakeitup.databinding.FragmentCocktailDetailBinding
import com.capestone.shakeitup.databinding.FragmentCocktailsListBinding
import com.capestone.shakeitup.service.Status
import com.capestone.shakeitup.viewmodel.CocktailListViewModel
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CocktailDetailFragment : Fragment() {
    private var _binding: FragmentCocktailDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CocktailListViewModel by viewModels()
    val args: CocktailDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCocktailDetailBinding.inflate(inflater, container, false)
        val view = binding.root

        val cocktailId = args.cocktailId
        viewModel.getCocktailDetails(cocktailId)
            .observe(viewLifecycleOwner) { cocktailResource ->
                cocktailResource?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        resource.data?.let { cocktail ->
                            binding.tvCocktailName.text = cocktail.cocktailDetailsList[0].strDrink
                            binding.tvCocktailType.text = "This drink is "+cocktail.cocktailDetailsList[0].strAlcoholic

                            binding.tvIngredients.text = prepareIngredientsToDisplay(cocktail.cocktailDetailsList[0])
                            binding.tvInstruction.text = cocktail.cocktailDetailsList[0].strInstructions

                            Glide
                                .with(view)
                                .load(cocktail.cocktailDetailsList[0].strDrinkThumb)
                                .placeholder(R.drawable.ic_baseline_wine_bar_24)
                                .into(binding.ivCocktail)

                            Log.d(
                                "Test list", "Test 1234 -> first alcoholic drink id:" +
                                        " ${cocktail.cocktailDetailsList[0].idDrink} = args $cocktailId"
                            )
                        }
                    }
                    Status.ERROR -> {
                        Toast.makeText(context, cocktailResource.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                        Log.d("Test alcoholic", "Test 1234 -> alcoholic Loading")
                    }
                }
            }
        }
        return view
    }

    private fun prepareIngredientsToDisplay(cocktail: CocktailDetails) : String {
        var ingredients = "Ingredients: \n"

        cocktail.strIngredient1?.let{
            ingredients = "$ingredients${cocktail.strMeasure1} $it \n"
        } ?: run {
            Log.d("", "ingredient1 is null")
            return ingredients
        }
        cocktail.strIngredient2?.let{
            ingredients = "$ingredients${cocktail.strMeasure2} $it \n"
        } ?: run {
            Log.d("", "ingredient2 is null")
            return ingredients
        }
        cocktail.strIngredient3?.let{
            ingredients = "$ingredients${cocktail.strMeasure3} $it \n"
        } ?: run {
            Log.d("", "ingredient3 is null")
            return ingredients
        }
        cocktail.strIngredient4?.let{
            ingredients = "$ingredients${cocktail.strMeasure4} $it \n"
        } ?: run {
            Log.d("", "ingredient4 is null")
            return ingredients
        }
        cocktail.strIngredient5?.let{
            ingredients = "$ingredients${cocktail.strMeasure5} $it \n"
        } ?: run {
            Log.d("", "ingredient5 is null")
            return ingredients
        }
        cocktail.strIngredient6?.let{
            ingredients = "$ingredients${cocktail.strMeasure6} $it \n"
        } ?: run {
            Log.d("", "ingredient6 is null")
            return ingredients
        }
        cocktail.strIngredient7?.let{
            ingredients = "$ingredients${cocktail.strMeasure7} $it \n"
        } ?: run {
            Log.d("", "ingredient7 is null")
            return ingredients
        }

        cocktail.strIngredient8?.let{
            ingredients = "$ingredients${cocktail.strMeasure8} $it \n"
        } ?: run {
            Log.d("", "ingredient8 is null")
            return ingredients
        }

        cocktail.strIngredient9?.let{
            ingredients = "$ingredients${cocktail.strMeasure9} $it \n"
        } ?: run {
            Log.d("", "ingredient9 is null")
            return ingredients
        }

        cocktail.strIngredient10?.let{
            ingredients = "$ingredients${cocktail.strMeasure10} $it \n"
        } ?: run {
            Log.d("", "ingredient10 is null")
            return ingredients
        }

        cocktail.strIngredient11?.let{
            ingredients = "$ingredients${cocktail.strMeasure11} $it \n"
        } ?: run {
            Log.d("", "ingredient11 is null")
            return ingredients
        }

        cocktail.strIngredient12?.let{
            ingredients = "$ingredients${cocktail.strMeasure12} $it \n"
        } ?: run {
            Log.d("", "ingredient12 is null")
            return ingredients
        }

        cocktail.strIngredient13?.let{
            ingredients = "$ingredients${cocktail.strMeasure13} $it \n"
        } ?: run {
            Log.d("", "ingredient13 is null")
            return ingredients
        }

        cocktail.strIngredient14?.let{
            ingredients = "$ingredients${cocktail.strMeasure14} $it \n"
        } ?: run {
            Log.d("", "ingredient14 is null")
            return ingredients
        }

        cocktail.strIngredient15?.let{
            ingredients = "$ingredients${cocktail.strMeasure15} $it \n"
        } ?: run {
            Log.d("", "ingredient15 is null")
            return ingredients
        }

        return ingredients
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}