package com.myappventure.app.ui.navigation.ui.home.foryou

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.myappventure.app.R
import com.myappventure.app.data.local.MySharedPref
import com.myappventure.app.databinding.FragmentForYouBinding
import com.myappventure.app.databinding.ItemPostinganBinding
import com.myappventure.app.ui.login.LoginActivity
import com.myappventure.app.ui.navigation.ui.home.detail_postingan.DetailPostinganActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ForYouFragment : Fragment() {

    private var _binding: FragmentForYouBinding? = null
    private val postinganViewModel: PostinganViewModel by activityViewModels()
    private val binding get() = _binding!!
    private val postinganAdapter = PostinganAdapter(mutableListOf(), onClick = {
        if (!MySharedPref.isLoggedIn) {
            val i = Intent(requireContext(), LoginActivity::class.java)
            startActivity(i)
        }
    },
        onDetail = { postingan ->
            val i = Intent(requireContext(), DetailPostinganActivity::class.java)
            i.putExtra("postingan", postingan)
            startActivity(i)
        },
        onLike = {
            if (MySharedPref.isLoggedIn){
                lifecycleScope.launch {
                    postinganViewModel.likePost(it)
                }
            }

        }
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentForYouBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        lifecycleScope.launch {
            postinganViewModel.getAllPost()
        }
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