package com.develo.ff_arsimulator.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import com.develo.ff_arsimulator.R
import com.develo.ff_arsimulator.databinding.FragmentHomeBinding
import com.develo.ff_arsimulator.ui.arguide.ArGuideActivity

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateDarkUI()
        binding.cvUsageGuide.setOnClickListener {
            Intent(requireContext(), ArGuideActivity::class.java).also {
                startActivity(it)
            }
        }
    }

    private fun updateDarkUI() {
        val mode = AppCompatDelegate.getDefaultNightMode()
        if (mode == AppCompatDelegate.MODE_NIGHT_YES) {
            binding.ivLogoTertiary.setImageResource(R.drawable.ic_arsim_logo_dark_tertiary)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}