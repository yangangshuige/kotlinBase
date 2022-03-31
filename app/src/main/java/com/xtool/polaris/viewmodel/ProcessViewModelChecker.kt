package com.xtool.polaris.viewmodel

import java.lang.IllegalStateException
import java.util.concurrent.atomic.AtomicInteger

/**
 * 进程唯一ViewModel检查工具
 */
internal class ProcessViewModelChecker private constructor() {
    companion object{
        private val sInstanceGuard = HashMap<String, AtomicInteger>()

        fun <T> checkInstance(clazz: Class<T>) {
            val className = clazz.name
            val instanceGuard = sInstanceGuard[className]?: AtomicInteger(0)
            if (!instanceGuard.compareAndSet(0, 1)) {
                val simpleName = clazz.simpleName
                throw IllegalStateException("Create instance by:" +
                        "val viewModel: $simpleName by processViewModels()")
            }

            sInstanceGuard[className] = instanceGuard
        }
    }
}