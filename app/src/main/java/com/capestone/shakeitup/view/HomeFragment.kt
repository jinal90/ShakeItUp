package com.capestone.shakeitup.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.capestone.shakeitup.R
import com.capestone.shakeitup.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.cardViewCocktail.setOnClickListener {
            findNavController().navigate(
                HomeFragmentDirections.actionHomeFragmentToCocktailsListFragment(
                    isAlcoholic = true, title = getString(
                        R.string.title_alcoholic_list
                    )
                )
            )
        }
        binding.cardViewMocktail.setOnClickListener {
            findNavController().navigate(
                HomeFragmentDirections.actionHomeFragmentToCocktailsListFragment(
                    isAlcoholic = false, title = getString(
                        R.string.title_nonalcoholic_list
                    )
                )
            )
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}