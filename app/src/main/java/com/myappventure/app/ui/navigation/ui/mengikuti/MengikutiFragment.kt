package com.myappventure.app.ui.navigation.ui.mengikuti

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.myappventure.app.databinding.FragmentMengikutiBinding

class MengikutiFragment : Fragment() {

    private var _binding: FragmentMengikutiBinding? = null
    private val binding get() = _binding!!

    private var isFollowed = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMengikutiBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerMengikuti.apply {
            adapter = RecyclerMengikutiAdapter()
            layoutManager = LinearLayoutManager(view.context)
        }
    }

}