package com.xtool.polaris.initializer

import android.content.Context
import androidx.startup.Initializer
import com.tencent.mmkv.MMKV

class MMkvInitializer : Initializer<String> {
    override fun create(context: Context): String {
        return MMKV.initialize(context)
    }

    override fun dependencies(): List<Class<out Initializer<*>>> {
        return emptyList()
    }
}