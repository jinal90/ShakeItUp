package com.capestone.shakeitup.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.capestone.shakeitup.data.Cocktail
import com.capestone.shakeitup.databinding.FragmentCocktailsListBinding
import com.capestone.shakeitup.service.Status
import com.capestone.shakeitup.viewmodel.CocktailListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CocktailsListFragment : Fragment() {
    private var _binding: FragmentCocktailsListBinding? = null
    private val binding get() = _binding!!
    private val viewModel: CocktailListViewModel by viewModels()
    val args: CocktailsListFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCocktailsListBinding.inflate(inflater, container, false)
        val adapter = CocktailAdapter { cocktail -> navigateToDetailScreen(cocktail) }
        binding.rlCocktailList.adapter = adapter

        val isAlcoholic = args.isAlcoholic

        if (isAlcoholic) {
            viewModel.getAlcoholicCocktails().observe(viewLifecycleOwner) {
                it?.let { resource ->
                    when (resource.status) {
                        Status.SUCCESS -> {
                            resource.data?.let { list ->
                                adapter.submitList(list.cocktailList)
                                Log.d(
                                    "Test list", "Test 1234 -> first alcoholic drink id:" +
                                            " ${list.cocktailList[0].idDrink}"
                                )
                            }
                        }
                        Status.ERROR -> {
                            Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
                        }
                        Status.LOADING -> {
                            Log.d("Test alcoholic", "Test 1234 -> alcoholic Loading")
                        }
                    }
                }
            }
        } else {
            viewModel.getNonAlcoholicCocktails().observe(viewLifecycleOwner) {
                it?.let { resource ->
                    when (resource.status) {
                        Status.SUCCESS -> {
                            resource.data?.let { list ->
                                adapter.submitList(list.cocktailList)
                                Log.d(
                                    "Test list", "Test 1234 -> first alcoholic drink id:" +
                                            " ${list.cocktailList[0].idDrink}"
                                )
                            }
                        }
                        Status.ERROR -> {
                            Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
                        }
                        Status.LOADING -> {
                            Log.d("Test alcoholic", "Test 1234 -> alcoholic Loading")
                        }
                    }
                }
            }
        }
        val view = binding.root
        return view
    }

    private fun navigateToDetailScreen(cocktail: Cocktail) {
        findNavController().navigate(CocktailsListFragmentDirections.actionCocktailsListFragmentToCocktailDetailFragment(cocktail.idDrink))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}