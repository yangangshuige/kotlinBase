package com.didi.bike.applicationholder

import android.app.Application
import android.content.Context
import java.lang.IllegalStateException

class AppContextHolder private constructor(val context: Context){
    companion object{
        internal var instance: AppContextHolder? = null

        internal fun initWithApplication(appContext: Context) {
            instance = AppContextHolder(appContext)
        }

        /**
         * Application context 对象
         */
        fun <T: Application> applicationContext(): T {
            val application = instance?.context?.applicationContext
                ?: throw IllegalStateException("Cannot use context before application create")

            @Suppress("UNCHECKED_CAST")
            return application as T
        }
    }
}