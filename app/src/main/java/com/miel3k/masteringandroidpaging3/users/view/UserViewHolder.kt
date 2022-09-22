package com.miel3k.masteringandroidpaging3.users.view

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.miel3k.masteringandroidpaging3.databinding.ItemUserBinding
import com.miel3k.masteringandroidpaging3.users.model.UserItem

/**
 * Created by jmielczarek on 20/09/2022
 */
class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val binding = ItemUserBinding.bind(itemView)

    fun bind(userItem: UserItem?) {
        userItem?.let {
            binding.run {
                tvName.text = "id = ${it.id} name = ${it.name}"
            }
        }
    }
}