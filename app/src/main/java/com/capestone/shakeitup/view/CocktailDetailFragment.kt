package com.capestone.shakeitup.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.capestone.shakeitup.R
import com.capestone.shakeitup.databinding.FragmentCocktailDetailBinding
import com.capestone.shakeitup.databinding.FragmentCocktailsListBinding

class CocktailDetailFragment : Fragment() {
    private var _binding: FragmentCocktailDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCocktailDetailBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}