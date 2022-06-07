package com.myappventure.app.ui.navigation.ui.destinasi

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
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
    private val baliDestinasiAdapter = BaliDestinasiAdapter(mutableListOf())
    private val destinasiAdapter = DestinasiAdapter(mutableListOf(),
        onClick = { destinasi ->
            val i = Intent(requireContext(), DetailDestinasiActivity::class.java)
            i.putExtra("destinasi", destinasi)
            startActivity(i)
        }
    )

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
        binding.recyclerDestinasi.apply {
            layoutManager = GridLayoutManager(
                context,
                2
            )
            adapter = destinasiAdapter
        }
        setupObserver()
    }

    private fun setupObserver() {
        destinasiViewModel.destinasiResult.observe(viewLifecycleOwner) {
            destinasiAdapter.destinasi.clear()
            destinasiAdapter.destinasi.addAll(it)
            destinasiAdapter.notifyDataSetChanged()
        }
        baliViewModel.destinasiBaliResult.observe(viewLifecycleOwner) {
            baliDestinasiAdapter.destinasi.clear()
            baliDestinasiAdapter.destinasi.addAll(it)
            baliDestinasiAdapter.notifyDataSetChanged()
        }
    }
}