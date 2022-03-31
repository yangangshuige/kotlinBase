package com.xtool.polaris.ui.notifications

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.xtool.polaris.R
import com.xtool.polaris.databinding.FragmentNotificationsBinding

class NotificationsFragment : Fragment(R.layout.fragment_notifications) {

    private val notificationsViewModel: NotificationsViewModel by viewModels()
    private lateinit var viewBinding: FragmentNotificationsBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding = FragmentNotificationsBinding.bind(view)
        notificationsViewModel.text.observe(viewLifecycleOwner, Observer {
            viewBinding.textNotifications.text = it
        })
    }
}