package com.miel3k.masteringandroidpaging3.users.view

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.miel3k.masteringandroidpaging3.R
import com.miel3k.masteringandroidpaging3.users.model.UserItem
import com.miel3k.masteringandroidpaging3.utils.getDiffCallback
import com.miel3k.masteringandroidpaging3.utils.inflate

/**
 * Created by jmielczarek on 20/09/2022
 */
class UsersPagingAdapter : PagingDataAdapter<UserItem, UserViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = parent.inflate(R.layout.item_user)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    fun isEmpty(): Boolean = itemCount < 1

    private companion object {
        val diffCallback = getDiffCallback<UserItem> { oldItem, newItem ->
            newItem.id == oldItem.id
        }
    }
}