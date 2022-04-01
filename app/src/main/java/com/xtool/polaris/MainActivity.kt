package com.xtool.polaris

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.*
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        immersiveStatusBar()
        immersiveNavigationBar()
        setStatusBarColor(ContextCompat.getColor(this,R.color.colorAccent))
        setNavigationBarColor(ContextCompat.getColor(this,R.color.colorAccent))
        fitStatusBar(true)
        fitNavigationBar(true)
    }
}