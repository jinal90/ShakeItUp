package com.capestone.shakeitup.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.capestone.shakeitup.R
import com.capestone.shakeitup.databinding.FragmentCocktailsListBinding
import com.capestone.shakeitup.databinding.FragmentHomeBinding

class CocktailsListFragment : Fragment() {
    private var _binding: FragmentCocktailsListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCocktailsListBinding.inflate(inflater, container, false)
        binding.dummyButtonList.setOnClickListener {
            findNavController().navigate(CocktailsListFragmentDirections.actionCocktailsListFragmentToCocktailDetailFragment())
        }
        val view = binding.root
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}