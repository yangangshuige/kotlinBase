package com.xtool.polaris.ui.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.xtool.polaris.R
import com.xtool.polaris.databinding.FragmentMainBinding
import com.xtool.polaris.ext.agreePrivacy
import com.xtool.polaris.ui.dashboard.DashboardFragment
import com.xtool.polaris.ui.home.HomeFragment
import com.xtool.polaris.ui.notifications.NotificationsFragment

class MainFragment : Fragment(R.layout.fragment_main) {
    private val homeFragment: Fragment by lazy {
        HomeFragment()
    }
    private val dashboardFragment: Fragment by lazy {
        DashboardFragment()
    }
    private val notificationsFragment: Fragment by lazy {
        NotificationsFragment()
    }
    private lateinit var viewBinding: FragmentMainBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding = FragmentMainBinding.bind(view)
        viewBinding.bottomNavigation.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.navigation_home -> {
                    childFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, homeFragment).commit()
                    agreePrivacy(true)
                }
                R.id.navigation_dashboard -> {
                    childFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, dashboardFragment).commit()
                    agreePrivacy(false)
                }
                R.id.navigation_notifications -> {
                    childFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, notificationsFragment).commit()
                }
            }
            true
        }
        viewBinding.bottomNavigation.selectedItemId = R.id.navigation_home
    }
}