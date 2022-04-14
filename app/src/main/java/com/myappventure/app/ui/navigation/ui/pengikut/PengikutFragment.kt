package com.myappventure.app.ui.navigation.ui.pengikut

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.myappventure.app.databinding.FragmentPengikutBinding
import com.myappventure.app.ui.navigation.ui.mengikuti.RecyclerMengikutiAdapter

class PengikutFragment : Fragment() {

    private var _binding: FragmentPengikutBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPengikutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerPengikut.apply {
            adapter = RecyclerMengikutiAdapter()
            layoutManager = LinearLayoutManager(view.context)
        }
    }
}
