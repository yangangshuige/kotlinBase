package com.xtool.polaris.ext

import com.tencent.mmkv.MMKV

private const val KEY_PRIVACY_AGREE = "key_privacy_agree"



fun isAgreePrivacy() = MMKV.defaultMMKV().getBoolean(KEY_PRIVACY_AGREE, false)

fun agreePrivacy(agree: Boolean) {
    MMKV.defaultMMKV().putBoolean(KEY_PRIVACY_AGREE, agree)
}

/**
 * 是否登录，如果登录了就执行then，没有登录就直接跳转登录界面
 * @return true-已登录，false-未登录
 */
//fun checkLogin(then: (() -> Unit)? = null) =
//    if (isLogin()) {
//        then?.invoke()
//        true
//    } else {
//        startLoginActivity()
//        false
//    }


