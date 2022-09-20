package com.miel3k.masteringandroidpaging3.users

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.miel3k.masteringandroidpaging3.data.user.model.User

/**
 * Created by jmielczarek on 19/09/2022
 */
interface UsersPaging {
    fun getPagingLiveData(): LiveData<PagingData<User>>
}