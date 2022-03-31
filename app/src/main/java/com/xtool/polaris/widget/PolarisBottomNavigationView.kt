package com.xtool.polaris.widget

import android.content.Context
import android.util.AttributeSet
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.xtool.polaris.R

class PolarisBottomNavigationView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null, defStyle: Int = R.attr.bottomNavigationStyle
) : BottomNavigationView(context, attributeSet, defStyle) {
    init {
        setOnApplyWindowInsetsListener(null)
    }
}