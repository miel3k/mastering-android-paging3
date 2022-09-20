package com.miel3k.masteringandroidpaging3.users.view

import android.view.View
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.miel3k.masteringandroidpaging3.databinding.ItemUsersLoadStateBinding

/**
 * Created by jmielczarek on 20/09/2022
 */
class UsersLoadStateViewHolder(itemView: View, private val onRetryClick: () -> Unit) :
    RecyclerView.ViewHolder(itemView) {

    private val binding = ItemUsersLoadStateBinding.bind(itemView)

    fun bind(loadState: LoadState) {
        binding.run {
            pbLoading.isVisible = loadState is LoadState.Loading
            tvError.isVisible = !(loadState as? LoadState.Error)?.error?.message.isNullOrBlank()
            tvError.text = (loadState as? LoadState.Error)?.error?.message
            mbRetry.isVisible = loadState is LoadState.Error
            mbRetry.setOnClickListener { onRetryClick() }
        }
    }
}
