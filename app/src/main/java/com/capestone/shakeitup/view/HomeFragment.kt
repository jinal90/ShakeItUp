package com.capestone.shakeitup.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.capestone.shakeitup.R
import com.capestone.shakeitup.databinding.FragmentHomeBackupBinding
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
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToCocktailsListFragment())
        }
        binding.cardViewMocktail.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToCocktailsListFragment())
        }
        val view = binding.root
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}