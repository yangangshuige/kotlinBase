package com.xtool.polaris.network

import com.xtool.polaris.api.Api
import com.xtool.polaris.api.ApiConstants
import okhttp3.OkHttpClient
import retrofit2.Retrofit

/**
 * description:
 *
 * @author Db_z
 * @Date 2021/10/14 15:09
 */
class RetrofitFactory private constructor(hostType: Int = ApiConstants.ANDROID_URL) :
    BaseRetrofitFactory() {

    val service by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
        getService(Api::class.java, hostType)
    }

    companion object {
        val instance: RetrofitFactory by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            RetrofitFactory()
        }
//        private var instance: RetrofitFactory? = null
//        fun getInStance(hostType: Int = ANDROID_URL) = instance ?: synchronized(this) {
//            instance ?: RetrofitFactory(hostType).also { instance = it }
//        }
    }

    override fun handleBuilder(builder: OkHttpClient.Builder) {

    }

    override fun retrofitBuilder(builder: Retrofit.Builder) {

    }
}