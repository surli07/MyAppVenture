package com.myappventure.app.ui.navigation.ui.home.follow

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
import com.myappventure.app.data.local.MySharedPref
import com.myappventure.app.databinding.FragmentFollowBinding
import com.myappventure.app.ui.login.LoginActivity
import com.myappventure.app.ui.navigation.ui.home.detail_postingan.DetailPostinganActivity
import com.myappventure.app.ui.navigation.ui.home.foryou.PostinganViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class FollowFragment : Fragment() {

    private var _binding: FragmentFollowBinding? = null
    private val postinganDiikutiViewModel: DiikutiViewModel by activityViewModels()
    private val postinganViewModel: PostinganViewModel by activityViewModels()
    private val binding get() = _binding!!
    private val postinganAdapter = PostinganDiikutiAdapter(mutableListOf(),
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

        })

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFollowBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onResume() {
        super.onResume()
        if(MySharedPref.isLoggedIn){
            lifecycleScope.launch {
                postinganDiikutiViewModel.getAllPost()
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (MySharedPref.isLoggedIn) {
            binding.txtYukBergabung.visibility = View.GONE
            if (postinganAdapter.itemCount == 0) {
                binding.scrollDiikuti.visibility = View.VISIBLE
            } else {
                binding.scrollDiikuti.visibility = View.GONE
                binding.recyclerDiikuti.visibility = View.VISIBLE
                binding.recyclerDiikuti.apply {
                    layoutManager = LinearLayoutManager(
                        context,
                        RecyclerView.VERTICAL,
                        false
                    )
                    adapter = postinganAdapter

                }
            }
        } else {
            binding.txtYukBergabung.setOnClickListener {
                val intent = Intent(requireContext(), LoginActivity::class.java)
                startActivity(intent)
            }
        }

        setupObserver()
    }

    private fun setupObserver() {
        postinganDiikutiViewModel.postinganResult.observe(viewLifecycleOwner) {
            postinganAdapter.postingan.clear()
            postinganAdapter.postingan.addAll(it)
            postinganAdapter.notifyDataSetChanged()
        }
    }
}