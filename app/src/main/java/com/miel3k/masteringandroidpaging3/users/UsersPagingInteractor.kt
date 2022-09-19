package com.miel3k.masteringandroidpaging3.users

import androidx.lifecycle.LiveData
import androidx.paging.*
import com.miel3k.masteringandroidpaging3.data.pagingkey.PagingKeyDataSource
import com.miel3k.masteringandroidpaging3.data.users.UsersDataSource
import com.miel3k.masteringandroidpaging3.data.users.model.User

/**
 * Created by jmielczarek on 19/09/2022
 */
class UsersPagingInteractor(
    private val pagingKeyRepository: PagingKeyDataSource,
    private val usersRepository: UsersDataSource
) : UsersPaging {

    @OptIn(ExperimentalPagingApi::class)
    override fun getPagingLiveData(): LiveData<PagingData<User>> {
        val config = PagingConfig(
            USERS_PAGE_SIZE,
            initialLoadSize = USERS_PAGE_SIZE,
            prefetchDistance = USERS_PAGE_SIZE / 2
        )
        val mediator = UsersPagingMediator(pagingKeyRepository, usersRepository)
        val factory = { usersRepository.getUsersPagingSource() }
        return Pager(config, remoteMediator = mediator, pagingSourceFactory = factory).liveData
    }

    private companion object {
        const val USERS_PAGE_SIZE = 20
    }
}