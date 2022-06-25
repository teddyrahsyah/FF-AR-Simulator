package com.develo.ff_arsimulator.ui.modules

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.develo.ff_arsimulator.data.source.Result
import com.develo.ff_arsimulator.databinding.FragmentModuleBinding
import com.develo.ff_arsimulator.viewmodel.ViewModelFactory

class ModuleFragment : Fragment() {

    private var _binding: FragmentModuleBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentModuleBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val factory: ViewModelFactory = ViewModelFactory.getInstance(requireActivity())
        val viewModel: ModuleViewModel by viewModels {
            factory
        }

        val moduleAdapter = ModuleAdapter()
        viewModel.getModules().observe(viewLifecycleOwner) { result ->
            if (result != null) {
                when (result) {
                    is Result.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                    }
                    is Result.Success -> {
                        binding.progressBar.visibility = View.GONE
                        if (result.data.isEmpty()) {
                            binding.tvDataNull.visibility = View.VISIBLE
                        } else {
                            val moduleData = result.data
                            moduleAdapter.submitList(moduleData)
                        }
                    }
                    is Result.Error -> {
                        binding.progressBar.visibility = View.GONE
                        Toast.makeText(
                            context,
                            "Terjadi kesalahan" + result.error,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }

        binding.rvModule.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = moduleAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}