package com.xtool.polaris.recyclerView

import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.recyclerview.widget.RecyclerView
import com.xtool.polaris.R
import com.xtool.polaris.databinding.PlrPageStateErrorItemBinding

class PageStateAdapter(private val retry: (() -> Unit)? = null) : StateAdapter() {
    override fun onCreateStateViewHolder(parent: ViewGroup, state: State): RecyclerView.ViewHolder {
        return when (state) {
            is State.Loading -> LoadingStateViewHolder(parent)
            is State.Error -> ErrorStateViewHolder(parent) { onRetry() }
            else -> throw IllegalStateException("no default implement")
        }
    }

    override fun onBindStateViewHolder(holder: RecyclerView.ViewHolder, state: State) {
        super.onBindStateViewHolder(holder, state)
        if (holder is ErrorStateViewHolder) {
            holder.bindState(state as State.Error)
        }
    }

    private class LoadingStateViewHolder(parent: ViewGroup) :
        AutoInflateViewHolder(parent, R.layout.plr_page_state_loading_item)

    private class ErrorStateViewHolder(parent: ViewGroup, onRetry: () -> Unit) :
        AutoInflateViewHolder(
            parent,
            R.layout.plr_page_state_error_item
        ) {
        private val viewBinding = PlrPageStateErrorItemBinding.bind(itemView)
        init {
            viewBinding.tvError.setOnClickListener { onRetry }
        }
        fun bindState(state: State.Error) {
            val extras = (state.extras as? ErrorStateExtras) ?: return
            viewBinding.tvError.setText(extras.message)
            viewBinding.imgError.setImageResource(extras.icon)
        }
    }

    private fun onRetry() {
        retry?.invoke()
    }

    data class ErrorStateExtras(@DrawableRes val icon: Int, @StringRes val message: Int) :
        StateExtras()
}