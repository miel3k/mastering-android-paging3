package com.miel3k.masteringandroidpaging3.utils

import androidx.recyclerview.widget.DiffUtil

/**
 * Created by miel3k on 20/09/2022
 */
fun <T : Any> getDiffCallback(compare: ((oldItem: T, newItem: T) -> Boolean)? = null) =
    object : DiffUtil.ItemCallback<T>() {
        override fun areItemsTheSame(oldItem: T, newItem: T) =
            compare?.invoke(oldItem, newItem) ?: false

        override fun areContentsTheSame(oldItem: T, newItem: T) = oldItem == newItem
    }