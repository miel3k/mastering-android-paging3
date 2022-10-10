package com.miel3k.masteringandroidpaging3.realm.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.miel3k.masteringandroidpaging3.data.pagingkey.PagingKeyDataSource
import com.miel3k.masteringandroidpaging3.data.user.UserDataSource
import com.miel3k.masteringandroidpaging3.di.PagingKeyDataModule
import com.miel3k.masteringandroidpaging3.di.UserDataModule
import com.miel3k.masteringandroidpaging3.users.UsersPagingMediator
import com.miel3k.masteringandroidpaging3.users.model.UserItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Created by jmielczarek on 18/09/2022
 */
@HiltViewModel
class RealmViewModel @Inject constructor(
    @PagingKeyDataModule.RealmPagingKeyDataSource private val pagingKeyRepository: PagingKeyDataSource,
    @UserDataModule.RealmUserDataSource private val userRepository: UserDataSource
) : ViewModel() {

    @OptIn(ExperimentalPagingApi::class)
    val userItemPagingDataFlow: Flow<PagingData<UserItem>> by lazy {
        val config = PagingConfig(
            USERS_PAGE_SIZE,
            initialLoadSize = USERS_PAGE_SIZE,
            prefetchDistance = USERS_PAGE_SIZE / 2
        )
        val mediator = UsersPagingMediator(pagingKeyRepository, userRepository)
        val factory = { userRepository.getUsersPagingSource() }
        Pager(config, remoteMediator = mediator, pagingSourceFactory = factory)
            .flow
            .map { it.map { user -> UserItem(user.id, user.login, user.avatar_url) } }
            .cachedIn(viewModelScope)
    }

    private companion object {
        const val USERS_PAGE_SIZE = 20
    }
}