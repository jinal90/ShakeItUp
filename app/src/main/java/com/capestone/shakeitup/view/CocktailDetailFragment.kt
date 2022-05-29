package com.capestone.shakeitup.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.capestone.shakeitup.R
import com.capestone.shakeitup.databinding.FragmentCocktailDetailBinding
import com.capestone.shakeitup.service.Status
import com.capestone.shakeitup.service.isConnectedToNetwork
import com.capestone.shakeitup.viewmodel.CocktailListViewModel
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
        if (isConnectedToNetwork(requireContext())) {
            viewModel.getCocktailDetails(cocktailId)
                .observe(viewLifecycleOwner) { cocktailResource ->
                    cocktailResource?.let { resource ->
                        when (resource.status) {
                            Status.SUCCESS -> {
                                resource.data?.let { cocktail ->
                                    with(binding) {
                                        tvCocktailName.text =
                                            cocktail.cocktailDetailsList[0].strDrink
                                        tvCocktailType.text = getString(
                                            R.string.drink_type,
                                            cocktail.cocktailDetailsList[0].strAlcoholic
                                        )
                                        tvIngredients.text =
                                            viewModel.prepareIngredientsToDisplay(
                                                cocktail.cocktailDetailsList[0],
                                                getString(R.string.title_ingredients)
                                            )
                                        tvInstruction.text =
                                            cocktail.cocktailDetailsList[0].strInstructions
                                        Glide
                                            .with(view)
                                            .load(cocktail.cocktailDetailsList[0].strDrinkThumb)
                                            .placeholder(R.drawable.ic_baseline_wine_bar_24)
                                            .into(ivCocktail)
                                    }
                                    Log.d(
                                        "Test list", "Test 1234 -> first alcoholic drink id:" +
                                                " ${cocktail.cocktailDetailsList[0].idDrink} = args $cocktailId"
                                    )
                                }
                            }
                            Status.ERROR -> {
                                Toast.makeText(context, cocktailResource.message, Toast.LENGTH_LONG)
                                    .show()
                            }
                            Status.LOADING -> {
                                Log.d("Test alcoholic", "Test 1234 -> alcoholic Loading")
                            }
                        }
                    }
                }
        } else {
            Toast.makeText(
                context,
                getString(R.string.no_internet_connection_message),
                Toast.LENGTH_LONG
            ).show()
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}