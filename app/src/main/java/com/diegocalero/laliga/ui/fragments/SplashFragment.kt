package com.diegocalero.laliga.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.diegocalero.laliga.databinding.FragmentSplashBinding
import com.diegocalero.laliga.viewmodels.SplashViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment : Fragment() {

    private val viewModel: SplashViewModel by viewModels()
    private lateinit var binding: FragmentSplashBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSplashBinding.inflate(layoutInflater)
        observeSplashLiveData()
        return binding.root
    }

    private fun observeSplashLiveData() {
        viewModel.initSplash()
        viewModel.splashFinished.observe(viewLifecycleOwner) {
            it?.let {
                if(it) {
                    navigateToHomeFragment()
                }
            }
        }
    }

    private fun navigateToHomeFragment() {
        requireActivity().actionBar?.show()
        view?.findNavController()?.navigate(SplashFragmentDirections.actionSplashFragmentToHomeFragment())
    }
}