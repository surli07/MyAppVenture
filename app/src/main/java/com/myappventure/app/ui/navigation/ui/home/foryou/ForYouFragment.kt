package com.myappventure.app.ui.navigation.ui.home.foryou

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.myappventure.app.databinding.FragmentForYouBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ForYouFragment : Fragment() {

    private var _binding: FragmentForYouBinding? = null
    private val postinganViewModel: PostinganViewModel by activityViewModels()
    private val binding get() = _binding!!
    private val postinganAdapter = PostinganAdapter(mutableListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentForYouBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerForYou.apply {
            layoutManager = LinearLayoutManager(
                context,
                RecyclerView.VERTICAL,
                false
            )
            adapter = postinganAdapter
        }

        setupObserver()
    }

    private fun setupObserver() {
        postinganViewModel.postinganResult.observe(viewLifecycleOwner) {
            postinganAdapter.postingan.clear()
            postinganAdapter.postingan.addAll(it)
            postinganAdapter.notifyDataSetChanged()
        }
    }
}