package com.myappventure.app.ui.navigation.ui.home.create_postingan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.myappventure.app.R
import com.myappventure.app.base.BaseActivity
import com.myappventure.app.databinding.ActivityCreatePostinganBinding
import com.myappventure.app.databinding.ActivityRegisterBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreatePostinganActivity : BaseActivity() {
    private lateinit var binding: ActivityCreatePostinganBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCreatePostinganBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerImage.apply {
            this.layoutManager = GridLayoutManager(context, 3)
        }
    }

    override fun setupObserver() {
//        TODO("Not yet implemented")
    }
}