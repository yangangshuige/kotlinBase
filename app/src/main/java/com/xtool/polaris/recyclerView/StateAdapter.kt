package com.xtool.polaris.recyclerView

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class StateAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var state = State.none()

    final override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        return onCreateStateViewHolder(parent, state)
    }

    final override fun getItemCount(): Int = if (shouldShowState(state)) 1 else 0

    final override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        onBindStateViewHolder(holder, state)
    }

    final override fun getItemViewType(position: Int): Int {
        return when (state) {
            is State.Success -> TYPE_SUCCESS
            is State.Error -> TYPE_ERROR
            is State.None -> TYPE_NONE
            else -> TYPE_LOADING
        }
    }

    fun submitState(state: State) {
        this.state = state
        notifyDataSetChanged()
    }

    abstract fun onCreateStateViewHolder(parent: ViewGroup, state: State): RecyclerView.ViewHolder

    open fun onBindStateViewHolder(holder: RecyclerView.ViewHolder, state: State) {}

    open fun shouldShowState(state: State): Boolean {
        return when (state) {
            is State.Loading -> true
            is State.Error -> true
            is State.Success -> false
            is State.None -> false
        }
    }

    abstract class StateExtras

    sealed class State(val extras: StateExtras?) {
        class Loading(extras: StateExtras?) : State(extras)
        class Success(extras: StateExtras?) : State(extras)
        class Error(extras: StateExtras?) : State(extras)
        class None(extras: StateExtras?) : State(extras)
        companion object {
            fun loading(extras: StateExtras? = null): State = Loading(extras)
            fun success(extras: StateExtras? = null): State = Success(extras)
            fun error(extras: StateExtras? = null): State = Error(extras)
            fun none(extras: StateExtras? = null): State = None(extras)
        }
    }

    companion object {
        private const val TYPE_LOADING = 1
        private const val TYPE_SUCCESS = 2
        private const val TYPE_ERROR = 3
        private const val TYPE_NONE = 4
    }
}