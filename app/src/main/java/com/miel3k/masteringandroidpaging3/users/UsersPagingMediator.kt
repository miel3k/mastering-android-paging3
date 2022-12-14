package com.miel3k.masteringandroidpaging3.users

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.miel3k.masteringandroidpaging3.data.Result
import com.miel3k.masteringandroidpaging3.data.pagingkey.PagingKeyDataSource
import com.miel3k.masteringandroidpaging3.data.pagingkey.model.PagingKey
import com.miel3k.masteringandroidpaging3.data.user.UserDataSource
import com.miel3k.masteringandroidpaging3.data.user.model.User

/**
 * Created by miel3k on 19/09/2022
 */
@OptIn(ExperimentalPagingApi::class)
class UsersPagingMediator(
    private val pagingKeyRepository: PagingKeyDataSource,
    private val userRepository: UserDataSource
) : RemoteMediator<Int, User>() {

    override suspend fun load(loadType: LoadType, state: PagingState<Int, User>): MediatorResult {
        val key = when (loadType) {
            LoadType.REFRESH -> INITIAL_KEY
            LoadType.PREPEND -> return MediatorResult.Success(true)
            LoadType.APPEND -> pagingKeyRepository.getPagingKey(USERS_PAGING_ID)?.nextPageKey
                ?: return MediatorResult.Success(true)
        }
        val pageSize = state.config.pageSize
        return when (val result = userRepository.loadUsers(key, pageSize)) {
            is Result.Success -> {
                savePage(loadType, result.data)
                saveNextKey(result.data)
                MediatorResult.Success(result.data.isEmpty())
            }
            is Result.Error -> MediatorResult.Error(result.exception)
        }
    }

    private suspend fun savePage(
        loadType: LoadType,
        users: List<User>
    ) = if (loadType == LoadType.REFRESH) {
        userRepository.deleteUsers()
        userRepository.saveUsers(users)
    } else {
        userRepository.saveUsers(users)
    }

    private suspend fun saveNextKey(users: List<User>) {
        val newPagingKey = PagingKey().apply {
            id = USERS_PAGING_ID
            nextPageKey = users.lastOrNull()?.id ?: 0
        }
        pagingKeyRepository.savePagingKey(newPagingKey)
    }

    private companion object {
        const val INITIAL_KEY = 0
        const val USERS_PAGING_ID = "users"
    }
}