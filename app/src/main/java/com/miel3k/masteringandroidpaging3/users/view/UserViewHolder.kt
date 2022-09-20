package com.miel3k.masteringandroidpaging3.users.view

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.miel3k.masteringandroidpaging3.data.user.model.User
import com.miel3k.masteringandroidpaging3.databinding.ItemUserBinding

/**
 * Created by jmielczarek on 20/09/2022
 */
class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val binding = ItemUserBinding.bind(itemView)

    fun bind(user: User?) {
        user?.let {
            binding.run {
                tvName.text = "id = ${user.id} name = ${user.name}"
            }
        }
    }
}