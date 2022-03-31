package com.xtool.polaris.common

import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.xtool.polaris.R
import com.xtool.polaris.recyclerView.PageStateAdapter
import com.xtool.polaris.recyclerView.StateAdapter

abstract class LoadingPageFragment(layout: Int = R.layout.fragment_loading_placeholder) :
    Fragment(layout) {
    private val stateAdapter = PageStateAdapter {
        onRetry()
    }.apply { submitState(StateAdapter.State.loading()) }
    open val stateRecyclerView: RecyclerView?
        get() {
            return view as? RecyclerView
        }

    override fun onResume() {
        super.onResume()
        stateRecyclerView?.adapter = stateAdapter
    }

    protected fun startLoading() {
        stateAdapter.submitState(StateAdapter.State.loading())
    }

    protected fun onLoadingSuccess() {
        stateAdapter.submitState(StateAdapter.State.success())
    }

    protected fun onLoadingFailed(extras: PageStateAdapter.ErrorStateExtras? = null) {
        stateAdapter.submitState(StateAdapter.State.error(extras))
    }

    private fun onRetry() {
    }
}