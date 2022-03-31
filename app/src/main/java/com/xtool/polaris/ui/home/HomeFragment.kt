package com.xtool.polaris.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.showToast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.xtool.polaris.R
import com.xtool.polaris.databinding.FragmentHomeBinding
import com.xtool.polaris.network.ResourceState

class HomeFragment : Fragment(R.layout.fragment_home) {

    private val homeViewModel: HomeViewModel by viewModels()
    private lateinit var viewBinding: FragmentHomeBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding = FragmentHomeBinding.bind(view)
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            viewBinding.textHome.text = it
        })
        homeViewModel.bannerLiveData.observeUnSticky(viewLifecycleOwner) { state ->
            when (state) {
                is ResourceState.Success -> {
                    showToast("成功")
                }
                is ResourceState.Error -> {
                    showToast("失败")
                }
            }
        }
        homeViewModel.http()
    }
}