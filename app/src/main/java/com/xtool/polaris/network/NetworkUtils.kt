package com.xtool.polaris.network

import com.xtool.polaris.utils.LogUtils
import com.xtool.polaris.utils.NetUtil
import okio.IOException

object NetworkUtils {
    private val TAG = NetworkUtils::class.java.simpleName

    //    suspend fun <T : Any> callRequest(
//        call: suspend () -> BaseResult<T>
//    ): BaseResult<T> {
//        return try {
//            call()
//        } catch (e: Exception) {
//            //这里统一处理异常
//            e.printStackTrace()
//            val recordMethodName = Thread.currentThread().stackTrace
//            LogUtils.d(
//                TAG,
//                "className = ${recordMethodName[4].className} fileName = ${recordMethodName[4].fileName} lineNumber = ${recordMethodName[4].lineNumber} methodName = ${recordMethodName[4].methodName}"
//            )
//            BaseResult.failure(if (!NetUtil.isConnected()) IOException("网络未连接") else e)
//        }
//    }
//
//    /**
//     * 处理返回结果
//     *
//     *  请注意：当前在IO线程
//     *  @param successBlock  成功之后处理
//     *  @param errorBlock    失败之后处理
//     */
//    suspend fun <T : Any> handlerResponse(
//        response: BaseResponse<T>,
//        successBlock: (suspend () -> Unit)? = null,
//        errorBlock: (suspend () -> Unit)? = null
//    ): BaseResult<T> {
////    return coroutineScope {
//        return if (response.errorCode == 0) {
//            val recordMethodName = Thread.currentThread().stackTrace
//            LogUtils.d(
//                TAG,
//                "className = ${recordMethodName[4].className} fileName = ${recordMethodName[4].fileName} lineNumber = ${recordMethodName[4].lineNumber} methodName = ${recordMethodName[4].methodName}"
//            )
//            // 成功之后处理
//            successBlock?.let { it() }
//            BaseResult.success(response.data)
//        } else {
//            val recordMethodName = Thread.currentThread().stackTrace
//            LogUtils.d(
//                TAG,
//                "className = ${recordMethodName[4].className} fileName = ${recordMethodName[4].fileName} lineNumber = ${recordMethodName[4].lineNumber} methodName = ${recordMethodName[4].methodName}"
//            )
//            // 失败之后处理
//            errorBlock?.let { it() }
//            BaseResult.failure(Exception("Failed to ${recordMethodName[4].className}${response.errorMsg}"))
//        }
////    }
//    }
    suspend fun <T> callRequest(
        call: suspend () -> ResourceState<T>
    ): ResourceState<T> {
        return try {
            call()
        } catch (e: Exception) {
            //这里统一处理异常
            e.printStackTrace()
            val recordMethodName = Thread.currentThread().stackTrace
            LogUtils.d(
                TAG,
                "className = ${recordMethodName[4].className} fileName = ${recordMethodName[4].fileName} lineNumber = ${recordMethodName[4].lineNumber} methodName = ${recordMethodName[4].methodName}"
            )
            ResourceState.error(null,-1,if (!NetUtil.isConnected()) "网络未连接" else e.message)
        }
    }
    suspend fun <T> handlerResponse(response: BaseResponse<T>): ResourceState<T> {
        return if (response.errorCode == 0) {
            ResourceState.success(response.data)
        } else {
            ResourceState.error(response.data, response.errorCode, response.errorMsg)
        }
    }
}