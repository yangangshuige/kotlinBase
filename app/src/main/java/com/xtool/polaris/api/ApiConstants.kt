package com.xtool.polaris.api

import com.xtool.polaris.api.HttpsApi.BASE_URL
import com.xtool.polaris.api.HttpsApi.GANK_IO_URL

object ApiConstants {
    const val ANDROID_URL = 1
    const val WAN_ANDROID = 2

    fun getHost(hostType: Int): String {
        return when (hostType) {
            ANDROID_URL -> BASE_URL
            WAN_ANDROID -> GANK_IO_URL
            else -> BASE_URL
        }
    }
}
