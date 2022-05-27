package com.myappventure.app.ui.navigation.ui.destinasi

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.myappventure.app.databinding.FragmentDestinasiBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import androidx.fragment.app.activityViewModels as activityViewModels1


@AndroidEntryPoint
class DestinasiFragment : Fragment() {

    private var _binding: FragmentDestinasiBinding? = null
    private val destinasiViewModel: DestinasiViewModel by activityViewModels1()
    private val baliViewModel: BaliViewModel by activityViewModels1()
    private val binding get() = _binding!!
    private val destinasiAdapter = DestinasiAdapter(mutableListOf())
    private val baliDestinasiAdapter = BaliDestinasiAdapter(mutableListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDestinasiBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onResume() {
        super.onResume()
        lifecycleScope.launch {
            destinasiViewModel.getAllDestinasi()
            baliViewModel.getBaliDestinasi()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerPopularDestination.apply {
            layoutManager = LinearLayoutManager(
                context,
                RecyclerView.HORIZONTAL,
                false
            )
            adapter = destinasiAdapter
        }
        binding.recyclerBali.apply {
            layoutManager = LinearLayoutManager(
                context,
                RecyclerView.HORIZONTAL,
                false
            )
            adapter = baliDestinasiAdapter
        }
        setupObserver()
    }

    private fun setupObserver() {
        destinasiViewModel.destinasiResult.observe(viewLifecycleOwner) {
            destinasiAdapter.destinasi.clear()
            destinasiAdapter.destinasi.addAll(it)
            destinasiAdapter.notifyDataSetChanged()
        }
        baliViewModel.destinasiResult.observe(viewLifecycleOwner) {
            baliDestinasiAdapter.destinasi.clear()
            baliDestinasiAdapter.destinasi.addAll(it)
            baliDestinasiAdapter.notifyDataSetChanged()
        }
    }
}