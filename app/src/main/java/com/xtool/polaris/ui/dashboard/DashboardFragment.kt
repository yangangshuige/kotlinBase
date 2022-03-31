package com.xtool.polaris.ui.dashboard

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.xtool.polaris.R
import com.xtool.polaris.databinding.FragmentDashboardBinding

class DashboardFragment : Fragment(R.layout.fragment_dashboard) {

    private  val dashboardViewModel: DashboardViewModel by viewModels()
    private lateinit var viewBinding: FragmentDashboardBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding = FragmentDashboardBinding.bind(view)
        dashboardViewModel.text.observe(viewLifecycleOwner, Observer {
            viewBinding.textDashboard.text =it
        })
    }
}