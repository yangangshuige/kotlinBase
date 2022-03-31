package com.xtool.polaris.ui.launch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.xtool.polaris.R
import com.xtool.polaris.ext.isAgreePrivacy

class LaunchActivity : AppCompatActivity() {
    private var navController: NavController? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launch)
        val hostFragment = NavHostFragment.create(R.navigation.launch_nav_graph)
        supportFragmentManager.beginTransaction().replace(android.R.id.content, hostFragment)
            .commit()
        val lifecycleEventObserver = object : LifecycleEventObserver {
            override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
                if (event == Lifecycle.Event.ON_CREATE) {
                    onCreateHostFragment(hostFragment)
                    hostFragment.lifecycle.removeObserver(this)
                }
            }

        }
        hostFragment.lifecycle.addObserver(lifecycleEventObserver)

    }

    private fun onCreateHostFragment(hostFragment: NavHostFragment) {
        navController=hostFragment.findNavController()
        if(isAgreePrivacy()){
            navController?.navigate(R.id.navigation_launch)
        }else{
            navController?.navigate(R.id.navigation_privacy)
        }
    }
}