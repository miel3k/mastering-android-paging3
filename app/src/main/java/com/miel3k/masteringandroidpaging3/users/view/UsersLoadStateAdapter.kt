package com.miel3k.masteringandroidpaging3.users.view

import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import com.miel3k.masteringandroidpaging3.R
import com.miel3k.masteringandroidpaging3.utils.inflate

/**
 * Created by jmielczarek on 20/09/2022
 */
class UsersLoadStateAdapter(private val onRetryClick: () -> Unit) :
    LoadStateAdapter<UsersLoadStateViewHolder>() {

    override fun onBindViewHolder(holder: UsersLoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): UsersLoadStateViewHolder {
        val view = parent.inflate(R.layout.item_users_load_state)
        return UsersLoadStateViewHolder(view, onRetryClick)
    }
}